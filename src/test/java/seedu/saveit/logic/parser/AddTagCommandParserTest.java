package seedu.saveit.logic.parser;

import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.saveit.logic.commands.AddTagCommand.MESSAGE_USAGE;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_PYTHON;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.TAG_DESC_UI;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_PYTHON;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_SYNTAX;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.saveit.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_SECOND_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_THIRD_ISSUE;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.commands.AddTagCommand;
import seedu.saveit.model.issue.Tag;

public class AddTagCommandParserTest {

    private AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_singleIndexSingleTag_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd = new Tag(VALID_TAG_UI);

        tagSet.add(toAdd);
        indexSet.add(INDEX_FIRST_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1" + TAG_DESC_UI, expectedAddTagCommand);
    }

    @Test
    public void parse_singleIndexTwoTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);
        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        indexSet.add(INDEX_FIRST_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1" + TAG_DESC_UI + TAG_DESC_SYNTAX, expectedAddTagCommand);
    }

    @Test
    public void parse_singleIndexMoreTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);
        Tag toAdd3 = new Tag(VALID_TAG_PYTHON);
        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        tagSet.add(toAdd3);
        indexSet.add(INDEX_FIRST_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1" + TAG_DESC_UI + TAG_DESC_SYNTAX + TAG_DESC_PYTHON, expectedAddTagCommand);
    }

    @Test
    public void parse_rangeIndexSingleTag_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd = new Tag(VALID_TAG_UI);

        tagSet.add(toAdd);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_SECOND_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1-3" + TAG_DESC_UI, expectedAddTagCommand);

    }

    @Test
    public void parse_rangeIndexTwoTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);

        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_SECOND_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1-3" + TAG_DESC_UI + TAG_DESC_SYNTAX, expectedAddTagCommand);

    }

    @Test
    public void parse_rangeIndexMoreTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);
        Tag toAdd3 = new Tag(VALID_TAG_PYTHON);

        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        tagSet.add(toAdd3);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_SECOND_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1-3" + TAG_DESC_UI + TAG_DESC_SYNTAX + TAG_DESC_PYTHON, expectedAddTagCommand);

    }


    @Test
    public void parse_indexesSingleTag_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd = new Tag(VALID_TAG_UI);

        tagSet.add(toAdd);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1 3" + TAG_DESC_UI, expectedAddTagCommand);

    }

    @Test
    public void parse_indexesTwoTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);

        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1 3" + TAG_DESC_UI + TAG_DESC_SYNTAX, expectedAddTagCommand);

    }

    @Test
    public void parse_indexesMoreTags_returnAddTagCommand() {
        Set<Tag> tagSet = new LinkedHashSet();
        Set<Index> indexSet = new LinkedHashSet();
        Tag toAdd1 = new Tag(VALID_TAG_UI);
        Tag toAdd2 = new Tag(VALID_TAG_SYNTAX);
        Tag toAdd3 = new Tag(VALID_TAG_PYTHON);

        tagSet.add(toAdd1);
        tagSet.add(toAdd2);
        tagSet.add(toAdd3);
        indexSet.add(INDEX_FIRST_ISSUE);
        indexSet.add(INDEX_THIRD_ISSUE);

        AddTagCommand expectedAddTagCommand =
            new AddTagCommand(indexSet, tagSet);

        assertParseSuccess(parser, "1 3" + TAG_DESC_UI + TAG_DESC_SYNTAX + TAG_DESC_PYTHON, expectedAddTagCommand);

    }


    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "?", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    // TODO: check exception for invalid tag, maybe separate
    @Test
    public void parse_validIndexInvalidTag_throwsParseException() {
        assertParseFailure(parser, "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "2 java",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 python 3", String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidIndexInvalidTag_throwsParseException() {
        assertParseFailure(parser, "0 java",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexValidTag_throwsParseException() {
        assertParseFailure(parser, "0 t/java",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyIndexValidTag_throwsParseException() {
        assertParseFailure(parser, "t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidRange1_throwsParseException() {
        assertParseFailure(parser, "4-2 t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidRange2_throwsParseException() {
        assertParseFailure(parser, "4-a t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexedAndRange_throwsParseException() {
        assertParseFailure(parser, "1 4-2 t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidMultipleRanges_throwsParseException() {
        assertParseFailure(parser, " 2-4 4-a t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexsRanges_throwsParseException() {
        assertParseFailure(parser, " -4-3 t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndexValidTags_throwsParseException() {
        assertParseFailure(parser, " 4-* t/python",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }
}
