package seedu.saveit.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.saveit.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DESCRIPTION_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_STATEMENT_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;
import static seedu.saveit.testutil.TypicalSolutions.SOLUTION_C;

import org.junit.Test;

import seedu.saveit.logic.commands.EditCommand.EditIssueDescriptor;
import seedu.saveit.testutil.EditIssueDescriptorBuilder;

public class EditIssueDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditIssueDescriptor descriptorWithSameValues = new EditCommand.EditIssueDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditIssueDescriptor editedAmy = new EditIssueDescriptorBuilder(DESC_AMY)
                .withStatement(VALID_STATEMENT_C).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditIssueDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_C).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different solutions -> returns false
        editedAmy = new EditIssueDescriptorBuilder(DESC_AMY).withSolutions(SOLUTION_C).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditIssueDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_UI).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
