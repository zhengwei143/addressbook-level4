package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK_STRING;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK_STRING;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.Solution;

/**
 * Adds an issue to the saveIt.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_ALIAS = "a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an issue to the saveIt. "
            + "Parameters: "
            + PREFIX_STATEMENT + "ISSUE_STATEMENT "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TAG + "algorithm "
            + PREFIX_TAG + "java\n"
            + "_____________________________\n"
            + COMMAND_WORD
            + ": Adds a solution to a existing issue. "
            + "Parameters: "
            + PREFIX_SOLUTION_LINK_STRING + "SOLUTION_LINK"
            + PREFIX_REMARK_STRING + "SOLUTION_REMARK";
    public static final String MESSAGE_ISSUE_SUCCESS = "New issue added: %1$s";
    public static final String MESSAGE_DUPLICATE_ISSUE = "This issue already exists in the saveIt";
    public static final String DUMMY_STATEMENT = "dummyStatement";
    public static final String DUMMY_DESCRIPTION = "dummyDescription";

    private static final String MESSAGE_FAILED_ISSUE =
            "Issue has to be selected first before adding solution";
    private static final String MESSAGE_WRONG_DIRECTORY = "Wrong directory, please check!";
    private static final String MESSAGE_SOLUTION_SUCCESS = "New solution added: %1$s";
    private boolean addSolution;
    private final Solution solutionToBeAdded;
    private final Issue toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Issue}
     */
    public AddCommand(Issue issue) {
        if (issue.getStatement().issue.equals(DUMMY_STATEMENT) && issue.getDescription().value
                .equals(DUMMY_DESCRIPTION)) {
            addSolution = true;
            assert (issue.getSolutions().size() == 1);
            solutionToBeAdded = issue.getSolutions().get(0);
            toAdd = null;
        } else {
            addSolution = false;
            requireNonNull(issue);
            toAdd = issue;
            solutionToBeAdded = null;
        }
    }

    /**
     * Add a solution to a existing issue in the issue list
     */
    private void addSolutionToIssue(Model model, int index) {
        List<Issue> lastShownList = model.getFilteredAndSortedIssueList();
        Issue originalIssue = lastShownList.get(index - 1);
        List<Solution> newSolutionList = new ArrayList<>(originalIssue.getSolutions());
        newSolutionList.add(solutionToBeAdded);
        Issue newIssue = new Issue(originalIssue.getStatement(), originalIssue.getDescription(),
                newSolutionList, originalIssue.getTags(), originalIssue.getFrequency());
        model.updateIssue(originalIssue, newIssue);
        model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
        model.commitSaveIt();
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        int issueIndex = model.getCurrentDirectory().getIssue();
        if (addSolution) {
            if (!model.getCurrentDirectory().isRootLevel()) {
                addSolutionToIssue(model, issueIndex);
                return new CommandResult(String.format(MESSAGE_SOLUTION_SUCCESS, solutionToBeAdded));
            } else {
                throw new CommandException(MESSAGE_FAILED_ISSUE);
            }
        }

        if (!model.getCurrentDirectory().isRootLevel()) {
            throw new CommandException(MESSAGE_WRONG_DIRECTORY);
        }

        if (model.hasIssue(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ISSUE);
        }

        model.addIssue(toAdd);
        model.commitSaveIt();
        addSolution = false;
        return new CommandResult(String.format(MESSAGE_ISSUE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
