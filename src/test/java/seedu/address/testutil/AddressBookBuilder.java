package seedu.address.testutil;

import seedu.saveit.model.SaveIt;
import seedu.saveit.model.person.Person;

/**
 * A utility class to help with building saveit objects.
 * Example usage: <br>
 *     {@code SaveIt ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private SaveIt saveIt;

    public AddressBookBuilder() {
        saveIt = new SaveIt();
    }

    public AddressBookBuilder(SaveIt saveIt) {
        this.saveIt = saveIt;
    }

    /**
     * Adds a new {@code Person} to the {@code SaveIt} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        saveIt.addPerson(person);
        return this;
    }

    public SaveIt build() {
        return saveIt;
    }
}
