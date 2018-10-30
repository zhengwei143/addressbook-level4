package seedu.saveit.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.saveit.commons.core.ComponentManager;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.DangerCommand;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.SaveItParser;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.Solution;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    public static final String ASK_FOR_CONFIRMATION = "Are you sure to %s ? Please enter Yes(Y) to confirm.";
    public static final String CONFIRMATION_FAILED = "Didn't %s.";
    public static final String CONFIRM_WORD = "Yes";
    public static final String CONFIRM_ALIAS = "Y";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final CommandHistory history;
    private final SaveItParser saveItParser;
    private DangerCommand bufferedCommand;

    public LogicManager(Model model) {
        this.model = model;
        history = new CommandHistory();
        saveItParser = new SaveItParser();
        bufferedCommand = null;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            // handle buffered command before executing other command
            if (requireConfirmationBeforeExecution(bufferedCommand)) {
                return handleBufferedCommand(commandText);
            }

            Command command = saveItParser.parseCommand(commandText);
            if (requireConfirmationBeforeExecution(command)) {
                return setBufferedCommand((DangerCommand) command);
            } else {
                return command.execute(model, history);
            }
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Issue> getFilteredAndSortedIssueList() {
        return model.getFilteredAndSortedIssueList();
    }

    @Override
    public ObservableList<Solution> getFilteredSolutionList() {
        ObservableList<Solution> k = model.getFilteredSolutionList();
        return k;
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }

    /**
     * Check if a buffered command can be executed based on {@code commandText}.
     * Update {@code bufferedCommand} to null.
     */
    private CommandResult handleBufferedCommand(String commandText) throws CommandException, ParseException {
        if (commandText.equals(CONFIRM_WORD) || commandText.equals(CONFIRM_ALIAS)) {
            return executeBufferedCommand();
        } else {
            String bufferedCommandType = getBufferedCommandType();
            resetBufferedCommand();
            return new CommandResult(String.format(CONFIRMATION_FAILED, bufferedCommandType));
        }
    }

    /**
     * Check if a command requires confirmation before being executed.
     */
    private boolean requireConfirmationBeforeExecution(Command command) {
        return command instanceof DangerCommand;
    }

    private CommandResult executeBufferedCommand() throws CommandException, ParseException {
        CommandResult commandResult = bufferedCommand.execute(model, history);
        resetBufferedCommand();
        return commandResult;
    }

    private CommandResult setBufferedCommand(DangerCommand command) {
        bufferedCommand = command;
        return new CommandResult(String.format(ASK_FOR_CONFIRMATION, getBufferedCommandType()));
    }

    private void resetBufferedCommand() {
        bufferedCommand = null;
    }

    private String getBufferedCommandType() {
        return bufferedCommand.getCommandWord();
    }
}
