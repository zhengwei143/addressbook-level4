package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

public class RetrieveCommand extends Command{

    public static final String COMMAND_WORD = "retrieve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the solution which is identified by the index number in the selected issue and copy it to the system clipboard.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_RETRIEVE_LINK_SUCCESS = "Solution link no. %1$s is pasted to the clipboard.";

    private final Index targetedIndex;

    public RetrieveCommand(Index targetedIndex) {
        this.targetedIndex = targetedIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history)
            throws CommandException, ParseException {
        requireNonNull(model);

        return null;
    }
}
