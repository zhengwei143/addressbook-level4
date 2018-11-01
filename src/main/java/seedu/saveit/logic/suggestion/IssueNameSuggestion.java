package seedu.saveit.logic.suggestion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import seedu.saveit.logic.Logic;

/**
 * The suggestion component which stores and provides issue statement key words
 */
public class IssueNameSuggestion implements Suggestion {

    private Logic logic;
    private TreeSet<String> issueStatementSet;
    private List<String> issueKeyWords;
    private LinkedList<String> searchResult;

    public IssueNameSuggestion(Logic logic) {
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
    public SuggestionResult evaluate() {
        // TODO: FIX
        searchResult.clear();
//        searchResult.addAll(issueStatementSet.subSet(text, text + Character.MAX_VALUE));
        return null;
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
