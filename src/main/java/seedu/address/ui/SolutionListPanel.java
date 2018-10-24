package seedu.address.ui;

import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.model.issue.Solution;

/**
 * Panel containing the list of solutions.
 */
public class SolutionListPanel extends UiPart<Region> {
    private static final String FXML = "SolutionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SolutionListPanel.class);

    @FXML
    private ListView<Solution> solutionListView;

    public SolutionListPanel(ObservableList<Solution> solutionList) {
        super(FXML);
        setConnections(solutionList);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<Solution> solutionList) {
        solutionListView.setItems(solutionList);
        solutionListView.setCellFactory(listView -> new SolutionListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        solutionListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        logger.fine("Selection in issue list panel changed to : '" + newValue + "'");
                        //raise(new PersonPanelSelectionChangedEvent(newValue));
                    }
                });
    }

    /**
     * Scrolls to the {@code PersonCard} at the {@code index} and selects it.
     */
    private void scrollTo(int index) {
        Platform.runLater(() -> {
            solutionListView.scrollTo(index);
            solutionListView.getSelectionModel().clearAndSelect(index);
        });
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        scrollTo(event.targetIndex);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Issue} using a {@code PersonCard}.
     */
    class SolutionListViewCell extends ListCell<Solution> {
        @Override
        protected void updateItem(Solution solution, boolean empty) {
            super.updateItem(solution, empty);

            if (empty || solution == null) {
                setGraphic(null);
                setText(null);
            } else {
               // setGraphic(new IssueCard(solution, getIndex() + 1).getRoot());
            }
        }
    }

}
