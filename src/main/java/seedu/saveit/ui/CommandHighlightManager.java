//package seedu.saveit.ui;
//
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_NEW_TAG;
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK;
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK;
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
//import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;
//
//import org.fxmisc.richtext.InlineCssTextArea;
//
///**
// * Manager of command highlighter
// */
//public class CommandHighlightManager {
//
//    public static final String STYLE_COMMAND_WORD = "-fx-fill: #f4ad42;";
//    public static final String STYLE_PARAMETER_KEY = "-fx-fill: #ffff00;";
//    public static final String STYLE_INDEX = "-fx-fill: #55ae47;";
//    public static final String STYLE_NORMAL_VALUE = "-fx-fill: #42c3f4;";
//    private static CommandHighlightManager instance;
//
//    static CommandHighlightManager getInstance() {
//        if (instance == null) {
//            instance = new CommandHighlightManager();
//        }
//        return instance;
//    }
//
//    /**
//     * highlight user input.
//     */
//    public void highlight(InlineCssTextArea commandTextField) {
//        String userInput = commandTextField.getText();
//        int position = 0;
//
//        while (position < userInput.length() && userInput.charAt(position) != ' ') {
//            commandTextField.setStyle(position, position + 1, STYLE_COMMAND_WORD);
//            position++;
//        }
//        System.out.println("----start position -- " + position);
//        // highlight the following parameters, which are key-value pairs
//        while (position < userInput.length()) {
//            // TODO: differentiate index
//            // string builder to check if it is number, ' 12 ', have space front and end
//            if (userInput.charAt(position - 1) == ' ') {
////                System.out.println("confirm needs to change " + position);
////                commandTextField.setStyle(position, position+1, STYLE_INDEX);
////                position++;
////                System.out.println("---pos " + position);
////                System.out.println("userInput: " + userInput);
////                System.out.println("----" + userInput.charAt(position - 1));
//                position+=1;
////                System.out.println("inside if ");
//                while (isDigit(userInput, position)) {
////                    System.out.println("inside while loop");
//                    commandTextField.setStyle(position-1, position, STYLE_INDEX);
//                    position++;
//                }
//            }
//
//            System.out.println("after index " + position);
//
////            if (isParameter(userInput, position)) {
////                System.out.println("first in index " + position);
////                commandTextField.setStyle(position-2, position , STYLE_PARAMETER_KEY);
////                position++;
////            }
//            if (position >= userInput.length()) {
//                break;
//            }
//
//            commandTextField.setStyle(position, position + 1, STYLE_NORMAL_VALUE);
//            position++;
//        }
//    }
//
//    private boolean isDigit(String userInput, int position) {
////        System.out.println("***" +userInput.charAt(position-1));
////        System.out.println("%%% " + position);
////        System.out.println("!!!length" + userInput.length());
//        return Character.isDigit(userInput.charAt(position-1));
//    }
//
//    private boolean isIndex(String userInput, int position) {
//        return userInput.charAt(position) == ' ' && userInput.charAt(position + 1) == '2';
//    }
//
//    /**
//     * check if user input parameters
//     *
//     * @return true if parameter, otherwise false
//     */
//    private boolean isParameter(String userInput, int position) {
//        StringBuilder input = new StringBuilder();
//
//        input.append(userInput.charAt(position - 2));
//        System.out.println("check point");
//        input.append(userInput.charAt(position - 1));
//        System.out.println("string maker " + input.toString());
//        String inputCheck = input.toString();
//        if (isParameter(inputCheck)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * check if find the parameter
//     *
//     * @param inputCheck every two consecutive characters.
//     * @return true if parameter, otherwise false
//     */
//    private boolean isParameter(String inputCheck) {
//        return inputCheck.equals(PREFIX_STATEMENT.toString()) || inputCheck.equals(PREFIX_SOLUTION_LINK.toString())
//            || inputCheck.equals(PREFIX_REMARK.toString()) || inputCheck.equals(PREFIX_DESCRIPTION.toString())
//            || inputCheck.equals(PREFIX_TAG.toString()) || inputCheck.equals(PREFIX_NEW_TAG.toString());
//    }
//}


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

    public static final String STYLE_COMMAND_WORD = "-fx-fill: #f4ad42;";
    public static final String STYLE_PARAMETER_KEY = "-fx-fill: #ffff00;";
    public static final String STYLE_INDEX = "-fx-fill: #55ae47;";
    public static final String STYLE_NORMAL_VALUE = "-fx-fill: #42c3f4;";
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
            commandTextField.setStyle(position, position + 1, STYLE_COMMAND_WORD);
            position++;
        }
        System.out.println("----start position -- " + position);
        // highlight the following parameters, which are key-value pairs
        while (position < userInput.length()) {
            // TODO: differentiate index
            // string builder to check if it is number, ' 12 ', have space front and end
            if (Character.isDigit(userInput.charAt(position))) {
                System.out.println("confirm needs to change " + position);
                commandTextField.setStyle(position, position+1, STYLE_INDEX);
                position++;
            }
            System.out.println("after index " + position);

            if (isParameter(userInput, position)) {
                System.out.println("first in index " + position);
                commandTextField.setStyle(position-2, position , STYLE_PARAMETER_KEY);
                position++;
            }
            if (position >= userInput.length()) {
                break;
            }

            commandTextField.setStyle(position, position + 1, STYLE_NORMAL_VALUE);
            position++;
        }
    }

    private boolean isIndex(String userInput, int position) {
        return userInput.charAt(position) == ' ' && userInput.charAt(position+1) == '2';
    }

    /**
     * check if user input parameters
     *
     * @return true if parameter, otherwise false
     */
    private boolean isParameter(String userInput, int position) {
        StringBuilder input = new StringBuilder();

        input.append(userInput.charAt(position - 2));
        System.out.println("check point");
        input.append(userInput.charAt(position -1));
        System.out.println("string maker " + input.toString());
        String inputCheck = input.toString();
        if (isParameter(inputCheck)) {
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
    private boolean isParameter(String inputCheck) {
        return inputCheck.equals(PREFIX_STATEMENT.toString()) || inputCheck.equals(PREFIX_SOLUTION_LINK.toString())
            || inputCheck.equals(PREFIX_REMARK.toString()) || inputCheck.equals(PREFIX_DESCRIPTION.toString())
            || inputCheck.equals(PREFIX_TAG.toString()) || inputCheck.equals(PREFIX_NEW_TAG.toString());
    }
}