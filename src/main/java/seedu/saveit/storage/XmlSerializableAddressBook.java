package seedu.saveit.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import seedu.saveit.commons.exceptions.IllegalValueException;
import seedu.saveit.model.ReadOnlySaveIt;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.person.Issue;

/**
 * An Immutable SaveIt that is serializable to XML format
 */
@XmlRootElement(name = "saveit")
public class XmlSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate issue(s).";

    @XmlElement
    private List<XmlAdaptedPerson> persons;

    /**
     * Creates an empty XmlSerializableAddressBook.
     * This empty constructor is required for marshalling.
     */
    public XmlSerializableAddressBook() {
        persons = new ArrayList<>();
    }

    /**
     * Conversion
     */
    public XmlSerializableAddressBook(ReadOnlySaveIt src) {
        this();
        persons.addAll(src.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this saveit into the model's {@code SaveIt} object.
     *
     * @throws IllegalValueException if there were any data constraints violated or duplicates in the
     * {@code XmlAdaptedPerson}.
     */
    public SaveIt toModelType() throws IllegalValueException {
        SaveIt saveIt = new SaveIt();
        for (XmlAdaptedPerson p : persons) {
            Issue issue = p.toModelType();
            if (saveIt.hasPerson(issue)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            saveIt.addPerson(issue);
        }
        return saveIt;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof XmlSerializableAddressBook)) {
            return false;
        }
        return persons.equals(((XmlSerializableAddressBook) other).persons);
    }
}
