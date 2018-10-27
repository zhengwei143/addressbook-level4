package seedu.saveit.ui;

import static seedu.saveit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_SOLUTION_LINK;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_STATEMENT;
import static seedu.saveit.logic.parser.CliSyntax.PREFIX_TAG;

import org.fxmisc.richtext.InlineCssTextArea;

public class CommandHighlightManager {

    public static final String COMMAND_WORD_STYLE = "-fx-fill: #f4ad42;";
    public static final String PARAMETER_KEY_STYLE = "-fx-fill: #ffffff;";
    public static final String SOLUTION_LINK_STYLE = "-fx-fill: #1a75ff;";  // blue"
    public static final String SOLUTION_REMARK_STYLE = "-fx-fill: #55ae47;";  // green
    public static final String DESCRIPTION_STYLE = "-fx-fill: #e68a00;";  // orange
    public static final String STATEMENT_STYLE = "-fx-fill: #cd5c5c;";  // red
    public static final String TAGS_STYLE = "-fx-fill: #9febf2;";  // another blue
    public static final String NEW_TAG_STYLE = "-fx-fill: #289096;";  // some blue
    public static final String NORMAL_STYLE = "-fx-fill: #f9ed02;";
    private static CommandHighlightManager instance;

    static CommandHighlightManager getInstance() {
        if (instance == null) {
            instance = new CommandHighlightManager();
        }
        return instance;
    }

    public void highlight(InlineCssTextArea commandTextField) {
        String userInput = commandTextField.getText();
        int position = 0;

        while (position < userInput.length() && userInput.charAt(position) != ' ') {
            commandTextField.setStyle(position, position + 1, COMMAND_WORD_STYLE);
            position++;
        }

        // highlight the following parameters, which are key-value pairs
        String key = "";
        while (position < userInput.length()) {
            if (userInput.charAt(position) == '/') {
                StringBuilder keyBuilder = new StringBuilder();
                while (position < userInput.length() && userInput.charAt(position) != ' ') {
                    commandTextField.setStyle(position, position + 1, NEW_TAG_STYLE);
                    keyBuilder.append(userInput.charAt(position));
                    position++;
                }
                key = keyBuilder.toString();
            }
            if (position >= userInput.length()) {
                break;
            }

            highlightCharacterOfParameterValue(position, key, commandTextField);
            position++;
        }
    }

    private void highlightCharacterOfParameterValue(int start, String key, InlineCssTextArea commandTextField) {
        System.out.println("key " + key);
        if (key.equals(PREFIX_STATEMENT.toString())) {
            commandTextField.setStyle(start, start + 1, STATEMENT_STYLE);
        } else if (key.equals(PREFIX_DESCRIPTION.toString())) {
            System.out.println("PREFIX_DESCRIPTION");
            commandTextField.setStyle(start, start + 1, DESCRIPTION_STYLE);
        } else if (key.equals(PREFIX_SOLUTION_LINK.toString())) {
            System.out.println("PREFIX_SOLUTION_LINK");
            commandTextField.setStyle(start, start + 1, SOLUTION_LINK_STYLE);
        } else if (key.equals(PREFIX_REMARK.toString())) {
            System.out.println("PREFIX_REMARK");
            commandTextField.setStyle(start, start + 1, SOLUTION_REMARK_STYLE);
        } else if (key.equals(PREFIX_NEW_TAG.toString())) {
            System.out.println("PREFIX_NEW_TAG");
            commandTextField.setStyle(start, start + 1, TAGS_STYLE);
        } else if (key.equals(PREFIX_TAG.toString())) {
            System.out.println("PREFIX_TAG");
            commandTextField.setStyle(start, start + 1, NEW_TAG_STYLE);
        } else {
            System.out.println();
            commandTextField.setStyle(start, start + 1, NORMAL_STYLE);
        }
    }

}