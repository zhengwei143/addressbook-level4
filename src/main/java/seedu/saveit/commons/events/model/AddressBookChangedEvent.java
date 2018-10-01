package seedu.saveit.commons.events.model;

import seedu.saveit.commons.events.BaseEvent;
import seedu.saveit.model.ReadOnlyAddressBook;

/** Indicates the AddressBook in the model has changed*/
public class AddressBookChangedEvent extends BaseEvent {

    public final ReadOnlyAddressBook data;

    public AddressBookChangedEvent(ReadOnlyAddressBook data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getPersonList().size();
    }
}
