package seedu.saveit.logic.commands;

/**
 * Represents a danger command that needs confirmation before being executed.
 */
public abstract class DangerCommand extends Command {
    public abstract String getCommandWord();
}
