package seedu.saveit.logic.commands;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import org.junit.Test;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.issue.Tag;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RefactorTagCommand}.
 */
public class RefactorTagCommandTest {

    private Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validRefactorTagWithoutNewTag_success() throws Exception {
        Tag oldTag = new Tag(VALID_TAG_SYNTAX);
        RefactorTagCommand refactorTagCommand = new RefactorTagCommand(oldTag);

        String expectedMessage = String.format(RefactorTagCommand.MESSAGE_REFACTOR_TAG_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.refactorTag(oldTag);
        expectedModel.commitSaveIt();

        assertCommandSuccess(refactorTagCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRefactorTagWithNewTag_success() throws Exception {
        Tag oldTag = new Tag(VALID_TAG_SYNTAX);
        Tag newTag = new Tag(VALID_TAG_UI);
        RefactorTagCommand refactorTagCommand = new RefactorTagCommand(oldTag, newTag);

        String expectedMessage = String.format(RefactorTagCommand.MESSAGE_REFACTOR_TAG_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.refactorTag(oldTag, newTag);
        expectedModel.commitSaveIt();

        assertCommandSuccess(refactorTagCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    // TODO: Needs to change it to failure
    @Test
    public void execute_validRefactorTagButNotHave_success() throws Exception {
        Tag oldTag = new Tag("test");
        RefactorTagCommand refactorTagCommand = new RefactorTagCommand(oldTag);

        String expectedMessage = String.format(RefactorTagCommand.MESSAGE_REFACTOR_TAG_FAILURE);

        ModelManager expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.refactorTag(oldTag);
        expectedModel.commitSaveIt();

        assertCommandSuccess(refactorTagCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
