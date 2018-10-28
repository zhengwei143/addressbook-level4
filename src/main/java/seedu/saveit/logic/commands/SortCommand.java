package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.IssueSort;

/**
 * Lists all persons in the saveIt to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_ALIAS = "sr";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort all issues with index numbers based on "
            + "the provided sort_type: freq (search frequency), chro (chronological), tag (tag names) .\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " freq";

    public static final String MESSAGE_SUCCESS = "Sorted issues by %s";

    private final IssueSort sort_type;

    public SortCommand(IssueSort sort_type) {
        this.sort_type = sort_type;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.sortIssues(sort_type);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, sort_type.getSortType()));
    }
}
