package seedu.saveit.model.issue;

import java.util.Comparator;

import seedu.saveit.model.Issue;

/**
 * Comparator used to sort the Issues in order
 */
public class IssueTagComparator implements Comparator<Issue> {
    public int compare(Issue a, Issue b) {
        Tag tag_b = b.getTags().stream().findFirst().orElse(null);
        Tag tag_a = a.getTags().stream().findFirst().orElse(null);


        System.out.println(b.getStatement().issue + ":" + b.getTags().size() + "  " + a.getStatement().issue + ":" + a.getTags().size());
        if (tag_a == null) {
            return -1;
        } else if (tag_b == null) {
            return 1;
        }

        return tag_a.compare(tag_b);
    }
}
