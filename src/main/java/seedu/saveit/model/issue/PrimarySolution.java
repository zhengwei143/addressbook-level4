package seedu.saveit.model.issue;

import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * Represents a Issue's primary solution in saveit.
 */
public class PrimarySolution extends Solution {
    public PrimarySolution(SolutionLink solutionLink, Remark remark) {
        super(solutionLink, remark);
    }

    public PrimarySolution(Solution solution) {
        super(solution.getLink(), solution.getRemark());
    }

    public boolean isPrimarySolution() {
        return true;
    }
}
