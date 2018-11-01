package seedu.saveit.ui.suggestion;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.saveit.logic.Logic;
import seedu.saveit.ui.AutoSuggestionManager;

/**
 * The suggestion component which stores and provides tag name key words
 */
public class TagNameAutoSuggestion implements AutoSuggestion {

    private Logic logic;
    private TreeSet<String> tagSet;
    private Set<String> tagKeyWords;
    private LinkedList<String> searchResult;

    public TagNameAutoSuggestion(Logic logic) {
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
    public LinkedList<String> giveSuggestion(String text) {
        searchResult.clear();
        searchResult.addAll(tagSet.subSet(text, text + Character.MAX_VALUE));
        return searchResult;
    }

    /**
     * Updates the keywords stored in the class.
     */
    @Override
    public void update(Logic logic) {
        this.logic = logic;
        fillTagKeyWords();
        addAllTagKeyWord();
    }

    @Override
    public EventHandler<ActionEvent>
        getItemHandler(AutoSuggestionManager manager, String previousText,
            String afterText, int initIndex, int selection) {
        String result = searchResult.get(selection);
        return actionEvent -> {
            manager.replaceText(previousText.substring(0, initIndex) + result + afterText);
            manager.moveTo(afterText.equals("")? manager.getLength() : initIndex + result.length());
            manager.getWindow().hide();
        };
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
