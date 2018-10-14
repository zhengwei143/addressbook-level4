package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameIssue comparison)
 */
public class SaveIt implements ReadOnlySaveIt {

    private final UniqueIssueList issues;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        issues = new UniqueIssueList();
    }

    public SaveIt() {}

    /**
     * Creates an SaveIt using the Issues in the {@code toBeCopied}
     */
    public SaveIt(ReadOnlySaveIt toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the issue list with {@code issues}.
     * {@code issues} must not contain duplicate issues.
     */
    public void setIssues(List<Issue> issues) {
        this.issues.setIssues(issues);
    }

    /**
     * Resets the existing data of this {@code SaveIt} with {@code newData}.
     */
    public void resetData(ReadOnlySaveIt newData) {
        requireNonNull(newData);

        setIssues(newData.getIssueList());
    }

    //// issue-level operations

    /**
     * Returns true if a issue with the same identity as {@code issue} exists in the saveIt.
     */
    public boolean hasIssue(Issue issue) {
        requireNonNull(issue);
        return issues.contains(issue);
    }

    /**
     * Adds a issue to the saveIt.
     * The issue must not already exist in the saveIt.
     */
    public void addIssue(Issue p) {
        issues.add(p);
    }

    /**
     * Replaces the given issue {@code target} in the list with {@code editedIssue}.
     * {@code target} must exist in the saveIt.
     * The issue identity of {@code editedIssue} must not be the same as another existing issue in the saveIt.
     */
    public void updateIssue(Issue target, Issue editedIssue) {
        requireNonNull(editedIssue);

        issues.setIssue(target, editedIssue);
    }

    /**
     * Removes {@code key} from this {@code SaveIt}.
     * {@code key} must exist in the saveIt.
     */
    public void removeIssue(Issue key) {
        issues.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return issues.asUnmodifiableObservableList().size() + " issues";
        // TODO: refine later
    }

    @Override
    public ObservableList<Issue> getIssueList() {
        return issues.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SaveIt // instanceof handles nulls
                && issues.equals(((SaveIt) other).issues));
    }

    @Override
    public int hashCode() {
        return issues.hashCode();
    }
}
