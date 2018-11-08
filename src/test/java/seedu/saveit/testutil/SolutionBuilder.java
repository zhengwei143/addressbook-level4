package seedu.saveit.testutil;

import seedu.saveit.model.issue.Solution;
import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * A utility class to help with building Issue objects.
 */
public class SolutionBuilder {

    public static final String DEFAULT_LINK = "https://docs.oracle.com/javase/7/docs/api/";
    public static final String DEFAULT_REMARK = "Java™ Platform, SE 7 API Specification";
    public static final String DEFAULT_SOLUTION_NAME = "https://docs.oracle.com/javase/7/docs/api/" + " "
           + "Java™ Platform, SE 7 API Specification";

    private SolutionLink solutionLink;
    private Remark remark;

    public SolutionBuilder() {
        solutionLink = new SolutionLink(DEFAULT_LINK);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the SolutionBuilder with the data of {@code solutionToCopy}.
     */
    public SolutionBuilder(Solution solutionToCopy) {
        solutionLink = solutionToCopy.getLink();
        remark = solutionToCopy.getRemark();
    }

    /**
     * Sets the {@code solutionLink} of the {@code Solution} that we are building.
     */
    public SolutionBuilder withLink(String link) {
        this.solutionLink = new SolutionLink(link);
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code Solution} that we are building.
     */
    public SolutionBuilder withRemark (String remark) {
        this.remark = new Remark(remark);
        return this;
    }


    public Solution build() {
        return new Solution(solutionLink , remark);
    }

}
