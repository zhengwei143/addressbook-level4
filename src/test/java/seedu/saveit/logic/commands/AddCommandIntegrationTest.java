package seedu.saveit.logic.commands;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import org.junit.Before;
import org.junit.Test;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.testutil.IssueBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    }

    @Test
    public void execute_newIssue_success() {
        Issue validIssue = new IssueBuilder().build();

        Model expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.addIssue(validIssue);
        expectedModel.commitSaveIt();

        assertCommandSuccess(new AddCommand(validIssue), model, commandHistory,
                String.format(AddCommand.MESSAGE_ISSUE_SUCCESS, validIssue), expectedModel);
    }

    @Test
    public void execute_duplicateIssue_throwsCommandException() {
        Issue issueInList = model.getSaveIt().getIssueList().get(0);
        assertCommandFailure(new AddCommand(issueInList), model, commandHistory,
                AddCommand.MESSAGE_DUPLICATE_ISSUE);
    }

}
