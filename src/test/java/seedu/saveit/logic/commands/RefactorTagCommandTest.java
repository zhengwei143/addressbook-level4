package seedu.saveit.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.commons.core.Messages.MESSAGE_ISSUES_LISTED_OVERVIEW;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_PYTHON;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.CommandTestUtil.showIssueAtIndex;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_SECOND_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_THIRD_ISSUE;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.logic.commands.EditCommand.EditIssueDescriptor;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.issue.IssueContainsKeywordsPredicate;
import seedu.saveit.model.issue.IssueSort;
import seedu.saveit.model.issue.Tag;
import seedu.saveit.testutil.EditIssueDescriptorBuilder;
import seedu.saveit.testutil.IssueBuilder;

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
