package seedu.saveit.logic.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;

import static seedu.saveit.logic.commands.CommandTestUtil.*;
import static seedu.saveit.testutil.TypicalPersons.getTypicalSaveIt;

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstPerson(model);
        deleteFirstPerson(model);
        model.undoSaveIt();
        model.undoSaveIt();

        deleteFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
        expectedModel.undoSaveIt();
        expectedModel.undoSaveIt();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoSaveIt();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoSaveIt();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
