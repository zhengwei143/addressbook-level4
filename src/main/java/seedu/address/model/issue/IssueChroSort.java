package seedu.address.model.issue;

import seedu.address.model.Issue;

import java.util.Comparator;

public class IssueChroSort implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        return b.getFrequency().compare(a.getFrequency());
    }
}
