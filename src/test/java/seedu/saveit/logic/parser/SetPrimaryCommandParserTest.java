package seedu.saveit.logic.parser;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_SOLUTION;

import org.junit.Test;

import seedu.saveit.logic.commands.SetPrimaryCommand;

public class SetPrimaryCommandParserTest {
    private SetPrimaryCommandParser parser = new SetPrimaryCommandParser();

    @Test
    public void parse_validArgs_returnsStarCommand() {
        assertParseSuccess(parser, "1", new SetPrimaryCommand(INDEX_FIRST_SOLUTION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPrimaryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPrimaryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPrimaryCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetPrimaryCommand.MESSAGE_USAGE));
    }
}
