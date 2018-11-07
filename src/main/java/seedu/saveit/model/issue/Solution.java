package seedu.saveit.model.issue;

import java.util.Objects;

import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * Represents a Issue's solution in saveit.
 */
public class Solution {
    private final SolutionLink solutionLink;
    private final Remark remark;

    /**
     * Construct a new Solution.
     *
     * @param solutionLink url link to solution website.
     * @param remark user's notes about this solution.
     */
    public Solution(SolutionLink solutionLink, Remark remark) {
        this.solutionLink = solutionLink;
        this.remark = remark;
    }

    public Solution(Solution solution) {
        this.solutionLink = solution.getLink();
        this.remark = solution.remark;
    }

    public SolutionLink getLink() {
        return solutionLink;
    }

    public Remark getRemark() {
        return remark;
    }

    public boolean isPrimarySolution() {
        return false;
    }

    @Override
    public String toString() {
        return '[' + " link: " + getLink() + " remark: " + getRemark() + ']';
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Solution // instanceof handles nulls
                && solutionLink.equals(((Solution) other).solutionLink)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(solutionLink, remark);
    }
}
