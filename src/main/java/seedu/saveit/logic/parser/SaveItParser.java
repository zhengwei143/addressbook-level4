package seedu.saveit.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.AddCommand;
import seedu.saveit.logic.commands.AddTagCommand;
import seedu.saveit.logic.commands.ClearCommand;
import seedu.saveit.logic.commands.Command;
import seedu.saveit.logic.commands.DeleteCommand;
import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.ExitCommand;
import seedu.saveit.logic.commands.FindByTagCommand;
import seedu.saveit.logic.commands.FindCommand;
import seedu.saveit.logic.commands.HelpCommand;
import seedu.saveit.logic.commands.HistoryCommand;
import seedu.saveit.logic.commands.HomeCommand;
import seedu.saveit.logic.commands.ListCommand;
import seedu.saveit.logic.commands.RedoCommand;
import seedu.saveit.logic.commands.RefactorTagCommand;
import seedu.saveit.logic.commands.RetrieveCommand;
import seedu.saveit.logic.commands.SelectCommand;
import seedu.saveit.logic.commands.UndoCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;

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
        case AddCommand.COMMAND_ALIAS:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
        case EditCommand.COMMAND_ALIAS:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
        case SelectCommand.COMMAND_ALIAS:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
        case DeleteCommand.COMMAND_ALIAS:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
        case ClearCommand.COMMAND_ALIAS:
            return new ClearCommand();

        case FindByTagCommand.COMMAND_WORD:
        case FindByTagCommand.COMMAND_ALIAS:
            return new FindByTagCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
        case FindCommand.COMMAND_ALIAS:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
        case ListCommand.COMMAND_ALIAS:
            return new ListCommandParser().parse(arguments);


        case HomeCommand.COMMAND_WORD:
        case HomeCommand.COMMAND_ALIAS:
            return new HomeCommand();

        case HistoryCommand.COMMAND_WORD:
        case HistoryCommand.COMMAND_ALIAS:
            return new HistoryCommand();

        case RetrieveCommand.COMMAND_WORD:
        case RetrieveCommand.COMMAND_ALIAS:
            return new RetrieveCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
        case ExitCommand.COMMAND_ALIAS:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
        case HelpCommand.COMMAND_ALIAS:
            return new HelpCommand();

        case UndoCommand.COMMAND_WORD:
        case UndoCommand.COMMAND_ALIAS:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
        case RedoCommand.COMMAND_ALIAS:
            return new RedoCommand();

        case RefactorTagCommand.COMMAND_WORD:
        case RefactorTagCommand.COMMAND_ALIAS:
            return new RefactorTagCommandParser().parse(arguments);

        case AddTagCommand.COMMAND_WORD:
        case AddTagCommand.COMMAND_ALIAS:
            return new AddTagCommandParser().parse(arguments);


            default:
            throw new ParseException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
