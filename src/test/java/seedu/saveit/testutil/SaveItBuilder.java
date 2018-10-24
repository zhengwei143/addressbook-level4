package seedu.saveit.testutil;

import seedu.saveit.model.Issue;
import seedu.saveit.model.SaveIt;

/**
 * A utility class to help with building saveit objects.
 * Example usage: <br>
 *     {@code SaveIt ab = new SaveItBuilder().withIssue("John", "Doe").build();}
 */
public class SaveItBuilder {

    private SaveIt saveIt;

    public SaveItBuilder() {
        saveIt = new SaveIt();
    }

    public SaveItBuilder(SaveIt saveIt) {
        this.saveIt = saveIt;
    }

    /**
     * Adds a new {@code Issue} to the {@code SaveIt} that we are building.
     */
    public SaveItBuilder withIssue(Issue issue) {
        saveIt.addIssue(issue);
        return this;
    }

    public SaveIt build() {
        return saveIt;
    }
}
