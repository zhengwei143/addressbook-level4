package seedu.saveit.logic.commands;

import static junit.framework.TestCase.assertEquals;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.saveit.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_ISSUE;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_FIRST_SOLUTION;
import static seedu.saveit.testutil.TypicalIndexes.INDEX_THIRD_ISSUE;
import static seedu.saveit.testutil.TypicalIssues.ALICE;
import static seedu.saveit.testutil.TypicalIssues.BENSON;
import static seedu.saveit.testutil.TypicalIssues.CARL;
import static seedu.saveit.testutil.TypicalIssues.DANIEL;
import static seedu.saveit.testutil.TypicalIssues.ELLE;
import static seedu.saveit.testutil.TypicalIssues.FIONA;
import static seedu.saveit.testutil.TypicalIssues.GEORGE;
import static seedu.saveit.testutil.TypicalIssues.getTypicalSaveIt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.ModelManager;
import seedu.saveit.model.UserPrefs;
import seedu.saveit.model.issue.Description;
import seedu.saveit.model.issue.IssueContainsKeywordsPredicate;
import seedu.saveit.model.issue.IssueStatement;
import seedu.saveit.model.issue.SortType;
import seedu.saveit.testutil.DirectoryBuilder;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalSaveIt(), new UserPrefs());
        expectedModel = new ModelManager(model.getSaveIt(), new UserPrefs());
    }

    @Test
    public void execute_sortIsNotFiltered_success() {
        SortType sortType = prepareIssueSort(SortType.TAG_SORT);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, sortType.getSortType());
        expectedModel.updateFilteredAndSortedIssueList(sortType.getComparator());
        SortCommand command = new SortCommand(sortType);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, BENSON, ALICE, CARL, ELLE, FIONA, GEORGE),
                model.getFilteredAndSortedIssueList());
    }

    @Test
    public void execute_sortIsFiltered_success() {
        String[] keywordArray = {"Alice", "Benson", "Daniel", "Meyer"};
        updateLastModifiedTime(BENSON, ELLE, DANIEL);
        filterIssueList(keywordArray);

        SortType sortType = prepareIssueSort(SortType.CHRONOLOGICAL_SORT);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, sortType.getSortType());
        expectedModel.updateFilteredAndSortedIssueList(sortType.getComparator());
        SortCommand command = new SortCommand(sortType);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL, ELLE, BENSON, ALICE), model.getFilteredAndSortedIssueList());
    }

    @Test
    public void execute_sortAfterUpdate_success() {
        SortType sortType = prepareIssueSort(SortType.FREQUENCY_SORT);
        String expectedMessage = String.format(SortCommand.MESSAGE_SUCCESS, sortType.getSortType());
        Issue issue = new Issue(new IssueStatement("new SOLUTION_C++ problem"),
                new Description("only for test"), new ArrayList<>(), new HashSet<>());
        updateFrequency(ELLE, ELLE, ELLE, BENSON, BENSON, BENSON, BENSON, ALICE, ALICE, CARL, FIONA);

        expectedModel.addIssue(issue);
        expectedModel.updateFilteredAndSortedIssueList(sortType.getComparator());
        model.addIssue(issue);
        SortCommand command = new SortCommand(sortType);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, ALICE, CARL, FIONA, DANIEL, GEORGE, issue),
                model.getFilteredAndSortedIssueList());
    }

    @Test
    public void execute_notUnderRootLevel_failure() {
        model.resetDirectory(new DirectoryBuilder().withIssueIndex(INDEX_FIRST_ISSUE).build());
        SortType sortType = prepareIssueSort(SortType.TAG_SORT);
        SortCommand command = new SortCommand(sortType);
        assertCommandFailure(command, model, commandHistory, Messages.MESSAGE_WRONG_DIRECTORY);

        model.resetDirectory(new DirectoryBuilder().withIssueIndex(INDEX_THIRD_ISSUE)
                .withSolutionIndex(INDEX_FIRST_SOLUTION).build());
        assertCommandFailure(command, model, commandHistory, Messages.MESSAGE_WRONG_DIRECTORY);
    }

    /**
     * Parses {@code userInput} into a {@code Comparator<Issue>}.
     */
    private SortType prepareIssueSort(String userInput) {
        return new SortType(userInput);
    }

    /**
     * Filter issue lists in both model and expectedModel by the provided keywords.
     * @param keywordArray
     */
    private void filterIssueList(String[] keywordArray) {
        List<String> keywords = Arrays.asList(keywordArray);
        IssueContainsKeywordsPredicate predicate = new IssueContainsKeywordsPredicate(keywords);
        model.updateFilteredIssueList(predicate);
        expectedModel.updateFilteredIssueList(predicate);
    }

    /**
     * Update frequency of data.
     */
    private void updateFrequency(Issue... issues) {
        Arrays.stream(issues).forEach(i -> i.updateFrequency());
    }

    /**
     * Update frequency of data.
     */
    private void updateLastModifiedTime(Issue... issues) {
        for (Issue issue : issues) {
            Issue newIssue = new Issue(issue.getStatement(), issue.getDescription(),
                    issue.getSolutions(), issue.getTags(), issue.getFrequency(), issue.getCreatedTime());
            model.updateIssue(issue, newIssue);
            expectedModel.updateIssue(issue, newIssue);
        }
    }
}
