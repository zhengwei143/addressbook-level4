package seedu.address.model.issue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.Issue;
import seedu.address.model.UniqueIssueList;
import seedu.address.model.issue.exceptions.DuplicatePersonException;
import seedu.address.model.issue.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueIssueListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueIssueList uniqueIssueList = new UniqueIssueList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.contains(null);
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueIssueList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueIssueList.add(ALICE);
        assertTrue(uniqueIssueList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueIssueList.add(ALICE);
        Issue editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueIssueList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.add(null);
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueIssueList.add(ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueIssueList.add(ALICE);
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.setIssue(null, ALICE);
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.setIssue(ALICE, null);
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueIssueList.setIssue(ALICE, ALICE);
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueIssueList.add(ALICE);
        uniqueIssueList.setIssue(ALICE, ALICE);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        expectedUniqueIssueList.add(ALICE);
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueIssueList.add(ALICE);
        Issue editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueIssueList.setIssue(ALICE, editedAlice);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        expectedUniqueIssueList.add(editedAlice);
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueIssueList.add(ALICE);
        uniqueIssueList.setIssue(ALICE, BOB);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        expectedUniqueIssueList.add(BOB);
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueIssueList.add(ALICE);
        uniqueIssueList.add(BOB);
        thrown.expect(DuplicatePersonException.class);
        uniqueIssueList.setIssue(ALICE, BOB);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.remove(null);
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueIssueList.remove(ALICE);
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueIssueList.add(ALICE);
        uniqueIssueList.remove(ALICE);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.setIssues((UniqueIssueList) null);
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueIssueList.add(ALICE);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        expectedUniqueIssueList.add(BOB);
        uniqueIssueList.setIssues(expectedUniqueIssueList);
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueIssueList.setIssues((List<Issue>) null);
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueIssueList.add(ALICE);
        List<Issue> personList = Collections.singletonList(BOB);
        uniqueIssueList.setIssues(personList);
        UniqueIssueList expectedUniqueIssueList = new UniqueIssueList();
        expectedUniqueIssueList.add(BOB);
        assertEquals(expectedUniqueIssueList, uniqueIssueList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Issue> listWithDuplicatePersons = Arrays.asList(ALICE, ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueIssueList.setIssues(listWithDuplicatePersons);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueIssueList.asUnmodifiableObservableList().remove(0);
    }
}
