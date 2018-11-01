package seedu.saveit.ui;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.saveit.logic.parser.SaveItParser.BASIC_COMMAND_FORMAT;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
import seedu.saveit.logic.commands.FindByTagCommand;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.ArgumentTokenizer;
import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.Prefix;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.ui.suggestion.AutoSuggestion;
import seedu.saveit.ui.suggestion.CopyExistingAutoSuggestion;
import seedu.saveit.ui.suggestion.IssueNameAutoSuggestion;
import seedu.saveit.ui.suggestion.TagNameAutoSuggestion;

/**
 * The TextField component which supports auto key word suggestion
 */
public class AutoSuggestionManager extends InlineCssTextArea {

    public static final String WORD_FIND = "find";
    private static final String WORD_TAG = "tag";

    private static final String DUMMY_STRING = "";
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

    private Logic logic;

    public AutoSuggestionManager() {
        super();
    }

    /**
     * Adds new listener to the text field to handle the key suggestion
     */
    public void initialise(Logic logic) {
        this.logic = logic;
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

            case FindByTagCommand.COMMAND_WORD:
            case FindByTagCommand.COMMAND_ALIAS:
                findTagCommandListener(commandWord, arguments);
                return;

            default:
                popUpWindow.hide();
            }
        });
    }

    /**
     * Handles the method call to display the suggestion box
     */
    private void handleCopyExisting(Index index, Prefix prefix) {
        AutoSuggestion suggestion = new CopyExistingAutoSuggestion(logic, index);
        // TODO: Temporarily feed in empty string, need refactoring
        LinkedList<String> values = suggestion.giveSuggestion(prefix.getPrefix());
        showSuggestionWindow(values, getCaretPosition(), suggestion, DUMMY_STRING);
    }

    /**
     * Listener for the {@code AddCommand}
     */
    public void addCommandListener(String commandWord, String arguments) {
        if (arguments.length() != 0) {
            showResult(TAG_PREFIX_IDENTIFIER, commandWord, arguments);
        }
    }

    /**
     * Listener for the {@code AddCommand}
     */
    public void findTagCommandListener(String commandWord, String arguments) {
        if (arguments.length() != 0) {
            showResult(WHITESPACE_IDENTIFIER, commandWord, arguments);
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
            return;
        }

        String i = argMultiMap.getPreamble();
        int position = getCaretPosition() - commandWord.length();

        Prefix prefix = argMultiMap.findNearestPrefixKey(position);

        if (prefix == null) {
            return;
        }

        // Differentiate suggestion based on prefix
        if (prefix.equals(PREFIX_STATEMENT) || prefix.equals(PREFIX_DESCRIPTION)) {
            Optional<String> posArgs = argMultiMap.getValue(prefix);
            // Only show AutoSuggestion box if the string is empty
            if (posArgs.isPresent() && posArgs.get().length() == 0) {
                handleCopyExisting(index, prefix);
            } else {
                popUpWindow.hide();
            }
        } else if (prefix.equals(PREFIX_TAG)) {
            showResult(TAG_PREFIX_IDENTIFIER, commandWord, arguments);
        }


    }

    /**
     * Listener for the {@code FindCommand}
     */
    public void findCommandListener(String commandWord, String arguments) {
        if (arguments.length() != 0) {
            showResult(WHITESPACE_IDENTIFIER, commandWord, arguments);
        }
    }

    /**
     * Updates the keywords stored in the class whenever there is a change on issue list in the storage.
     */
    public void update(Logic logic) {
        this.logic = logic;
        issueSuggestion.update(logic);
        tagSuggestion.update(logic);
    }

    /**
     * Analyses the input string and suggests the key words
     */
    public void showResult(String identifier, String commandWord, String argument) {
        if (identifier.equals(WHITESPACE_IDENTIFIER)) {
            handleSuggestionForFinding();
        } else {
            handleSuggestionForAddingEditing();
        }

    }

    /**
     * Analyses the input String and give suggestion for find and findtag command
     */
    private void handleSuggestionForFinding() {
        String mainText = getText();
        int startingIndex = mainText.indexOf(WHITESPACE_IDENTIFIER) + STRING_INDEX_ADJUSTMENT_WHITESPACE;
        String text = startingIndex == -1? mainText.trim() : mainText.substring(startingIndex).trim();
        LinkedList<String> searchResult = mainText.contains(WORD_TAG)? tagSuggestion.giveSuggestion(text)
                : issueSuggestion.giveSuggestion(text);
        AutoSuggestion suggestion = mainText.contains(WORD_TAG)? tagSuggestion : issueSuggestion;
        showWindow(searchResult, text, startingIndex, suggestion, DUMMY_STRING);
    }

    /**
     * Analyses the input String and give suggestion for tags in add and edit command
     */
    private void handleSuggestionForAddingEditing() {
        String cursorString = getText().substring(0, getCaretPosition());
        if (cursorString.lastIndexOf(TAG_PREFIX_IDENTIFIER) == -1 ) {
            return;
        }
        int startingIndex = cursorString.lastIndexOf(TAG_PREFIX_IDENTIFIER);
        String afterText = getText().substring(getCaretPosition(), getText().length());
        String substring = cursorString.substring(startingIndex, getCaretPosition());
        startingIndex += STRING_INDEX_ADJUSTMENT_TAG_PREFIX;
        String text = substring.contains(WHITESPACE_IDENTIFIER)? DUMMY_STRING :
                substring.replaceFirst(TAG_PREFIX_IDENTIFIER, DUMMY_STRING);
        LinkedList<String> searchResult = tagSuggestion.giveSuggestion(text);
        showWindow(searchResult, text, startingIndex, tagSuggestion, afterText);
    }

    /**
     * Handles the pop up window
     * @param searchResult List of strings used to create the items
     * @param text The context which user enters
     * @param startingIndex position where the argument starts
     * @param suggestion The AutoSuggestion object being used
     * @param afterText The command line string will be truncated into three parts when dealing with tag,
     * it represents the last truncated part which is after the cursor position.
     */
    private void showWindow(LinkedList<String> searchResult, String text, int startingIndex,
            AutoSuggestion suggestion, String afterText) {
        if (searchResult.size() > 0 && text.length() > 0) {
            //hide the suggestion window if the issue statement is already entered completely
            if (searchResult.size() == 1 && searchResult.get(0).equals(text)) {
                popUpWindow.hide();
            } else {
                showSuggestionWindow(searchResult, startingIndex, suggestion, afterText);
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
            int startingIndex, AutoSuggestion suggestion, String afterText) {
        int count = Math.min(searchResult.size(), MAX_NUMBER);
        // Builds the dropdown
        List<CustomMenuItem> menuItems = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            final String previousText = getText();
            Label entryLabel = new Label(result);
            requestFocus();
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            item.setOnAction(suggestion.getItemHandler(this, previousText, afterText, startingIndex, i));
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
