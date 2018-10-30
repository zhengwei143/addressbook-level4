package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.Solution;

import java.util.List;

public class StarCommand extends Command {
    public static final String COMMAND_WORD = "star";
    public static final String COMMAND_ALIAS = "*";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Star solution by the index number used in the displayed solution list\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Stared solution: %1$s";

    private final Index index;

    public StarCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        Directory currentDirectory = model.getCurrentDirectory();
        List<Issue> lastShownIssueList = model.getFilteredAndSortedIssueList();

        if (currentDirectory.isIssueLevel()) {
            Issue issueSelected = lastShownIssueList.get(currentDirectory.getIssue() - 1);
            List<Solution> solutionList = issueSelected.getSolutions();
            int oneBasedIndex = index.getOneBased();
            if (oneBasedIndex < solutionList.size()) {
                Solution solutionToStar = solutionList.get(oneBasedIndex);

            }
        }
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredIssueList().size()));
    }
}
