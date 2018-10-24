package seedu.saveit.model.issue;

import java.util.Comparator;

import seedu.saveit.model.Issue;

/**
 * Comparator used to sort the Issues in order
 */
public class IssueSort implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        return b.getFrequency().compare(a.getFrequency());
    }
}