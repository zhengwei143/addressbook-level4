package seedu.saveit.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.CommandTestUtil.showIssueAtIndex;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_SECOND_ISSUE;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import org.junit.Ignore;
import org.junit.Test;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    @Ignore
    public void execute_validIndexUnfilteredList_success() {
        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST_ISSUE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ISSUE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, issueToDelete);

        ModelManager expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.deleteIssue(issueToDelete);
        expectedModel.commitSaveIt();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIssueList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);
    }

    @Test
    @Ignore
    public void execute_validIndexFilteredList_success() {
        showIssueAtIndex(model, INDEX_FIRST_ISSUE);

        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST_ISSUE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ISSUE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, issueToDelete);

        Model expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.deleteIssue(issueToDelete);
        expectedModel.commitSaveIt();
        showNoIssue(expectedModel);

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showIssueAtIndex(model, INDEX_FIRST_ISSUE);

        Index outOfBoundIndex = INDEX_SECOND_ISSUE;
        // ensures that outOfBoundIndex is still in bounds of saveit book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSaveIt().getIssueList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST_ISSUE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ISSUE);
        Model expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.deleteIssue(issueToDelete);
        expectedModel.commitSaveIt();

        // delete -> first issue deleted
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts saveit back to previous state and filtered issue list to show all issues
        expectedModel.undoSaveIt();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first issue deleted again
        expectedModel.redoSaveIt();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIssueList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        // execution failed -> saveit book state not added into model
        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX);

        // single saveit book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Issue} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted issue in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the issue object regardless of indexing.
     */
    @Test
    @Ignore
    public void executeUndoRedo_validIndexFilteredList_sameIssueDeleted() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ISSUE);
        Model expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());

        showIssueAtIndex(model, INDEX_SECOND_ISSUE);
        Issue issueToDelete = model.getFilteredIssueList().get(INDEX_FIRST_ISSUE.getZeroBased());
        expectedModel.deleteIssue(issueToDelete);
        expectedModel.commitSaveIt();

        // delete -> deletes second issue in unfiltered issue list / first issue in filtered issue list
        deleteCommand.execute(model, commandHistory);

        // undo -> reverts saveit back to previous state and filtered issue list to show all issues
        expectedModel.undoSaveIt();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(issueToDelete, model.getFilteredIssueList().get(INDEX_FIRST_ISSUE.getZeroBased()));
        // redo -> deletes same second issue in unfiltered issue list
        expectedModel.redoSaveIt();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_ISSUE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_ISSUE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_ISSUE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different issue -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIssue(Model model) {
        model.updateFilteredIssueList(p -> false);

        assertTrue(model.getFilteredIssueList().isEmpty());
    }
}
