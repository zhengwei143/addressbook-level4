package seedu.saveit.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.events.ui.JumpToListRequestEvent;
import seedu.saveit.commons.events.ui.NewResultAvailableEvent;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(ResultDisplay.class);
    private static final String FXML = "ResultDisplay.fxml";
    private static final String ROOT_DIRECTORY = "../SaveIt";

    private final StringProperty displayed = new SimpleStringProperty("");
    private final StringProperty currentDirectory = new SimpleStringProperty(ROOT_DIRECTORY);

    @FXML
    private TextArea resultDisplay;

    @FXML
    private Label directory;

    public ResultDisplay() {
        super(FXML);
        resultDisplay.textProperty().bind(displayed);
        directory.textProperty().bind(currentDirectory);
        registerAsAnEventHandler(this);
    }

    @Subscribe
    private void handleNewResultAvailableEvent(NewResultAvailableEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        Platform.runLater(() -> displayed.setValue(event.message));
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        Platform.runLater(() -> currentDirectory.setValue(ROOT_DIRECTORY + "/issue " + (event.targetIndex + 1)));
    }

}
