package seedu.saveit.model.issue;

import java.util.Objects;

import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * Represents a Issue's solution in saveit.
 */
public class Solution {

    public final String solutionName;

    public final SolutionLink solutionLink;

    public final Remark remark;

    /**
     * Construct a new Solution.
     */
    public Solution(String solutionName) {
        int index = solutionName.indexOf(' ');
        if (index != -1) {
            this.solutionLink = new SolutionLink(solutionName.substring(0, index));
            this.remark = new Remark(solutionName.substring(index + 1));
        } else {
            if (SolutionLink.isValidLink(solutionName)) {
                this.solutionLink = new SolutionLink(solutionName);
                this.remark = null;
            } else {
                this.solutionLink = null;
                this.remark = new Remark(solutionName);
            }
        }
        this.solutionName = solutionName;
    }

    /**
     * Construct a new Solution.
     *
     * @param solutionLink url link to solution website.
     * @param remark user's notes about this solution.
     */
    public Solution(String solutionLink, String remark) {
        this.solutionLink = new SolutionLink(solutionLink);
        this.remark = new Remark(remark);
        this.solutionName = solutionLink + " " + remark;
    }

    public Solution(SolutionLink solutionLink, Remark remark) {
        this.solutionLink = solutionLink;
        this.remark = remark;
        this.solutionName = solutionLink + " " + remark;
    }

    public Solution(Solution solution) {
        this.solutionLink = solution.getLink();
        this.remark = solution.remark;
        this.solutionName = solutionLink + " " + remark;
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
