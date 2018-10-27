package seedu.saveit.ui;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import org.fxmisc.richtext.InlineCssTextArea;

/**
 * Manager of command highlighter
 */
public class CommandHighlightManager {

    public static final String COMMAND_WORD_STYLE = "-fx-fill: #f4ad42;";
    public static final String PARAMETER_KEY_STYLE = "-fx-fill: #ffff00;";
    public static final String SOLUTION_LINK_STYLE = "-fx-fill: #1a75ff;"; // blue"
    public static final String SOLUTION_REMARK_STYLE = "-fx-fill: #55ae47;"; // green
    public static final String DESCRIPTION_STYLE = "-fx-fill: #e68a00;"; // orange
    public static final String STATEMENT_STYLE = "-fx-fill: #cd5c5c;"; // red
    public static final String TAGS_STYLE = "-fx-fill: #9febf2;"; // another blue
    public static final String NEW_TAG_STYLE = "-fx-fill: #289096;"; // some blue
    public static final String NORMAL_STYLE = "-fx-fill: #aaf9ff;";
    private static CommandHighlightManager instance;

    static CommandHighlightManager getInstance() {
        if (instance == null) {
            instance = new CommandHighlightManager();
        }
        return instance;
    }

    /**
     * highlight user input.
     */
    public void highlight(InlineCssTextArea commandTextField) {
        String userInput = commandTextField.getText();
        int position = 0;

        while (position < userInput.length() && userInput.charAt(position) != ' ') {
            commandTextField.setStyle(position, position + 1, COMMAND_WORD_STYLE);
            position++;
        }

        // highlight the following parameters, which are key-value pairs
        while (position < userInput.length()) {
            // TODO: differentiate index
            if (userInput.charAt(position) == '2') {
                commandTextField.setStyle(position, position + 1, SOLUTION_REMARK_STYLE);
            }

            if (isParameter(userInput, position)) {
                StringBuilder keyBuilder = new StringBuilder();
                commandTextField.setStyle(position - 1, position + 1, PARAMETER_KEY_STYLE);
                keyBuilder.append(userInput.charAt(position));
                position++;
            }

            if (position >= userInput.length()) {
                break;
            }

            commandTextField.setStyle(position, position + 1, NORMAL_STYLE);
            position++;
        }
    }

    /**
     * check if user input parameters
     *
     * @return true if parameter, otherwise false
     */
    private boolean isParameter(String userInput, int position) {
        StringBuilder input = new StringBuilder();
        input.append(userInput.charAt(position - 1));
        input.append(userInput.charAt(position));
        String inputCheck = input.toString();
        if (isParamter(inputCheck)) {
            return true;
        }
        return false;
    }

    /**
     * check if find the parameter
     *
     * @param inputCheck every two consecutive characters.
     * @return true if parameter, otherwise false
     */
    private boolean isParamter(String inputCheck) {
        return inputCheck.equals(PREFIX_STATEMENT.toString()) || inputCheck.equals(PREFIX_SOLUTION_LINK.toString())
            || inputCheck.equals(PREFIX_REMARK.toString()) || inputCheck.equals(PREFIX_DESCRIPTION.toString())
            || inputCheck.equals(PREFIX_TAG.toString()) || inputCheck.equals(PREFIX_NEW_TAG.toString());
    }
}
