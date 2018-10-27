package seedu.saveit.ui;

import java.util.logging.Logger;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.events.ui.NewResultAvailableEvent;
import seedu.saveit.logic.ListElementPointer;
import seedu.saveit.logic.Logic;
import seedu.saveit.logic.commands.CommandResult;
import seedu.saveit.logic.commands.exceptions.CommandException;
import seedu.saveit.logic.parser.exceptions.ParseException;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";

    private static final String FXML = "CommandBox.fxml";

    private final Logger logger = LogsCenter.getLogger(CommandBox.class);
    private final Logic logic;
    private ListElementPointer historySnapshot;


    @FXML
    private InlineCssTextArea commandTextField;

    public CommandBox(Logic logic) {
        super(FXML);
        this.logic = logic;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((observable, oldValue, newValue)
            -> highlight(observable, oldValue, newValue));

        historySnapshot = logic.getHistorySnapshot();
    }

    /**
     * highlight different parameters for user friendly input command line
     * @param value
     * @param oldValue
     * @param newValue
     */
    private void highlight(ObservableValue<?> value, String oldValue, String newValue) {
        String userInput = newValue;
        CommandHighlightManager highlightManager = CommandHighlightManager.getInstance();
        highlightManager.highlight(commandTextField);
    }


    /**
     * Handles the key press event, {@code keyEvent}.
     */
    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case UP:
            // As up and down buttons will alter the position of the caret,
            // consuming it causes the caret's position to remain unchanged
            keyEvent.consume();
            navigateToPreviousInput();
            break;
        case DOWN:
            keyEvent.consume();
            navigateToNextInput();
            break;
        case ENTER:
            keyEvent.consume();
            handleCommandEntered();
            break;
        default:
            // let JavaFx handle the keypress
        }
    }

    /**
     * Updates the text field with the previous input in {@code historySnapshot}, if there exists a previous input in
     * {@code historySnapshot}
     */
    private void navigateToPreviousInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasPrevious()) {
            return;
        }
        setCommandInput(historySnapshot.previous());
    }

    /**
     * Updates the text field with the next input in {@code historySnapshot}, if there exists a next input in {@code
     * historySnapshot}
     */
    private void navigateToNextInput() {
        assert historySnapshot != null;
        if (!historySnapshot.hasNext()) {
            return;
        }

        setCommandInput(historySnapshot.next());
    }


    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        try {
            CommandResult commandResult = logic.execute(commandTextField.getText().trim().replaceAll("\\r|\\n", ""));
            initHistory();
            historySnapshot.next();
            // process result of the command
            setCommandInput("");
            logger.info("Result: " + commandResult.feedbackToUser);
            raise(new NewResultAvailableEvent(commandResult.feedbackToUser));

        } catch (CommandException | ParseException e) {
            initHistory();
            // handle command failure
            setCommandInput(commandTextField.getText());
            setStyleToIndicateCommandFailure();
            logger.info("Invalid command: " + commandTextField.getText());
            raise(new NewResultAvailableEvent(e.getMessage()));


        }
    }

    private void setCommandInput(String string) {
        commandTextField.clear();
        commandTextField.appendText(string.replaceAll("\\r|\\n", ""));

        // move the cursor to the end of the input string
        commandTextField.moveTo(commandTextField.getText().length());
    }

    /**
     * Initializes the history snapshot.
     */
    private void initHistory() {
        historySnapshot = logic.getHistorySnapshot();
        // add an empty string to represent the most-recent end of historySnapshot, to be shown to
        // the user if she tries to navigate past the most-recent end of the historySnapshot.
        historySnapshot.add("");
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        // TODO: Check why it does not set style successfully?
        ObservableList<String> styleClass = commandTextField.getStyleClass();
        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }
        styleClass.add(ERROR_STYLE_CLASS);
    }

}
