package seedu.saveit.logic.suggestion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import seedu.saveit.logic.Logic;

/**
 * The suggestion component which stores and provides tag name key words
 */
public class TagNameSuggestion implements Suggestion {

    private Logic logic;
    private TreeSet<String> tagSet;
    private Set<String> tagKeyWords;
    private LinkedList<String> searchResult;

    public TagNameSuggestion(Logic logic) {
        this.logic = logic;
        this.tagSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.tagKeyWords = new HashSet<>();
        this.searchResult = new LinkedList<>();
        fillTagKeyWords();
        addAllTagKeyWord();
    }

    /**
     * Compares and match the keywords.
     */
    @Override
    public SuggestionResult evaluate() {
        // TODO: FIX
        searchResult.clear();
//        searchResult.addAll(tagSet.subSet(text, text + Character.MAX_VALUE));
        return null;
    }

    /**
     * Adds all the stored key words to the treeSet
     */
    private void addAllTagKeyWord() {
        tagSet.clear();
        for (String str : tagKeyWords) {
            tagSet.add(str);
        }
    }

    /**
     * Stores all the tag names to the key word set
     */
    private void fillTagKeyWords() {
        this.tagKeyWords.clear();
        logic.getFilteredAndSortedIssueList().forEach(issue -> issue.getTags()
                .forEach(tag -> this.tagKeyWords.add(tag.tagName)));
    }
}
