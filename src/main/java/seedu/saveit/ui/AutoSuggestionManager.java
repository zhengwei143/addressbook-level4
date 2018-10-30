package seedu.saveit.ui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import seedu.saveit.logic.Logic;
import seedu.saveit.ui.suggestion.AutoSuggestion;
import seedu.saveit.ui.suggestion.IssueNameAutoSuggestion;
import seedu.saveit.ui.suggestion.TagNameAutoSuggestion;

/**
 * The TextField component which supports auto key word suggestion
 */
public class AutoSuggestionManager extends InlineCssTextArea {

    public static final String WORD_FIND = "find";
    private static final String WORD_TAG = "tag";
    private static final String WORD_ADD = "add";
    private static final String WORD_EDIT = "edit";

    private static final String WHITESPACE_IDENTIFIER = " ";
    private static final String TAG_PREFIX_IDENTIFIER = "t/";
    //To get substring after whitespace, substring starting index has to be increased by 1
    private static final int STRING_INDEX_ADJUSTMENT_WHITESPACE = 1;
    //To get substring after tag prefix, substring starting index has to be increased by 2
    private static final int STRING_INDEX_ADJUSTMENT_TAG_PREFIX = 2;
    private static final int MAX_NUMBER = 5;
    public ContextMenu popUpWindow;
    private String[] wordSet = {WORD_ADD, WORD_EDIT, WORD_FIND, WORD_TAG};

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
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                String newValue) {
                if (getText().length() == 0 || !Arrays.stream(wordSet).parallel().anyMatch(getText()::contains)) {
                    popUpWindow.hide();
                } else {
                    if (getText().length() > WORD_FIND.length() && getText()
                        .substring(0, WORD_FIND.length()).contains(WORD_FIND)) { //find or findtag
                        showResult(WHITESPACE_IDENTIFIER);
                    } else { //add or edit
                        showResult(TAG_PREFIX_IDENTIFIER);
                    }
                }
            }
        });
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
     */
    private void showSuggestionWindow(LinkedList<String> searchResult,
            int startingIndex, AutoSuggestion suggestion) {
        int count = Math.min(searchResult.size(), MAX_NUMBER);
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
        popUpWindow.show(this, Side.BOTTOM, (double) getCaretPosition() * 8, 0);
    }

    /**
     * Makes the popup window get ready to get focused before next showing
     */
    private void getFocused() {
        popUpWindow.show(AutoSuggestionManager.this, Side.BOTTOM, (double) getCaretPosition() * 8, 0);
        popUpWindow.hide();
    }
}
