package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Issue;
import seedu.address.model.Model;
import seedu.address.model.issue.Tag;

/**
 * To rename or remove a spcific tag for all entries with that tag.
 */
public class RefactorTagCommand extends Command {

    public static final String COMMAND_WORD = "refactorTag";
    public static final String COMMAND_ALIAS = "rt";

    public static final String MESSAGE_REFACTOR_TAG_SUCCESS = "Refactoring tag success";

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": To rename or remove a specific tag for all entries with that tag.\n"
            + "Parameters: KEYWORD t/OLD_TAG [nt/NEW_TAG]...\n"
            + "Example: " + COMMAND_WORD + " t/python nt/java";

    private static final String dummyTag = "dummyTag";

    private final Tag oldTag;
    private final Tag newTag;

    /**
     * @param oldTag the tag will be replaced
     * @param newTag the new tag that will replace original one
     */
    public RefactorTagCommand(Tag oldTag, Tag newTag) {
        requireNonNull(oldTag);
        this.oldTag = oldTag;
        this.newTag = newTag;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Issue> lastShownList = model.getFilteredIssueList();

        for (Issue issue : lastShownList) {
            Set<Tag> updatedTags = new HashSet<Tag>(issue.getTags());
            if (updatedTags.contains(oldTag)) {
                updatedTags.remove(oldTag);
                if (!newTag.tagName.equals(dummyTag)) {
                    updatedTags.add(newTag);
                }
            }

            Issue editedIssue = new Issue(issue.getStatement(), issue.getDescription(),
                issue.getSolutions(), updatedTags);
            model.updateIssue(issue, editedIssue);
            model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
            model.commitSaveIt();
        }
        return new CommandResult(MESSAGE_REFACTOR_TAG_SUCCESS);
    }
}

