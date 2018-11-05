package seedu.saveit.model.issue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

import seedu.saveit.model.Issue;

/**
 * Comparator used to sort the Issues in order
 */
public class IssueTagComparator implements Comparator<Issue> {
    /**
     * Compare Issue a and b with their first tag in the tag set. Tag is compared based on String tagName.
     */
    public int compare(Issue a, Issue b) {
        Set<Tag> tagsB = b.getTags();
        Set<Tag> tagsA = a.getTags();

        Iterator<Tag> tagAIterator = tagsA.iterator();
        Iterator<Tag> tagBIterator = tagsB.iterator();
        while (tagAIterator.hasNext() && tagBIterator.hasNext()) {
            Tag tagA = tagAIterator.next();
            Tag tagB = tagBIterator.next();
            if (tagA.compare(tagB) != 0) {
                return tagA.compare(tagB);
            }
        }
        return tagsA.size() - tagsB.size();
    }
}
