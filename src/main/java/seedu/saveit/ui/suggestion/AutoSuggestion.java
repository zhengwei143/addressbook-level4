package seedu.saveit.ui.suggestion;

import java.util.LinkedList;

import seedu.saveit.logic.Logic;

/**
 *
 */
public interface AutoSuggestion {

    /**
     * Returns a list of suggestions to fill the autosuggestion box
     */
    LinkedList<String> giveSuggestion(String text);

    /**
     * Interfaces with the logic component's API to search the issues and
     * updates the values suggested accordingly
     */
    void update(Logic logic);
}
