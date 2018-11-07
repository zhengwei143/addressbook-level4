package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_REMARK_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_REMARK_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_REMARK_STACKOVERFLOW;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_LINK_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_LINK_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_LINK_STACKOVERFLOW;

import seedu.saveit.model.issue.Solution;

/**
 * A utility class containing a list of {@code Solution} objects to be used in tests.
 */
public class TypicalSolutions {
    public static final Solution REPO = new SolutionBuilder()
            .withLink("https://github.com/CS2103-AY1819S1-T12-4/main").withRemark("repoPage").build();
    public static final Solution ZHIHU = new SolutionBuilder()
            .withLink("http://www.ZhiHu.com").withRemark("newSol").build();
    public static final Solution GOOGLE = new SolutionBuilder()
            .withLink("http://www.google.com").withRemark("ave").build();
    public static final Solution REDDIT = new SolutionBuilder()
            .withLink("http://www.reddit.com").withRemark("Reddit").build();

    // Manually added
    public static final Solution C = new SolutionBuilder().withLink(VALID_SOLUTION_LINK_C)
            .withRemark(VALID_REMARK_C).build();
    public static final Solution JAVA = new SolutionBuilder().withLink(VALID_SOLUTION_LINK_JAVA)
            .withRemark(VALID_REMARK_JAVA).build();
    public static final Solution STACKOVERFLOW = new SolutionBuilder().withLink(VALID_SOLUTION_LINK_STACKOVERFLOW)
            .withRemark(VALID_REMARK_STACKOVERFLOW).build();

    private TypicalSolutions() {} // prevents instantiation

}
