package seedu.address.model.issue;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.Issue;

/**
 * Tests that a {@code Issue}'s {@code tags} matches any of the keywords given.
 */
public class IssueHasTagsPredicate implements Predicate<Issue> {
    private final List<String> keywords;

    public IssueHasTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Issue issue) {
        return keywords.stream().allMatch(keyword -> issue.getTags().contains(new Tag(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueHasTagsPredicate // instanceof handles nulls
                && keywords.equals(((IssueHasTagsPredicate) other).keywords)); // state check
    }
}
