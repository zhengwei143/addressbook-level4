package seedu.saveit.logic.commands;

import org.junit.Rule;
import org.junit.Test;
import seedu.saveit.commons.events.ui.ExitAppRequestEvent;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.ui.testutil.EventsCollectorRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

public class ExitCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_exit_success() {
        CommandResult result = new ExitCommand().execute(model, commandHistory);
        assertEquals(MESSAGE_EXIT_ACKNOWLEDGEMENT, result.feedbackToUser);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ExitAppRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
