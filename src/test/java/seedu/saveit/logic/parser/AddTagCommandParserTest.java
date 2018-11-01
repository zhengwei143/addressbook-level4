package seedu.saveit.logic.parser;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.AddTagCommand.MESSAGE_USAGE;
import static seedu.saveit.logic.commands.CommandTestUtil.DESCRIPTION_DESC_C;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import seedu.saveit.logic.commands.AddTagCommand;
import seedu.saveit.model.issue.Tag;

public class AddTagCommandParserTest {

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    @Ignore
    public void parse_validOneTag_returnAddTagCommand() {
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag(VALID_TAG_UI));

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(INDEX_FIRST_ISSUE, tagSet);

        assertParseSuccess(parser, "1" + TAG_DESC_UI, expectedAddTagCommand);

    }

    @Test
    @Ignore
    public void parse_validMoreTags_returnAddTagCommand() {
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag(VALID_TAG_UI));
        tagSet.add(new Tag(VALID_TAG_SYNTAX));

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(INDEX_FIRST_ISSUE, tagSet);

        assertParseSuccess(parser, "1" + TAG_DESC_UI + TAG_DESC_SYNTAX, expectedAddTagCommand);

    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // without index
        assertParseFailure(parser, TAG_DESC_UI,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

        // with index, but other prefix
        assertParseFailure(parser, DESCRIPTION_DESC_C,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));


    }
}
