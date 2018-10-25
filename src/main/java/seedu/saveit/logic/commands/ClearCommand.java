package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Model;
import seedu.saveit.model.SaveIt;

/**
 * Clears the saveIt
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_ALIAS = "c";

    public static final String MESSAGE_SUCCESS = "SaveIt has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.resetData(new SaveIt());
        model.commitSaveIt();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
