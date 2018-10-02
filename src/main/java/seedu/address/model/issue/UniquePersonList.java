package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.issue.exceptions.DuplicatePersonException;
import seedu.address.model.issue.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A issue is considered unique by comparing using {@code Issue#isSameIssue(Issue)}. As such, adding and updating of
 * persons uses Issue#isSameIssue(Issue) for equality so as to ensure that the issue being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a issue uses Issue#equals(Object) so
 * as to ensure that the issue with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Issue#isSameIssue(Issue)
 */
public class UniquePersonList implements Iterable<Issue> {

    private final ObservableList<Issue> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent issue as the given argument.
     */
    public boolean contains(Issue toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameIssue);
    }

    /**
     * Adds a issue to the list.
     * The issue must not already exist in the list.
     */
    public void add(Issue toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the issue {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The issue identity of {@code editedPerson} must not be the same as another existing issue in the list.
     */
    public void setPerson(Issue target, Issue editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameIssue(editedPerson) && contains(editedPerson)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPerson);
    }

    /**
     * Removes the equivalent issue from the list.
     * The issue must exist in the list.
     */
    public void remove(Issue toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Issue> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Issue> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Issue> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Issue> persons) {
        for (int i = 0; i < persons.size() - 1; i++) {
            for (int j = i + 1; j < persons.size(); j++) {
                if (persons.get(i).isSameIssue(persons.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
