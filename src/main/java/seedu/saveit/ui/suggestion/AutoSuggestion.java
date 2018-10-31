package seedu.saveit.ui.suggestion;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.saveit.logic.Logic;
import seedu.saveit.ui.AutoSuggestionManager;

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

    EventHandler<ActionEvent>
        getItemHandler(AutoSuggestionManager manager, String previousText, int initIndex, int selection);

}
