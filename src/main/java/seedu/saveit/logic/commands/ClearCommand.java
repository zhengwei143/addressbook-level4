package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "SaveIt has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.resetData(new SaveIt());
        model.commitSaveIt();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
