package seedu.saveit.model;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.saveit.commons.core.ComponentManager;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.events.model.AddressBookChangedEvent;
import seedu.saveit.model.person.Issue;
import seedu.saveit.commons.util.CollectionUtil;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedSaveIt versionedAddressBook;
    private final FilteredList<Issue> filteredIssues;

    /**
     * Initializes a ModelManager with the given saveIt and userPrefs.
     */
    public ModelManager(ReadOnlySaveIt saveIt, UserPrefs userPrefs) {
        super();
        CollectionUtil.requireAllNonNull(saveIt, userPrefs);

        logger.fine("Initializing with address book: " + saveIt + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedSaveIt(saveIt);
        filteredIssues = new FilteredList<>(versionedAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new SaveIt(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlySaveIt newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlySaveIt getAddressBook() {
        return versionedAddressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    @Override
    public boolean hasPerson(Issue issue) {
        requireNonNull(issue);
        return versionedAddressBook.hasPerson(issue);
    }

    @Override
    public void deletePerson(Issue target) {
        versionedAddressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addPerson(Issue issue) {
        versionedAddressBook.addPerson(issue);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(Issue target, Issue editedIssue) {
        CollectionUtil.requireAllNonNull(target, editedIssue);

        versionedAddressBook.updatePerson(target, editedIssue);
        indicateAddressBookChanged();
    }

    //=========== Filtered Issue List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Issue} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Issue> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredIssues);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Issue> predicate) {
        requireNonNull(predicate);
        filteredIssues.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredIssues.equals(other.filteredIssues);
    }

}
