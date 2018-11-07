package guitests.guihandles;

import java.util.ArrayList;
import java.util.List;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

/**
 * A handle to the {@code CommandBox} in the GUI.
 */
public class CommandBoxHandle extends NodeHandle<InlineCssTextArea> {

    public static final String COMMAND_INPUT_FIELD_ID = "#commandTextArea";

    public CommandBoxHandle(InlineCssTextArea commandBoxNode) {
        super(commandBoxNode);
    }

    /**
     * Returns the text in the command box.
     */
    public String getInput() {
        return getRootNode().getText();
    }

    /**
     * set the text in the command box
     */
    public void enterCommand(String command) {
        run(command);
    }


    /**
     * Enters the given command in the Command Box and presses enter.
     */
    public void run(String command) {
        click();
        guiRobot.interact(() -> getRootNode().replaceText(command));
        guiRobot.pauseForHuman();

        guiRobot.type(KeyCode.ENTER);
    }

    /**
     * return the word list that matches the styleInCSS required
     */
    public List<String> getWordListWithStyle(String styleInCSS) {
        InlineCssTextArea inputBox = (InlineCssTextArea) getRootNode();
        ArrayList<String> wordList = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        for (int pos = 0; pos < inputBox.getLength(); pos++) {
            while (pos < inputBox.getLength() && inputBox.getStyleOfChar(pos).equals(styleInCSS)) {
                sb.append(inputBox.getText().charAt(pos));
                pos++;
            }
            if (sb.length() > 0) {
                wordList.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return wordList;
    }

    /**
     * Returns the list of style classes present in the command box.
     */
    public ObservableList<String> getStyleClass() {
        return getRootNode().getStyleClass();
    }
}
