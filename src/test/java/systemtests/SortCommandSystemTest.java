package systemtests;

import static seedu.saveit.model.issue.SortType.CHRONOLOGICAL_SORT;
import static seedu.saveit.model.issue.SortType.FREQUENCY_SORT;
import static seedu.saveit.model.issue.SortType.TAG_SORT;
import static seedu.saveit.testutil.TypicalIssues.ALICE;
import static seedu.saveit.testutil.TypicalIssues.BENSON;
import static seedu.saveit.testutil.TypicalIssues.CARL;
import static seedu.saveit.testutil.TypicalIssues.DANIEL;
import static seedu.saveit.testutil.TypicalIssues.ELLE;
import static seedu.saveit.testutil.TypicalIssues.FIONA;
import static seedu.saveit.testutil.TypicalIssues.GEORGE;

import org.junit.Ignore;
import org.junit.Test;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.commands.ListCommand;
import seedu.saveit.logic.commands.SelectCommand;
import seedu.saveit.logic.commands.SortCommand;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.SortType;

public class SortCommandSystemTest extends SaveItSystemTest {
    @Test
    @Ignore
    public void sort() {
        Model expectedModel = getModel();

        /* Case: sort issues by tag sort in saveit book, command with leading spaces and trailing spaces */
        String command = "   " + SortCommand.COMMAND_WORD + " " + TAG_SORT + "   ";
        ModelHelper.setSortedList(expectedModel, DANIEL, BENSON, ALICE, CARL, ELLE, FIONA, GEORGE);
        assertCommandSuccess(command, SortType.TAG, expectedModel);

        /* Case: repeat previous find command where issue list is displaying in the order we are using
         * -> no change
         */
        command = SortCommand.COMMAND_WORD + " " + TAG_SORT;
        assertCommandSuccess(command, SortType.TAG, expectedModel);

        /* Case: sort issues by default sort in saveit book */
        command = SortCommand.COMMAND_WORD;
        ModelHelper.setSortedList(expectedModel, ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertCommandSuccess(command, SortType.DEFAULT, expectedModel);

        /* Case: sort issues by frequency sort in saveit book when frequency of all issues is the same
         * -> no change
         */
        command = SortCommand.COMMAND_WORD + " " + FREQUENCY_SORT;
        assertCommandSuccess(command, SortType.FREQUENCY, expectedModel);

        /* Case: update issue frequency. The issue list is updated accordingly. */
        updateFrequency(ELLE, ELLE, CARL, GEORGE);
        ModelHelper.setSortedList(expectedModel, ELLE, CARL, GEORGE, ALICE, BENSON, DANIEL, FIONA);
        assertCommandSuccess(command, SortType.FREQUENCY, expectedModel);

        /* Case: sort issues by chronological sort in saveit book when frequency of all issues is the same */
        command = SortCommand.COMMAND_WORD + " " + CHRONOLOGICAL_SORT;
        ModelHelper.setSortedList(expectedModel, GEORGE, FIONA, ELLE, DANIEL, CARL, BENSON, ALICE);
        assertCommandSuccess(command, SortType.CHRONOLOGICAL, expectedModel);

        /* Case: sort the filtered list */
        filterList(ALICE, DANIEL, BENSON);
        ModelHelper.setFilteredList(expectedModel, ALICE, DANIEL, BENSON);
        ModelHelper.setSortedList(expectedModel, DANIEL, BENSON, ALICE);
        assertCommandSuccess(command, SortType.CHRONOLOGICAL, expectedModel);

        /* Case: invalid sort type
         * -> failure
         */
        command = SortCommand.COMMAND_WORD + " random";
        assertCommandFailure(command,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        /* Case: sort after selectiong
         * -> failure
         */
        executeCommand(SelectCommand.COMMAND_WORD + " 1");
        command = SortCommand.COMMAND_WORD;
        assertCommandFailure(command, Messages.MESSAGE_WRONG_DIRECTORY);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_ISSUES_LISTED_OVERVIEW} with the number of people in the filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code SaveItSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     * @see SaveItSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, String sortType, Model expectedModel) {
        String expectedResultMessage = String.format(SortCommand.MESSAGE_SUCCESS, sortType);

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code SaveItSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     * @see SaveItSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Find the {@code} issues passed in one by one, to increase their search frequency.
     */
    private void updateFrequency(Issue... issues) {
        String command;
        for (Issue issue : issues) {
            String statement = issue.getStatement().getValue().split(" ")[1];
            command = FindCommand.COMMAND_WORD + " " + statement;
            executeCommand(command);
            command = ListCommand.COMMAND_WORD;
            executeCommand(command);
        }
    }

    /**
     * Find all the {@code} issues passed in, to filter the issue list.
     */
    private void filterList(Issue... issues) {
        String keyword = "";
        for (Issue issue : issues) {
            keyword = keyword + issue.getStatement().getValue();
        }
        executeCommand(FindCommand.COMMAND_WORD + " " + keyword);
    }
}
