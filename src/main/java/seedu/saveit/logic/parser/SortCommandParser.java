package seedu.saveit.logic.parser;

import java.util.Arrays;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.SortCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.issue.IssueSort;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (Arrays.asList(trimmedArgs.isEmpty()).size() > 1) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)
            );
        }

        return new SortCommand(new IssueSort(trimmedArgs));
    }
}
