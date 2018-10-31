package seedu.saveit.logic.parser;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.CommandTestUtil.DESCRIPTION_DESC_C;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.logic.commands.RefactorTagCommand.MESSAGE_USAGE;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.saveit.logic.commands.RefactorTagCommand;
import seedu.saveit.model.issue.Tag;

public class RefactorTagCommandParserTest {

    private RefactorTagCommandParser parser = new RefactorTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnRefactorTagCommand() {
        RefactorTagCommand expectedRefactorTagCommand =
            new RefactorTagCommand(new Tag(VALID_TAG_UI), new Tag(VALID_TAG_SYNTAX));

        assertParseSuccess(parser, TAG_DESC_UI + VALID_TAG_SYNTAX, expectedRefactorTagCommand);

        // different order
        assertParseSuccess(parser, TAG_DESC_SYNTAX + TAG_DESC_UI, expectedRefactorTagCommand);
    }

    @Test
    public void parse_validArgs_without_newTag_returnRefactorTagCommand() {
        RefactorTagCommand expectedRefactorTagCommand =
            new RefactorTagCommand(new Tag(VALID_TAG_UI), new Tag(RefactorTagCommand.DUMMY_TAG));

        assertParseSuccess(parser, TAG_DESC_UI + RefactorTagCommand.DUMMY_TAG, expectedRefactorTagCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // correct tag prefix, but wrong new tag prefix
        assertParseFailure(parser, TAG_DESC_UI + DESCRIPTION_DESC_C,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // wrong tag prefix, also wrong new tag prefix
        assertParseFailure(parser, DESCRIPTION_DESC_C + DESCRIPTION_DESC_C,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // different order and includes tag prefix, but it has not allowed prefix
        assertParseFailure(parser, DESCRIPTION_DESC_C + TAG_DESC_UI,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // only has new tag, but not original tag
        assertParseFailure(parser, TAG_DESC_SYNTAX + TAG_DESC_UI,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // only has new tag, but not original tag
        assertParseFailure(parser, TAG_DESC_UI + TAG_DESC_UI,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

    }


}
