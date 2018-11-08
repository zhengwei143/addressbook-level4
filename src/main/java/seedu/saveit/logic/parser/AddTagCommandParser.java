package seedu.saveit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX;
import static seedu.saveit.commons.util.StringUtil.arePrefixesNotPresent;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.LinkedHashSet;
import java.util.Set;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.logic.commands.AddTagCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.issue.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    private Set<Tag> tagList;
    private final int indexLowerLimit = 1;


    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand and returns an AddTagCommand
     * object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Set<Index> index = new LinkedHashSet<>();

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        try {
            String indexToCheck = argMultimap.getPreamble();
            if (indexToCheck.contains("-")) {
                addRangeIndex(index, indexToCheck);
            } else if (indexToCheck.contains(" ")) {
                addDiscreteIndex(index, indexToCheck);
            } else {
                index.add(ParserUtil.parseIndex(indexToCheck));
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent() && arePrefixesNotPresent(args,
            PREFIX_SOLUTION_LINK, PREFIX_REMARK, PREFIX_STATEMENT, PREFIX_DESCRIPTION, PREFIX_NEW_TAG)) {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } else {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }
        return new AddTagCommand(index, tagList);
    }

    /**
     * add discrete values to the index set
     *
     * @param index index set
     * @param indexToCheck the issue index user want to add tags
     */
    private void addDiscreteIndex(Set<Index> index, String indexToCheck) throws ParseException {
        String[] indexNumber = indexToCheck.split(" ");
        try {
            for (int i = 0; i < indexNumber.length; i++) {
                addIndex(index, Integer.parseInt(indexNumber[i]));
            }
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

    }

    /**
     * add range values to the index set
     *
     * @param index index set
     * @param indexToCheck the issue index user want to add tags
     */
    private void addRangeIndex(Set<Index> index, String indexToCheck) throws ParseException {
        String[] indexRange = indexToCheck.split("-");
        int rangeStart;
        int rangeEnd;
        try {
            rangeStart = Integer.parseInt(indexRange[0]);
            rangeEnd = Integer.parseInt(indexRange[1]);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }
        checkLowerBound(rangeStart);

        if (rangeEnd < rangeStart) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        for (int i = rangeStart; i <= rangeEnd; i++) {
            addIndex(index, i);
        }
    }

    /**
     * add the issue index to the index set.
     *
     * @param index index set for the issue that users want to add tags.
     * @param indexToAdd the index that user want to add tags.
     */
    private void addIndex(Set<Index> index, int indexToAdd) throws ParseException {
        checkLowerBound(indexToAdd);

        String toAdd = String.valueOf(indexToAdd);
        try {
            Index addIndex = ParserUtil.parseIndex(toAdd);
            index.add(addIndex);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }
    }

    /**
     * check if the index is smaller than indexLowerLimit.
     *
     * @throws ParseException throw exception if index is smaller than indexLowerLimit.
     */
    private void checkLowerBound(int indexToAdd) throws ParseException {
        if (indexToAdd < indexLowerLimit) {
            throw new ParseException(String
                .format(MESSAGE_INVALID_ISSUE_DISPLAYED_INDEX, Messages.MESSAGE_INVALID_DISPLAYED_INDEX));
        }
    }
}
