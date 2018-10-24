package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.StringUtil.arePrefixesValuePresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.RefactorTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.Tag;


/**
 * Parses input arguments and creates a new RefactorTagCommand object
 */
public class RefactorTagCommandParser implements Parser<RefactorTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RefactorTagCommand and returns an
     * RefactorTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RefactorTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer
                .tokenize(args, PREFIX_TAG, PREFIX_NEW_TAG);
        Tag newTag = new Tag(RefactorTagCommand.dummyTag);
        if (!arePrefixesValuePresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RefactorTagCommand.MESSAGE_USAGE));
        } else if (arePrefixesValuePresent(argMultimap, PREFIX_NEW_TAG)) {
            newTag = new Tag(argMultimap.getValue(PREFIX_NEW_TAG).get());
        }
        Tag oldTag = new Tag(argMultimap.getValue(PREFIX_TAG).get());
        return new RefactorTagCommand(oldTag, newTag);
    }
}
