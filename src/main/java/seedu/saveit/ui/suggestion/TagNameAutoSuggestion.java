package seedu.saveit.ui.suggestion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import seedu.saveit.logic.Logic;

public class TagNameAutoSuggestion {

    private Logic logic;
    private static TreeSet<String> tagSet;
    private Set<String> tagKeyWords;

    public TagNameAutoSuggestion(Logic logic) {
        this.logic = logic;
        this.tagSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        this.tagKeyWords = new HashSet<>();
        fillTagKeyWords();
        addAllTagKeyWord();
    }

    public LinkedList<String> giveSuggestion(String text) {
        LinkedList<String> searchResult = new LinkedList<>();
        searchResult.addAll(tagSet.subSet(text, text + Character.MAX_VALUE));
        return searchResult;
    }

    public void update(Logic logic) {
        this.logic = logic;
        fillTagKeyWords();
        addAllTagKeyWord();
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
        logic.getFilteredIssueList().forEach(issue -> issue.getTags()
                .forEach(tag -> this.tagKeyWords.add(tag.tagName)));
    }
}
