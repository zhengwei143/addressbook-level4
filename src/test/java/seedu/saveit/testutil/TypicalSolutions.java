package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_STACKOVERFLOW;

import seedu.saveit.model.issue.Solution;

/**
 * A utility class containing a list of {@code Solution} objects to be used in tests.
 */
public class TypicalSolutions {
    public static final Solution REPO = new Solution("https://github.com/CS2103-AY1819S1-T12-4/main repoPage");
    public static final Solution ZHIHU = new Solution("http://www.ZhiHu.com newSol");
    public static final Solution GOOGLE = new Solution("http://www.google.com ave");
    public static final Solution REDDIT = new Solution("http://www.reddit.com Reddit");

    // Manually added
    public static final Solution C = new Solution(VALID_SOLUTION_C);
    public static final Solution JAVA = new Solution(VALID_SOLUTION_JAVA);
    public static final Solution STACK = new Solution(VALID_SOLUTION_STACKOVERFLOW);

    private TypicalSolutions() {} // prevents instantiation

}
