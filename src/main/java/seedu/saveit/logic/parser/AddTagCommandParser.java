package seedu.saveit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.AddTagCommand;
import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.RefactorTagCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.issue.Tag;

public class AddTagCommandParser implements Parser<AddTagCommand> {

    private Set<Tag>  tagList;

    /**
     * Parses the given {@code String} of arguments in the context of the RefactorTagCommand and returns an
     * RefactorTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer
                .tokenize(args, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } else {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RefactorTagCommand.MESSAGE_USAGE));
        }
        return new AddTagCommand(index, tagList);
    }
}
