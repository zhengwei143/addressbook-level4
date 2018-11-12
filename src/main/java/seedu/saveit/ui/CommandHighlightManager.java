package seedu.saveit.ui;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK_STRING;
import static seedu.saveit.model.util.SampleDataUtil.isCommandWordNeedIndex;
import static seedu.saveit.model.util.SampleDataUtil.isPrefixParameter;

import org.fxmisc.richtext.InlineCssTextArea;

/**
 * Manager of command highlighter
 */
public class CommandHighlightManager {

    public static final String STYLE_COMMAND_WORD = "-fx-fill: #e2a03d;";
    public static final String STYLE_PARAMETER_KEY = "-fx-fill: #ffff00;";
    public static final String STYLE_INDEX = "-fx-fill: #55ae47;";
    public static final String STYLE_NORMAL_VALUE = "-fx-fill: #42c3f4;";

    /**
     * highlight user input in different colors.
     */
    public static void highlight(InlineCssTextArea commandTextField) {
        String userInput = commandTextField.getText();
        StringBuilder commandWord = new StringBuilder();
        int position = 0;
        boolean indexHighlighted = false;
        boolean indexNeedToHighlight;
        boolean isSolutionLink = false;

        // if there are space chars before command word, pos++
        while (isShorterThanInput(userInput, position) && isSpace(userInput, position)) {
            position++;
        }

        // highlight command word
        while (isShorterThanInput(userInput, position) && !isSpace(userInput, position)) {
            commandWord.append(userInput.charAt(position));
            commandTextField.setStyle(position, position + 1, STYLE_COMMAND_WORD);
            position++;
        }

        // check for some command word that does not require index
        indexNeedToHighlight = checkCommandWord(commandWord);

        // highlight the following parameters, which are key-value pairs
        while (isShorterThanInput(userInput, position)) {
            while (indexNeedToHighlight && !indexHighlighted && isIndex(userInput, position) && !isSpace(userInput,
                position)) {
                commandTextField.setStyle(position, position + 1, STYLE_INDEX);
                position++;
            }

            if (!isSolutionLink && isShorterThanInput(userInput, position) && isParameter(userInput, position)) {
                commandTextField.setStyle(position - 1, position + 1, STYLE_PARAMETER_KEY);
                position++;
                indexHighlighted = true;
            }

            isSolutionLink = isSolutionLinkStart(userInput, position, isSolutionLink);

            if (!isShorterThanInput(userInput, position)) {
                break;
            }

            commandTextField.setStyle(position, position + 1, STYLE_NORMAL_VALUE);
            position++;

            isSolutionLink = isSolutionLinkEnd(userInput, position, isSolutionLink);
        }
    }


    /**
     * This method is used to check whether user is currently input the solution link, return true if solution link
     * prefix is found.
     */
    private static boolean isSolutionLinkStart(String userInput, int position, boolean isSolutionLink) {
        if (userInput.charAt(position - 1) == '/') {
            String inputCheck = getString(userInput, position, 2, position - 1);
            if (inputCheck.equals(PREFIX_SOLUTION_LINK_STRING)) {
                isSolutionLink = true;
            }
        }
        return isSolutionLink;
    }

    /**
     * This method is used to check whether the user finishes solution link by checking whether space is input, return
     * false if it is not solution link, otherwise return true.
     */
    private static boolean isSolutionLinkEnd(String userInput, int position, boolean isSolutionLink) {
        if (isSolutionLink && isSpace(userInput, position - 1)) {
            isSolutionLink = false;
        }
        return isSolutionLink;
    }

    /**
     * check if the commandWord requires index, return true if yes, otherwise false
     */
    private static boolean checkCommandWord(StringBuilder userInput) {
        return isCommandWordNeedIndex(userInput.toString());
    }

    /**
     * check if the this position character is space, return true if yes, otherwise false
     */
    private static boolean isSpace(String userInput, int position) {
        return userInput.charAt(position) == ' ';
    }

    /**
     * check if the position is within the userInput length, return true if yes, otherwise false
     */
    private static boolean isShorterThanInput(String userInput, int position) {
        return position < userInput.length();
    }

    private static boolean isIndex(String userInput, int position) {
        return isShorterThanInput(userInput, position) && Character.isDigit(userInput.charAt(position));
    }

    /**
     * check if the character is parameter
     * @return true if parameter, otherwise false
     */
    private static boolean isParameter(String userInput, int position) {
        if (userInput.charAt(position) == '/') {
            String inputCheck = getString(userInput, position, 1, position);
            if (isPrefixParameter(inputCheck)) {
                return true;
            }
        }
        return false;
    }

    private static String getString(String userInput, int position, int i, int i2) {
        StringBuilder input = new StringBuilder();
        input.append(userInput.charAt(position - i));
        input.append(userInput.charAt(i2));
        return input.toString();
    }
}
