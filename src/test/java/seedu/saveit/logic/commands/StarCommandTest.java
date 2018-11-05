package seedu.saveit.logic.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.*;
import seedu.saveit.model.issue.Solution;

import java.util.List;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_SOLUTION;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

public class StarCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory;
    Issue issueSelected;
    List<Solution> solutionList;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
        expectedModel = new ModelManager(new SaveIt(model.getSaveIt()), new UserPrefs());
        commandHistory = new CommandHistory();
    }

    @Test
    public void execute_validIndexRootLevel_failure() {
        setUpRootLevel();
        assertExecutionFailure(INDEX_FIRST_SOLUTION, Messages.MESSAGE_WRONG_DIRECTORY);
    }

    @Test
    public void execute_validIndexIssueLevel_success() {
        setUpIssueLevel();
        int lastIssueIndex = solutionList.size();
        assertExecutionSuccess(INDEX_FIRST_SOLUTION);
        assertExecutionSuccess(Index.fromOneBased(lastIssueIndex));
    }

    @Test
    public void execute_invalidIndexIssueLevel_failure() {
        setUpIssueLevel();
        int lastSolutionIndex = solutionList.size();
        assertExecutionFailure(Index.fromZeroBased(lastSolutionIndex), Messages.MESSAGE_INVALID_SOLUTION_DISPLAYED_INDEX);
    }

    /**
     * Reset the directory of model and expected to Issue Level.
     */
    private void setUpIssueLevel() {
        Index selectedIssueOneBasedIndex = Index.fromOneBased(4);
        model.resetDirectory(Directory.formDirectory(selectedIssueOneBasedIndex.getOneBased(), 0));
        expectedModel.resetDirectory(Directory.formDirectory(selectedIssueOneBasedIndex.getOneBased(), 0));
        issueSelected = expectedModel.getFilteredAndSortedIssueList().get(selectedIssueOneBasedIndex.getZeroBased());
        solutionList = expectedModel.getFilteredSolutionList();
    }

    /**
     * Reset the directory of model and expected to Root Level.
     */
    private void setUpRootLevel() {
        model.resetDirectory(Directory.formDirectory(0, 0));
        expectedModel.resetDirectory(Directory.formDirectory(0, 0));
        issueSelected = null;
        solutionList = null;
    }

    /**
     * Executes a {@code StarCommand} with the given {@code index}.
     */
    private void assertExecutionSuccess(Index index) {
        StarCommand starCommand = new StarCommand(index);
        Solution staredSolution = solutionList.get(index.getZeroBased());
        expectedModel.updateIssue(issueSelected, issueSelected.updatePrimarySolution(solutionList, index.getZeroBased()));
        expectedModel.commitSaveIt();
        String expectedMessage = String.format(StarCommand.MESSAGE_SUCCESS, staredSolution);

        assertCommandSuccess(starCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    /**
     * Executes a {@code StarCommand} with the given {@code index}, and checks that a {@code CommandException}
     * is thrown with the {@code expectedMessage}.
     */
    private void assertExecutionFailure(Index index, String expectedMessage) {
        StarCommand starCommand = new StarCommand(index);
        assertCommandFailure(starCommand, model, commandHistory, expectedMessage);
    }
}
