package seedu.saveit.logic.parser;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.SetPrimaryCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SetPrimaryCommand object
 */
public class SetPrimaryCommandParser implements Parser<SetPrimaryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SelectCommand
     * and returns an SelectCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetPrimaryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SetPrimaryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SetPrimaryCommand.MESSAGE_USAGE), pe);
        }
    }
}
