package seedu.address.logic.parser;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.IssueHasTagsPredicate;

/**
 * Parses input arguments and creates a new FindByTagCommand object
 */
public class FindByTagCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindByTagCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindByTagCommand(new IssueHasTagsPredicate(Arrays.asList(nameKeywords)));
    }

}
