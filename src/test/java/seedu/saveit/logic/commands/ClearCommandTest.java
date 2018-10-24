package seedu.saveit.logic.commands;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import org.junit.Test;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.UserPrefs;

public class ClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptySaveIt_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.commitSaveIt();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySaveIt_success() {
        Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSaveIt(), new UserPrefs());
        expectedModel.resetData(new SaveIt());
        expectedModel.commitSaveIt();

        assertCommandSuccess(new ClearCommand(), model, commandHistory, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
