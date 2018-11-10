package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * A handler for the {@code ResultDisplay} of the UI
 */
public class ResultDisplayHandle extends NodeHandle<Node> {

    public static final String RESULT_DISPLAY_ID = "#resultDisplay";
    public static final String DIRECTORY_FIELD_ID = "#directory";

    private final TextArea resultDisplay;
    private final Label directory;

    public ResultDisplayHandle(Node resultDisplayNode) {
        super(resultDisplayNode);
        resultDisplay = getChildNode(RESULT_DISPLAY_ID);
        directory = getChildNode(DIRECTORY_FIELD_ID);
    }

    /**
     * Returns the text in the result display.
     */
    public String getText() {
        return resultDisplay.getText();
    }

    /**
     * Returns the text in the currentDirectory in the result diaplay.
     */
    public String getDirectory() {
        return directory.getText();
    }
}
