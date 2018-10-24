package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RetrieveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RetrieveCommandParser implements Parser<RetrieveCommand> {

    @Override
    public RetrieveCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new RetrieveCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RetrieveCommand.MESSAGE_USAGE), pe);
        }
    }
}
