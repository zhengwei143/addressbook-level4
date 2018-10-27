package seedu.saveit.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import seedu.saveit.logic.Logic;

/**
 * The TextField component which supports auto key word suggestion
 */
public class AutoSuggestedManager extends InlineCssTextArea {

    public static final String WORD_FIND = "find";
    public static final String WORD_TAG = "tag";

    private static ContextMenu popUpWindow;
    private static TreeSet<String> issueStatementSet;
    private static TreeSet<String> tagSet;
    private static Logic logic;
    private List<String> issueKeyWords = new ArrayList<>();
    private Set<String> tagKeyWords = new HashSet<>();


    public AutoSuggestedManager() {
        super();
    }

    /**
     * Adds new listener to the text field to handle the key suggestion
     */
    public void initialise(Logic logic) {
        this.logic = logic;

        fillIssueKeyWords();
        issueStatementSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        addAllIssueKeyWord();

        fillTagKeyWords();
        tagSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        addAllTagKeyWord();

        popUpWindow = new ContextMenu();
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (getText().length() == 0 || !getText().contains(WORD_FIND)) {
                    popUpWindow.hide();
                } else {
                    if (getText().contains(WORD_TAG)) {
                        showResult(AutoSuggestedManager.this, tagSet);
                    } else {
                        showResult(AutoSuggestedManager.this, issueStatementSet);
                    }
                }
            }
        });
    }

    /**
     * Updates the keywords stored in the class whenever there is a change on issue list in the storage.
     */
    public void update(Logic logic) {
        this.logic = logic;
        fillIssueKeyWords();
        fillTagKeyWords();
        addAllIssueKeyWord();
        addAllTagKeyWord();
    }

    /**
     * Analyses the input string and suggests the key words
     */
    private void showResult(InlineCssTextArea textField, TreeSet<String> matchSet) {
        String mainText = textField.getText();
        String text;
        int whiteSpaceIndex = mainText.indexOf(" ") + 1;
        if (whiteSpaceIndex != -1) {
            text = mainText.substring(whiteSpaceIndex, mainText.length()).trim();
        } else {
            text = mainText.trim();
        }
        LinkedList<String> searchResult = new LinkedList<>();
        searchResult.addAll(matchSet.subSet(text, text + Character.MAX_VALUE));
        if (searchResult.size() > 0 && text.length() > 0) {
            //hide the suggestion window if the issue statement is already entered completely
            if (searchResult.size() == 1 && searchResult.get(0).equals(text)) {
                popUpWindow.hide();
            } else {
                int maxNum = 5;
                int count = Math.min(searchResult.size(), maxNum);
                List<CustomMenuItem> menuItems = new LinkedList<>();
                for (int i = 0; i < count; i++) {
                    final String result = searchResult.get(i);
                    final String previousText = textField.getText();
                    Label entryLabel = new Label(result);
                    textField.requestFocus();
                    CustomMenuItem item = new CustomMenuItem(entryLabel, true);
                    int initIndex = whiteSpaceIndex;
                    item.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            textField.replaceText(previousText.substring(0, initIndex) + result);
                            textField.moveTo(textField.getLength());
                            popUpWindow.hide();
                        }
                    });
                    menuItems.add(item);
                }
                popUpWindow.getItems().clear();
                popUpWindow.getItems().addAll(menuItems);
                getFocused();
                popUpWindow.show(textField, Side.BOTTOM, (double) textField.getCaretPosition() * 8, 0);
            }
        } else {
            popUpWindow.hide();
        }
    }

    /**
     * Adds all the stored key words to the treeSet
     */
    private void addAllIssueKeyWord() {
        issueStatementSet.clear();
        for (String str : issueKeyWords) {
            issueStatementSet.add(str);
        }
    }

    /**
     * Adds all the stored key words to the treeSet
     */
    private void addAllTagKeyWord() {
        tagSet.clear();
        for (String str: tagKeyWords) {
            tagSet.add(str);
        }
    }

    /**
     * Stores all the issue statements to the key word list
     */
    private void fillIssueKeyWords() {
        this.issueKeyWords.clear();
        logic.getFilteredIssueList().forEach(issue -> this.issueKeyWords.add(issue.getStatement().issue));
    }

    /**
     * Stores all the tag names to the key word set
     */
    private void fillTagKeyWords() {
        this.tagKeyWords.clear();
        logic.getFilteredIssueList().forEach(issue -> issue.getTags().
                forEach(tag -> this.tagKeyWords.add(tag.tagName)));
    }

    /**
     * Makes the popup window get ready to get focused before next showing
     */
    private void getFocused() {
        popUpWindow.show(AutoSuggestedManager.this, Side.BOTTOM, (double) AutoSuggestedManager
                .this.getCaretPosition() * 8, 0);
        popUpWindow.hide();
    }
}
