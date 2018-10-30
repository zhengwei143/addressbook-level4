package guitests.guihandles;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;

/**
 * A handle to the {@code CommandBox} in the GUI.
 */
public class CommandBoxHandle extends NodeHandle<InlineCssTextArea> {

    public static final String COMMAND_INPUT_FIELD_ID = "#commandTextField";

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
     * Enters the given command in the Command Box and presses enter.
     */
    public void run(String command) {
        click();
        guiRobot.interact(() -> getRootNode().replaceText(command));
        guiRobot.pauseForHuman();

        guiRobot.type(KeyCode.ENTER);
    }

    /**
     * Returns the list of style classes present in the command box.
     */
    public ObservableList<String> getStyleClass() {
        return getRootNode().getStyleClass();
    }
}
