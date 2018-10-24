package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.issue.IssueSort;

/**
 * Lists all persons in the saveIt to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_ALIAS = "l";

    public static final String DEFAULT_SORT_TYPE = "chro";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all issues with index numbers based on "
            + "the provided sort_type: freq (search frequency), chro (chronological), tag (tag names) .\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " freq";

    public static final String MESSAGE_SUCCESS = "Listed all issues";

    private final IssueSort sort_type;

    public ListCommand(IssueSort sort_type) {
        this.sort_type = sort_type;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
        model.sortIssues(sort_type);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getSortedIssueList().size()));
    }
}
