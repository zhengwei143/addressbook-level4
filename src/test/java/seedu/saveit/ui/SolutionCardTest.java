package seedu.saveit.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.saveit.ui.testutil.GuiTestAssert.assertCardDisplaysSolution;

import org.junit.Test;

import guitests.guihandles.SolutionCardHandle;
import seedu.saveit.model.Issue;
import seedu.saveit.model.issue.Solution;
import seedu.saveit.testutil.IssueBuilder;
import seedu.saveit.testutil.SolutionBuilder;

public class SolutionCardTest extends GuiUnitTest {

    @Test
    public void display() {
        Solution solution = new SolutionBuilder().build();
        SolutionCard solutionCard = new SolutionCard(solution, 1);
        uiPartRule.setUiPart(solutionCard);
        assertCardDisplay(solutionCard, solution, 1);
    }

    @Test
    public void equals() {
        Issue issue = new IssueBuilder().build();
        IssueCard issueCard = new IssueCard(issue, 0);

        // same issue, same index -> returns true
        IssueCard copy = new IssueCard(issue, 0);
        assertTrue(issueCard.equals(copy));

        // same object -> returns true
        assertTrue(issueCard.equals(issueCard));

        // null -> returns false
        assertFalse(issueCard.equals(null));

        // different types -> returns false
        assertFalse(issueCard.equals(0));

        // different issue, same index -> returns false
        Issue differentIssue = new IssueBuilder().withStatement("differentName").build();
        assertFalse(issueCard.equals(new IssueCard(differentIssue, 0)));

        // same issue, different index -> returns false
        assertFalse(issueCard.equals(new IssueCard(issue, 1)));
    }

    /**
     * Asserts that {@code solutionCard} displays the details of {@code expectedSolution} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(SolutionCard solutionCard, Solution expectedSolution, int expectedId) {
        guiRobot.pauseForHuman();

        SolutionCardHandle solutionCardHandle = new SolutionCardHandle(solutionCard.getRoot());

        // verify id is displayed correctly
        assertEquals("Solution " + Integer.toString(expectedId), solutionCardHandle.getId());

        // verify solution details are displayed correctly
        assertCardDisplaysSolution(expectedSolution, solutionCardHandle);
    }
}
