package seedu.saveit.ui.suggestion;

import java.util.Arrays;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.saveit.logic.Logic;
import seedu.saveit.ui.AutoSuggestionManager;

/**
 * Prompts the user with a suggestion to copy and paste the existing text value
 *  in any field of the object that is being edited
 *  e.g. {@code Description} of {@code Issue}
 *  e.g. {@code Remark} of {@code Solution}
 */
public class CopyExistingAutoSuggestion implements AutoSuggestion {

    private static final String COPY_EXISTING_PROMPT = "Copy Existing...";

    private Logic logic;
    private LinkedList<String> suggestions =
            new LinkedList<String>(Arrays.asList(COPY_EXISTING_PROMPT));

    public CopyExistingAutoSuggestion(Logic logic) {
        this.logic = logic;
    }

    @Override
    public LinkedList<String> giveSuggestion(String text) {
        return suggestions;
    }

    @Override
    public void update(Logic logic) {

    }

    @Override
    public EventHandler<ActionEvent>
        getItemHandler(AutoSuggestionManager manager, String previousText, int initIndex, String result) {
        return actionEvent -> {
            manager.replaceText(previousText.substring(0, initIndex) + result);
            manager.moveTo(manager.getLength());
            manager.getWindow().hide();
        };
    }
}
