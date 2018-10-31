package seedu.saveit.ui;

import static seedu.saveit.logic.parser.CliSyntax.*;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.saveit.logic.parser.SaveItParser.BASIC_COMMAND_FORMAT;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.Logic;
import seedu.saveit.logic.commands.AddCommand;
import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.ArgumentTokenizer;
import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.ui.suggestion.AutoSuggestion;
import seedu.saveit.ui.suggestion.IssueNameAutoSuggestion;
import seedu.saveit.ui.suggestion.TagNameAutoSuggestion;

/**
 * The TextField component which supports auto key word suggestion
 */
public class AutoSuggestionManager extends InlineCssTextArea {

    public static final String WORD_FIND = "find";
    private static final String WORD_TAG = "tag";

    private static final String WHITESPACE_IDENTIFIER = " ";
    private static final String TAG_PREFIX_IDENTIFIER = "t/";
    // To get substring after whitespace, substring starting index has to be increased by 1
    private static final int STRING_INDEX_ADJUSTMENT_WHITESPACE = 1;
    // To get substring after tag prefix, substring starting index has to be increased by 2
    private static final int STRING_INDEX_ADJUSTMENT_TAG_PREFIX = 2;
    private static final int MAX_NUMBER = 5;
    private ContextMenu popUpWindow;

    private IssueNameAutoSuggestion issueSuggestion;
    private TagNameAutoSuggestion tagSuggestion;


    public AutoSuggestionManager() {
        super();
    }

    /**
     * Adds new listener to the text field to handle the key suggestion
     */
    public void initialise(Logic logic) {

        issueSuggestion = new IssueNameAutoSuggestion(logic);
        tagSuggestion = new TagNameAutoSuggestion(logic);
        popUpWindow = new ContextMenu();

        this.textProperty().addListener((observable, oldValue, newValue) -> {
            Matcher matcher = BASIC_COMMAND_FORMAT.matcher(newValue);

            if (!matcher.matches()) {
                popUpWindow.hide();
                return;
            }

            String commandWord = matcher.group("commandWord");
            String arguments = matcher.group("arguments");

            switch (commandWord) {

            case AddCommand.COMMAND_WORD:
            case AddCommand.COMMAND_ALIAS:
                addCommandListener(commandWord, arguments);
                return;

            case EditCommand.COMMAND_WORD:
            case EditCommand.COMMAND_ALIAS:
                editCommandListener(commandWord, arguments);
                return;

            case FindCommand.COMMAND_WORD:
            case FindCommand.COMMAND_ALIAS:
                findCommandListener(commandWord, arguments);
                return;

            default:
                popUpWindow.hide();
            }
        });
    }

    /**
     * Listener for the {@code AddCommand}
     */
    public void addCommandListener(String commandWord, String arguments) {
        if (arguments.length() != 0) {
            showResult(TAG_PREFIX_IDENTIFIER);
        }
    }

    /**
     * Listener for the {@code EditCommand}
     */
    public void editCommandListener(String commandWord, String arguments) {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(arguments, PREFIX_STATEMENT, PREFIX_DESCRIPTION, PREFIX_SOLUTION_LINK,
                        PREFIX_REMARK, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            // TODO: Throw some exception?
        }

        String i = argMultiMap.getPreamble();
        int position = getCaretPosition();

        if (arguments.length() != 0) {
            showResult(TAG_PREFIX_IDENTIFIER);
        }
    }

    /**
     * Listener for the {@code FindCommand}
     */
    public void findCommandListener(String commandWord, String arguments) {
        if (arguments.length() != 0) {
            showResult(WHITESPACE_IDENTIFIER);
        }
    }

    /**
     * Updates the keywords stored in the class whenever there is a change on issue list in the storage.
     */
    public void update(Logic logic) {
        issueSuggestion.update(logic);
        tagSuggestion.update(logic);
    }

    /**
     * Analyses the input string and suggests the key words
     */
    public void showResult(String identifier) {
        String mainText = getText();
        String text;
        AutoSuggestion suggestion;

        int startingIndex;

        if (identifier.equals(WHITESPACE_IDENTIFIER)) {
            startingIndex = mainText.indexOf(identifier) + STRING_INDEX_ADJUSTMENT_WHITESPACE;
        } else {
            startingIndex = mainText.lastIndexOf(identifier) + STRING_INDEX_ADJUSTMENT_TAG_PREFIX;
        }

        if (startingIndex != -1) {
            text = mainText.substring(startingIndex).trim();
        } else {
            text = mainText.trim();
        }

        LinkedList<String> searchResult;
        if (identifier.equals(WHITESPACE_IDENTIFIER) && !getText().contains(WORD_TAG)) {
            searchResult = issueSuggestion.giveSuggestion(text);
            suggestion = issueSuggestion;
        } else {
            searchResult = tagSuggestion.giveSuggestion(text);
            suggestion = tagSuggestion;
        }

        if (searchResult.size() > 0 && text.length() > 0) {
            //hide the suggestion window if the issue statement is already entered completely
            if (searchResult.size() == 1 && searchResult.get(0).equals(text)) {
                popUpWindow.hide();
            } else {
                showSuggestionWindow(searchResult, startingIndex, suggestion);
            }
        } else {
            popUpWindow.hide();
        }
    }

    /**
     * Fills in suggestion content and shows the pop up window
     * @param searchResult List of strings used to create the items
     * @param startingIndex position where the argument starts
     * @param suggestion the AutoSuggestion object being used
     */
    private void showSuggestionWindow(LinkedList<String> searchResult,
            int startingIndex, AutoSuggestion suggestion) {
        int count = Math.min(searchResult.size(), MAX_NUMBER);
        // Builds the dropdown
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            final String previousText = getText();
            Label entryLabel = new Label(result);
            requestFocus();
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(suggestion.getItemHandler(this, previousText, startingIndex, result));
            menuItems.add(item);
        }
        popUpWindow.getItems().clear();
        popUpWindow.getItems().addAll(menuItems);
        getFocused();
        popUpWindow.show(this, Side.BOTTOM, (double) startingIndex * 8, 0);
    }

    /**
     * Makes the popup window get ready to get focused before next showing
     */
    private void getFocused() {
        popUpWindow.show(AutoSuggestionManager.this, Side.BOTTOM, (double) getCaretPosition() * 8, 0);
        popUpWindow.hide();
    }

    /**
     * Gets the contextMenu
     */
    public ContextMenu getWindow() {
        return popUpWindow;
    }
}
