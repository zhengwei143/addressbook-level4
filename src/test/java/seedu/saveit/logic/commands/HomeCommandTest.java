package seedu.saveit.logic.commands;

import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.HomeCommand.MESSAGE_SUCCESS;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import org.junit.Test;

import seedu.saveit.commons.events.model.DirectoryChangedEvent;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.ui.testutil.EventsCollectorRule;

public class HomeCommandTest {
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSaveIt(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_home_success() {
        assertCommandSuccess(new HomeCommand(), model, commandHistory, MESSAGE_SUCCESS, expectedModel);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof DirectoryChangedEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 2);
        DirectoryChangedEvent lastEvent =
                (DirectoryChangedEvent) eventsCollectorRule.eventsCollector.getMostRecent();
        assertTrue(lastEvent.directory.isRootLevel());
    }
}
