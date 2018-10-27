package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.IssueSort;

/**
 * Lists all issues in the saveIt to the user.
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

    public ListCommand(IssueSort sortType) {
        this.sort_type = sortType;
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
