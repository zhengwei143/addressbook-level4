package seedu.saveit.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import seedu.saveit.commons.events.ui.ShowHelpRequestEvent;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.ui.testutil.EventsCollectorRule;

import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

public class HelpCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        assertCommandSuccess(new HelpCommand(), model, commandHistory, SHOWING_HELP_MESSAGE, expectedModel);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowHelpRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
