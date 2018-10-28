package seedu.saveit.model.issue;

import java.util.Comparator;

import seedu.saveit.model.Issue;

/**
 * Comparator used to sort the Issues in order
 */
public class IssueTagComparator implements Comparator<Issue> {
    /**
     * Compare Issue a and b with their first tag in the tag set. Tag is compared based on String tagName.
     */
    public int compare(Issue a, Issue b) {
        Tag tag_b = b.getTags().stream().findFirst().orElse(null);
        Tag tag_a = a.getTags().stream().findFirst().orElse(null);

        if (tag_a == null) {
            return 1;
        }

        int result = tag_a.compare(tag_b);

        if (result == 0) {
            return a.getTags().size() - b.getTags().size();
        } else {
            return result;
        }
    }
}
