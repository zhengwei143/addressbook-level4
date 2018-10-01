package seedu.saveit.model.person;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.saveit.model.issue.Issue;
import seedu.saveit.testutil.PersonBuilder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.logic.commands.CommandTestUtil.*;
import static seedu.saveit.testutil.TypicalPersons.ALICE;
import static seedu.saveit.testutil.TypicalPersons.BOB;

public class IssueTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Issue issue = new PersonBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        issue.getTags().remove(0);
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameIssue(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameIssue(null));

        // different phone and email -> returns false
        Issue editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameIssue(editedAlice));

        // different name -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameIssue(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameIssue(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameIssue(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameIssue(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Issue aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different issue -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Issue editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different saveit -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
