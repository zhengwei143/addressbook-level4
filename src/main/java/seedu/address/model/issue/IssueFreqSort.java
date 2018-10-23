package seedu.address.model.issue;

import java.util.Comparator;

import seedu.address.model.Issue;

/**
 * Comparator used to sort the Issues in search frequency order
 */
public class IssueFreqSort implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        return b.getFrequency().compare(a.getFrequency());
    }
}
