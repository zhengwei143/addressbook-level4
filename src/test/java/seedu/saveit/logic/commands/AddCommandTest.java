package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.ObservableList;
import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.ReadOnlySaveIt;
import seedu.saveit.model.SaveIt;
import seedu.saveit.model.issue.IssueSort;
import seedu.saveit.model.issue.Solution;
import seedu.saveit.testutil.IssueBuilder;

public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullIssue_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddCommand(null);
    }

    @Test
    @Ignore
    //TODO: fix the override method below getCurrentDirectory
    public void execute_issueAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingIssueAdded modelStub = new ModelStubAcceptingIssueAdded();
        Issue validIssue = new IssueBuilder().build();

        CommandResult commandResult = new AddCommand(validIssue).execute(modelStub, commandHistory);

        assertEquals(String.format(AddCommand.MESSAGE_ISSUE_SUCCESS, validIssue), commandResult.feedbackToUser);
        assertEquals(Arrays.asList(validIssue), modelStub.issuesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    @Ignore
    //TODO: fix the override method below getCurrentDirectory
    public void execute_duplicateIssue_throwsCommandException() throws Exception {
        Issue validIssue = new IssueBuilder().build();
        AddCommand addCommand = new AddCommand(validIssue);
        ModelStub modelStub = new ModelStubWithIssue(validIssue);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_ISSUE);
        addCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Issue alice = new IssueBuilder().withStatement("Alice").build();
        Issue bob = new IssueBuilder().withStatement("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different issue -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    @Ignore
    private class ModelStub implements Model {
        @Override
        public void addIssue(Issue issue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlySaveIt newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetDirectory(Directory currentDirectory) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Directory getCurrentDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySaveIt getSaveIt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIssue(Issue issue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIssue(Issue target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateIssue(Issue target, Issue editedIssue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Issue> getFilteredIssueList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Solution> getFilteredSolutionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Issue> getSortedIssueList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterIssues(Predicate<Issue> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIssueList(Predicate<Issue> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortIssues(IssueSort sortType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedIssueList(Comparator<Issue> sortType) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoSaveIt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoSaveIt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoSaveIt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoSaveIt() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitSaveIt() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single issue.
     */
    private class ModelStubWithIssue extends ModelStub {
        private final Issue issue;

        ModelStubWithIssue(Issue issue) {
            requireNonNull(issue);
            this.issue = issue;
        }

        @Override
        public boolean hasIssue(Issue issue) {
            requireNonNull(issue);
            return this.issue.isSameIssue(issue);
        }
    }

    /**
     * A Model stub that always accept the issue being added.
     */
    private class ModelStubAcceptingIssueAdded extends ModelStub {
        final ArrayList<Issue> issuesAdded = new ArrayList<>();

        @Override
        public boolean hasIssue(Issue issue) {
            requireNonNull(issue);
            return issuesAdded.stream().anyMatch(issue::isSameIssue);
        }

        @Override
        public void addIssue(Issue issue) {
            requireNonNull(issue);
            issuesAdded.add(issue);
        }

        @Override
        public void commitSaveIt() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlySaveIt getSaveIt() {
            return new SaveIt();
        }
    }

}
