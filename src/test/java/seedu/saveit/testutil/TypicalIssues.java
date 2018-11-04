package seedu.saveit.testutil;

import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DESCRIPTION_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_DESCRIPTION_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_REMARK_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_REMARK_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_LINK_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_SOLUTION_LINK_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_STATEMENT_C;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_STATEMENT_JAVA;
import static seedu.saveit.logic.commands.CommandTestUtil.VALID_TAG_UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.saveit.model.Issue;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.issue.Solution;

/**
 * A utility class containing a list of {@code Issue} objects to be used in tests.
 */
public class TypicalIssues {
    // {@code IssueSearchFrequency} value when first initialized
    public static final Integer INITIALIZED_ISSUE_FREQUENCY = 0;
    // {@code IssueSearchFrequency} value commonly used across the test cases
    public static final Integer COMMON_ISSUE_FREQUENCY = 1;
    public static final long COMMON_ISSUE_TIME = 0;

    public static final Issue ALICE = new IssueBuilder().withStatement("Alice Pauline")
            .withDescription("94351253")
            .withTags("syntax")
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 10).build();
    public static final Issue BENSON = new IssueBuilder().withStatement("Benson Meier")
            .withDescription("98765432")
            .withTags("owesMoney", "friends")
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 20).build();
    public static final Issue CARL = new IssueBuilder().withStatement("Carl Kurz").withDescription("95352563")
            .withSolutions(new SolutionBuilder().withLink("https://github.com/CS2103-AY1819S1-T12-4/main")
                            .withRemark("repoPage").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 30).build();
    public static final Issue DANIEL = new IssueBuilder().withStatement("Daniel Meier")
            .withDescription("87652533")
            .withTags("friends")
            .withSolutions(new SolutionBuilder().withLink("https://stackoverflow.com/").withRemark("newSolution").build(),
                    new SolutionBuilder().withLink("http://www.ZhiHu.com").withRemark("newSol").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 40).build();
    public static final Issue ELLE = new IssueBuilder().withStatement("Elle Meyer").withDescription("9482224")
            .withSolutions(new SolutionBuilder().withLink("http://www.google.com").withRemark("ave").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 50).build();
    public static final Issue FIONA = new IssueBuilder().withStatement("Fiona Kunz").withDescription("9482427")
            .withSolutions(new SolutionBuilder().withLink("http://www.reddit.com").withRemark("tokyo").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 60).build();

    public static final Issue GEORGE = new IssueBuilder().withStatement("George Best").withDescription("9482442")
            .withSolutions(new SolutionBuilder().withLink("http://www.yahoo.com").withRemark("street").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime(COMMON_ISSUE_TIME + 70).build();

    // Manually added
    public static final Issue HOON = new IssueBuilder().withStatement("Hoon Meier").withDescription("8482424")
            .withSolutions(new SolutionBuilder().withLink("https://stackoverflow.com/").withRemark("india").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime().build();
    public static final Issue IDA = new IssueBuilder().withStatement("Ida Mueller").withDescription("8482131")
            .withSolutions(new SolutionBuilder().withLink("http://www.baidu.com").withRemark("ave").build())
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime().build();

    // Manually added - Issue's details found in {@code CommandTestUtil}
    public static final Issue AMY = new IssueBuilder().withStatement(VALID_STATEMENT_JAVA)
            .withDescription(VALID_DESCRIPTION_JAVA)
            .withSolutions(new SolutionBuilder().withLink(VALID_SOLUTION_LINK_JAVA).withRemark(VALID_REMARK_JAVA).build())
            .withTags(VALID_TAG_UI)
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime().build();
    public static final Issue BOB = new IssueBuilder().withStatement(VALID_STATEMENT_C)
            .withDescription(VALID_DESCRIPTION_C)
            .withSolutions(new SolutionBuilder().withLink(VALID_SOLUTION_LINK_C).withRemark(VALID_REMARK_C).build())
            .withTags(VALID_TAG_UI)
            .withFrequency(COMMON_ISSUE_FREQUENCY)
            .withLastModifiedTime().build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalIssues() {} // prevents instantiation

    /**
     * Returns an {@code SaveIt} with all the typical issues.
     */
    public static SaveIt getTypicalSaveIt() {
        SaveIt ab = new SaveIt();
        for (Issue issue : getTypicalIssues()) {
            ab.addIssue(issue);
        }
        return ab;
    }

    public static List<Issue> getTypicalIssues() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
