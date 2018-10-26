package seedu.saveit.ui;

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

    public void highlight(InlineCssTextArea commandTextField, String userInput) {
        int i = 0;
        while (i < userInput.length() && userInput.charAt(i) != ' ') {
            commandTextField.setStyle(i, i + 1, COMMAND_WORD_STYLE);
            i++;
        }

        while (i < userInput.length()) {
            commandTextField.setStyle(i, i + 1, PARAMETER_KEY_STYLE);

            if (userInput.charAt(i) == '\\') {
                System.out.println(userInput.charAt(i));
                while (i < userInput.length() && userInput.charAt(i) != ' ') {
                    commandTextField.setStyle(i, i + 1, PARAMETER_KEY_STYLE);
                    i++;
                }
            }
            if (i >= userInput.length()) {
                break;
            }
            commandTextField.setStyle(i, i + 1, NORMAL_STYLE);
            i++;
        }
    }

}