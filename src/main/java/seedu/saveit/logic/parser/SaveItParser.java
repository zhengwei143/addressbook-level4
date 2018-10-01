package seedu.saveit.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.logic.commands.AddCommand;
import seedu.saveit.logic.commands.ClearCommand;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.DeleteCommand;
import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.ExitCommand;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.commands.HelpCommand;
import seedu.saveit.logic.commands.HistoryCommand;
import seedu.saveit.logic.commands.ListCommand;
import seedu.saveit.logic.commands.RedoCommand;
import seedu.saveit.logic.commands.SelectCommand;
import seedu.saveit.logic.commands.UndoCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.commons.core.Messages;

/**
 * Parses user input.
 */
public class SaveItParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HistoryCommand.COMMAND_WORD:
            return new HistoryCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
