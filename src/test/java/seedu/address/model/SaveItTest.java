package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.ReadOnlySaveIt;
import seedu.saveit.model.person.Person;
import seedu.saveit.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class SaveItTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final SaveIt saveIt = new SaveIt();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), saveIt.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveIt.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        SaveIt newData = getTypicalAddressBook();
        saveIt.resetData(newData);
        assertEquals(newData, saveIt);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        SaveItStub newData = new SaveItStub(newPersons);

        thrown.expect(DuplicatePersonException.class);
        saveIt.resetData(newData);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        saveIt.hasPerson(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(saveIt.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        saveIt.addPerson(ALICE);
        assertTrue(saveIt.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        saveIt.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(saveIt.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        saveIt.getPersonList().remove(0);
    }

    /**
     * A stub ReadOnlySaveIt whose persons list can violate interface constraints.
     */
    private static class SaveItStub implements ReadOnlySaveIt {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        SaveItStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
