package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.issue.Tag;

/**
 * Finds and lists all issues in saveIt whose name contains any of the argument keywords. Keyword matching is case
 * insensitive.
 */
public class RefactorTagCommand extends Command {

    public static final String COMMAND_WORD = "refactorTag";
    public static final String MESSAGE_REFACTOR_TAG_SUCCESS = "Success";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all issues whose statements contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Tag oldTag;
    private final Tag newTag;

    /**
     * @param oldTag the tag will be replaced
     * @param newTag the new tag replace original one
     */
    public RefactorTagCommand(Tag oldTag, Tag newTag) {
        requireNonNull(oldTag);
        System.out.println("old Tag " + oldTag.toString());
        System.out.println("new Tag " + newTag.toString());
        this.oldTag = oldTag;
        this.newTag = newTag;
    }

    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        return new CommandResult(MESSAGE_REFACTOR_TAG_SUCCESS);
    }

}
