package seedu.saveit.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.logic.CommandHistory;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;
import seedu.saveit.model.issue.Tag;
import seedu.saveit.model.issue.exceptions.DuplicateIssueException;
import seedu.saveit.model.issue.exceptions.IssueNotFoundException;

/**
 * To rename or remove a specific tag for all entries with that tag.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "addtag";
    public static final String COMMAND_ALIAS = "at";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Add tags success";
    public static final String MESSAGE_DUPLICATE_TAG = "No updated tags due to duplicate tags";

    public static final String MESSAGE_ADD_TAG_HIGHER_BOUND_FAILURE =
        "Your index should not be more than the number of your issues";


    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Add the tag to an issue by the index number used "
        + "in the last issue listing.\n"
        + "Parameters: INDEX... TAG...(INDEX must be positive integer. [INDEX] can be set as a range.\n"
        + "Example: " + COMMAND_WORD + " 1 t/java\n"
        + "Example: " + COMMAND_WORD + " 1-5 t/important t/TIL\n"
        + "Example: " + COMMAND_WORD + " 1 2 t/important t/TIL";


    private final Set<Tag> tagList;
    private final Set<Index> index;


    /**
     * @param index the issue index that tag will be add
     * @param tagList the new tag(s) to be added to saveIt
     */
    public AddTagCommand(Set<Index> index, Set<Tag> tagList) {
        requireNonNull(tagList);
        this.index = index;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws ParseException, CommandException {
        requireNonNull(model);
        Directory currentDirectory = model.getCurrentDirectory();
        if (!currentDirectory.isRootLevel()) {
            throw new CommandException(Messages.MESSAGE_WRONG_DIRECTORY);
        }

        int numOfIssues = model.getFilteredAndSortedIssueList().size();
        checkHigherBound(numOfIssues, index);
        Set<Issue> issueToEdit = new LinkedHashSet<>();
        try {
            List<Issue> lastShownList = model.getFilteredAndSortedIssueList();
            index.forEach(issueIndex -> {
                issueToEdit.add(lastShownList.get(issueIndex.getZeroBased()));
            });

            model.addTag(issueToEdit, tagList);
            model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
            model.commitSaveIt();
        } catch (DuplicateIssueException die) {
            throw new CommandException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        } catch (IssueNotFoundException infe) {
            throw new CommandException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_DUPLICATE_TAG));
        }

        return new CommandResult(MESSAGE_ADD_TAG_SUCCESS);
    }

    /**
     * check if the index set exceeds the higher bound of the issues
     */
    public void checkHigherBound(int higherBound, Set<Index> index) throws ParseException {
        Iterator<Index> it = index.iterator();

        while (it.hasNext()) {
            int next = it.next().getZeroBased();
            if (next >= higherBound) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_ADD_TAG_HIGHER_BOUND_FAILURE));
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddTagCommand) // instanceof handles nulls
            || (index.equals(((AddTagCommand) other).index)) //state check
            || (tagList.equals(((AddTagCommand) other).tagList)); //state check
    }


}

