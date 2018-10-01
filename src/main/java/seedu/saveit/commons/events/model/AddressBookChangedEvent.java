package seedu.saveit.commons.events.model;

import seedu.saveit.commons.events.BaseEvent;
import seedu.saveit.model.ReadOnlySaveIt;

/** Indicates the SaveIt in the model has changed*/
public class AddressBookChangedEvent extends BaseEvent {

    public final ReadOnlySaveIt data;

    public AddressBookChangedEvent(ReadOnlySaveIt data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getPersonList().size();
    }
}
