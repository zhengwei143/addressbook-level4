package seedu.saveit.ui.suggestion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.saveit.logic.Logic;
import seedu.saveit.ui.AutoSuggestionManager;

/**
 * The suggestion component which stores and provides issue statement key words
 */
public class IssueNameAutoSuggestion implements AutoSuggestion {

    private Logic logic;
    private TreeSet<String> issueStatementSet;
    private List<String> issueKeyWords;
    private LinkedList<String> searchResult;

    public IssueNameAutoSuggestion(Logic logic) {
        this.logic = logic;
        this.issueStatementSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.issueKeyWords = new ArrayList<>();
        this.searchResult = new LinkedList<>();

        fillIssueKeyWords();
        addAllIssueKeyWord();
    }

    /**
     * Compares and match the keywords.
     */
    @Override
    public LinkedList<String> giveSuggestion(String text) {
        searchResult.clear();
        searchResult.addAll(issueStatementSet.subSet(text, text + Character.MAX_VALUE));
        return searchResult;
    }

    /**
     * Updates the keywords stored in the class.
     */
    @Override
    public void update(Logic logic) {
        this.logic = logic;
        fillIssueKeyWords();
        addAllIssueKeyWord();
    }

    @Override
    public EventHandler<ActionEvent>
        getItemHandler(AutoSuggestionManager manager, String previousText,
            String afterText, int initIndex, int selection) {
        String result = searchResult.get(selection);
        return actionEvent -> {
            manager.replaceText(previousText.substring(0, initIndex) + result);
            manager.moveTo(manager.getLength());
            manager.getWindow().hide();
        };
    }

    /**
     * Stores all the issue statements to the key word list
     */
    private void fillIssueKeyWords() {
        this.issueKeyWords.clear();
        logic.getFilteredAndSortedIssueList().forEach(issue -> this.issueKeyWords.add(issue.getStatement().issue));
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
}
