package seedu.saveit.logic.commands;

import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalPersons.getTypicalSaveIt;

import org.junit.Before;
import org.junit.Test;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.issue.Issue;
import seedu.saveit.testutil.PersonBuilder;

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
    public void execute_newPerson_success() {
        Issue validIssue = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
        expectedModel.addPerson(validIssue);
        expectedModel.commitSaveIt();

        assertCommandSuccess(new AddCommand(validIssue), model, commandHistory,
                String.format(AddCommand.MESSAGE_SUCCESS, validIssue), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Issue issueInList = model.getSaveIt().getPersonList().get(0);
        assertCommandFailure(new AddCommand(issueInList), model, commandHistory,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
