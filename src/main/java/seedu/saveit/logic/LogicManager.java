package seedu.saveit.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.saveit.commons.core.ComponentManager;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.SaveItParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.Issue;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final CommandHistory history;
    private final SaveItParser saveItParser;

    public LogicManager(Model model) {
        this.model = model;
        history = new CommandHistory();
        saveItParser = new SaveItParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = saveItParser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Issue> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
