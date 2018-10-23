package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;
import static seedu.address.testutil.TypicalPersons.getTypicalSaveIt;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.issue.IssueSort;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
        expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
    }

    @Test
    @Ignore
    public void execute_listIsNotFiltered_showsSameList() throws ParseException {
        assertCommandSuccess(new ListCommand(new IssueSort("")), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    @Ignore
    public void execute_listIsFiltered_showsEverything() throws ParseException{
        showPersonAtIndex(model, INDEX_FIRST_ISSUE);
        assertCommandSuccess(new ListCommand(new IssueSort("freq")), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
