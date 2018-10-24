package seedu.address.model.issue;

import java.util.Comparator;

import seedu.address.model.Issue;

/**
 * Comparator used to sort the Issues in order
 */
public class IssueTagSort implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        return b.getTags().iterator().next().compare(a.getTags().iterator().next());
    }
}
