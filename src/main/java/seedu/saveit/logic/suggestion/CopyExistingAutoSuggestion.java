package seedu.saveit.logic.suggestion;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION_STRING;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT_STRING;

import java.util.Arrays;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.Logic;
import seedu.saveit.model.Issue;
import seedu.saveit.logic.AutoSuggestionManager;

/**
 * Prompts the user with a suggestion to copy and paste the existing text value
 *  in any field of the object that is being edited
 *  e.g. {@code Description} of {@code Issue}
 *  e.g. {@code Remark} of {@code Solution}
 */
public class CopyExistingAutoSuggestion implements AutoSuggestion {

    private static final String COPY_EXISTING_PROMPT = "Copy Existing...";

    private Logic logic;
    private Index index;
    private Issue issue;
    private String identifier;
    private LinkedList<String> suggestions =
            new LinkedList<>(Arrays.asList(COPY_EXISTING_PROMPT));

    public CopyExistingAutoSuggestion(Logic logic, Index index) {
        this.logic = logic;
        this.index = index;
        this.identifier = "";
        this.issue = logic.getFilteredAndSortedIssueList().get(index.getZeroBased());
    }

    @Override
    public LinkedList<String> giveSuggestion(String identifier) {
        this.identifier = identifier;
        return suggestions;
    }

    @Override
    public void update(Logic logic) {
        this.logic = logic;
    }

    @Override
    public EventHandler<ActionEvent>
        getItemHandler(AutoSuggestionManager manager, String previousText,
            String afterText, int initIndex, int selection) {
        String result = getValueFromIdentifier(identifier);
        return actionEvent -> {
            manager.replaceText(previousText.substring(0, initIndex) + result);
            manager.moveTo(manager.getLength());
            manager.getWindow().hide();
        };
    }

    /**
     * Get value based on identifier
     */
    private String getValueFromIdentifier(String identifier) {
        switch (identifier) {

        case PREFIX_DESCRIPTION_STRING:
            return issue.getDescription().value;

        case PREFIX_STATEMENT_STRING:
            return issue.getStatement().issue;

        default:
            return "";
        }
    }
}
