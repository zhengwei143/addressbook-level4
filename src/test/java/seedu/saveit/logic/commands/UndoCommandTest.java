package seedu.saveit.logic.commands;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.saveit.testutil.TypicalPersons.getTypicalSaveIt;

import org.junit.Before;
import org.junit.Test;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstPerson(model);
        deleteFirstPerson(model);

        deleteFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in model
        expectedModel.undoSaveIt();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // single undoable state in model
        expectedModel.undoSaveIt();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // no undoable states in model
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}
