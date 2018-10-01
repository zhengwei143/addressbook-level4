package seedu.address.testutil;

import seedu.saveit.model.SaveIt;
import seedu.saveit.model.person.Issue;

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
     * Adds a new {@code Issue} to the {@code SaveIt} that we are building.
     */
    public AddressBookBuilder withPerson(Issue issue) {
        saveIt.addPerson(issue);
        return this;
    }

    public SaveIt build() {
        return saveIt;
    }
}
