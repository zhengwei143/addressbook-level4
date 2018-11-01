package seedu.saveit.logic;

import static seedu.saveit.logic.parser.CliSyntax.*;
import static seedu.saveit.logic.parser.SaveItParser.BASIC_COMMAND_FORMAT;

import java.util.LinkedList;
import java.util.Optional;
import java.util.regex.Matcher;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.AddCommand;
import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.FindByTagCommand;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.ArgumentTokenizer;
import seedu.saveit.logic.parser.ParserUtil;
import seedu.saveit.logic.parser.Prefix;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.logic.suggestion.*;
import seedu.saveit.logic.suggestion.Suggestion;
import seedu.saveit.model.Model;

/**
 * The TextField component which supports auto key word suggestion
 */
public class SuggestionLogicManager implements SuggestionLogic {

    private static final int INITIAL_CARET_POSITION = 0;

    public static final String WORD_FIND = "find";
    private static final String WORD_TAG = "tag";

    private static final String DUMMY_STRING = "";
    private static final String WHITESPACE_IDENTIFIER = " ";
    private static final String TAG_PREFIX_IDENTIFIER = "t/";
    // To get substring after whitespace, substring starting index has to be increased by 1
    private static final int STRING_INDEX_ADJUSTMENT_WHITESPACE = 1;
    // To get substring after tag startPrefix, substring starting index has to be increased by 2
    private static final int STRING_INDEX_ADJUSTMENT_TAG_PREFIX = 2;


    private final Model model;
    private int caretPosition;

    public SuggestionLogicManager(Model model) {
        this.model = model;
        this.caretPosition = INITIAL_CARET_POSITION;
    }

    /**
     * Generates a {@code SuggestionResult} based on the {@code userInput}
     */
    public SuggestionResult evaluate(String userInput) {
        Suggestion suggestion = parseUserInput(userInput);
        if (suggestion == null) {
            return new SuggestionResult(new LinkedList<>(), "", 0, 0);
        }
        return suggestion.evaluate();
    }

    /**
     * Called by the UI component
     */
    public void updateCaretPosition(int position) {
        this.caretPosition = position;
    }

    /**
     * Instantiates a {@code Suggestion} based on the {@code userInput}
     */
    private Suggestion parseUserInput(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput);

        if (!matcher.matches()) {
            // TODO: Handle invalid userInput(s)
            return null;
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenizeAndOffset(arguments, commandWord.length(),
                        PREFIX_STATEMENT, PREFIX_DESCRIPTION, PREFIX_SOLUTION_LINK, PREFIX_REMARK, PREFIX_TAG);
        // Nearest Prefix preceding the caret position
        Prefix startPrefix = argMultiMap.findPrecedingPrefixKey(caretPosition);
        
        if (startPrefix == null) {
            // TODO: Handle no startPrefix found
            return null;
        }

        Prefix endPrefix = argMultiMap.findSucceedingPrefixKey(startPrefix);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
        case AddCommand.COMMAND_ALIAS:
            return parseAddCommandSuggestion(commandWord, arguments);

        case EditCommand.COMMAND_WORD:
        case EditCommand.COMMAND_ALIAS:
            return parseEditCommandSuggestion(argMultiMap, startPrefix, endPrefix);

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_ALIAS:
            return parseFindCommandSuggestion(startPrefix, endPrefix);

        case FindByTagCommand.COMMAND_WORD:
        case FindByTagCommand.COMMAND_ALIAS:
            return parseFindByTagCommandSuggestion(commandWord, arguments);

        default:
            // TODO: Handle invalid comamndWord(s)
            return null;
        }
    }

    /**
     * Determine which {@code Suggestion} to return given the arguments and caret position
     * for the {@code AddCommand}
     */
    public Suggestion parseAddCommandSuggestion(String commandWord, String arguments) {
        if (arguments.length() != 0) {
//            handleSuggestionForAddingEditing();
        }
        return null;
    }

    /**
     * Determine which {@code Suggestion} to return given the arguments and caret position
     * for the {@code EditCommand}
     */
    public Suggestion parseEditCommandSuggestion(
            ArgumentMultimap argMultiMap, Prefix startPrefix, Prefix endPrefix) {

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            // TODO: Throw some exception?
            return null;
        }

        switch (startPrefix.getPrefix()) {

        case PREFIX_STATEMENT_STRING:
        case PREFIX_DESCRIPTION_STRING:
            Optional<String> posArgs = argMultiMap.getValue(startPrefix);
            // Only show Suggestion box if the string is empty
            if (posArgs.isPresent() && posArgs.get().length() == 0) {
                return new CopyExistingSuggestion(model, index, startPrefix, endPrefix);
            }
        case PREFIX_TAG_STRING:
//            handleSuggestionForAddingEditing();
            break;
        }
        return null;
    }

    /**
     * Determine which {@code Suggestion} to return given the arguments and caret position
     * for the {@code FindCommand}
     */
    public Suggestion parseFindCommandSuggestion(Prefix startPrefix, Prefix endPrefix) {
//        if (arguments.length() != 0) {
//            handleSuggestionForFinding();
//        }
        return null;
    }

    /**
     * Determine which {@code Suggestion} to return given the arguments and caret position
     * for the {@code FindByTagCommand}
     */
    public Suggestion parseFindByTagCommandSuggestion(String commandWord, String arguments) {
        if (arguments.length() != 0) {
//            handleSuggestionForFinding();
        }
        return null;
    }

//    /**
//     * Updates the keywords stored in the class whenever there is a change on issue list in the storage.
//     */
//    public void update(Logic logic) {
//        this.logic = logic;
//        issueSuggestion.update(logic);
//        tagSuggestion.update(logic);
//    }

//    /**
//     * Analyses the input String and give suggestion for find and findtag command
//     */
//    private void handleSuggestionForFinding() {
//        String mainText = getText();
//        int startingIndex = mainText.indexOf(WHITESPACE_IDENTIFIER) + STRING_INDEX_ADJUSTMENT_WHITESPACE;
//        String text = startingIndex == -1 ? mainText.trim() : mainText.substring(startingIndex).trim();
//        LinkedList<String> searchResult = mainText.contains(WORD_TAG) ? tagSuggestion.evaluate(text)
//                : issueSuggestion.evaluate(text);
//        Suggestion suggestion = mainText.contains(WORD_TAG) ? tagSuggestion : issueSuggestion;
//        showWindow(searchResult, text, startingIndex, suggestion, DUMMY_STRING);
//    }
//
//    /**
//     * Analyses the input String and give suggestion for tags in add and edit command
//     */
//    private void handleSuggestionForAddingEditing() {
//        String cursorString = getText().substring(0, getCaretPosition());
//        if (cursorString.lastIndexOf(TAG_PREFIX_IDENTIFIER) == -1) {
//            return;
//        }
//        int startingIndex = cursorString.lastIndexOf(TAG_PREFIX_IDENTIFIER);
//        String afterText = getText().substring(getCaretPosition(), getText().length());
//        String substring = cursorString.substring(startingIndex, getCaretPosition());
//        startingIndex += STRING_INDEX_ADJUSTMENT_TAG_PREFIX;
//        String text = substring.contains(WHITESPACE_IDENTIFIER) ? DUMMY_STRING
//                : substring.replaceFirst(TAG_PREFIX_IDENTIFIER, DUMMY_STRING);
//        LinkedList<String> searchResult = tagSuggestion.evaluate(text);
//        showWindow(searchResult, text, startingIndex, tagSuggestion, afterText);
//    }
//
//    /**
//     * Handles the pop up window
//     * @param searchResult List of strings used to create the items
//     * @param text The context which user enters
//     * @param startingIndex position where the argument starts
//     * @param suggestion The Suggestion object being used
//     * @param afterText The command line string will be truncated into three parts when dealing with tag,
//     * it represents the last truncated part which is after the cursor position.
//     */
//    private void showWindow(LinkedList<String> searchResult, String text, int startingIndex,
//                            Suggestion suggestion, String afterText) {
//        if (searchResult.size() > 0 && text.length() > 0) {
//            //hide the suggestion window if the issue statement is already entered completely
//            if (searchResult.size() == 1 && searchResult.get(0).equals(text)) {
//                popUpWindow.hide();
//            } else {
//                showSuggestionWindow(searchResult, startingIndex, suggestion, afterText);
//            }
//        } else {
//            popUpWindow.hide();
//        }
//    }
}
