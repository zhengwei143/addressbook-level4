package seedu.saveit.model;

import javafx.collections.ObservableList;
import seedu.saveit.model.person.Issue;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlySaveIt {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Issue> getPersonList();

}
