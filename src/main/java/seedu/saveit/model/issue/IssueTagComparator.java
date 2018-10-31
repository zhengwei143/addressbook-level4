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
        Tag tagB = b.getTags().stream().findFirst().orElse(null);
        Tag tagA = a.getTags().stream().findFirst().orElse(null);

        if (tagA == null) {
            return 1;
        }

        int result = tagA.compare(tagB);

        if (result == 0) {
            return a.getTags().size() - b.getTags().size();
        } else {
            return result;
        }
    }
}
