package seedu.saveit.ui;

import java.net.URL;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;
import seedu.saveit.MainApp;
import seedu.saveit.commons.core.LogsCenter;
import seedu.saveit.commons.events.ui.BrowserPanelFocusChangeEvent;
import seedu.saveit.commons.events.ui.SolutionPanelSelectionChangedEvent;
import seedu.saveit.model.issue.Solution;

/**
 * The Browser Panel of the App.
 */
public class BrowserPanel extends UiPart<Region> {

    public static final String DEFAULT_PAGE = "default.html";
    public static final String JAVADOC_PAGE =
            "https://docs.oracle.com/javase/7/docs/api/";

    private static final String FXML = "BrowserPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private boolean isNewPageLoaded;

    @FXML
    private WebView browser;

    public BrowserPanel() {
        super(FXML);

        // To prevent triggering events for typing inside the loaded Web page.
        getRoot().setOnKeyPressed(Event::consume);

        isNewPageLoaded = false;
        loadDefaultPage();
        registerAsAnEventHandler(this);
        browser.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && isNewPageLoaded) {
                raise(new BrowserPanelFocusChangeEvent());
                isNewPageLoaded = false;
            }
        });
        browser.setFocusTraversable(false);
    }


    private void loadSolutionPage(Solution solution) {
        loadPage(solution.solutionLink.getValue());
    }

    public void loadPage(String url) {
        Platform.runLater(() -> browser.getEngine().load(url));
    }

    /**
     * Loads a default HTML file with a background that matches the general theme.
     */
    private void loadDefaultPage() {
        URL defaultPage = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        loadPage(defaultPage.toExternalForm());
    }

    /**
     * Frees resources allocated to the browser.
     */
    public void freeResources() {
        browser = null;
    }

    @Subscribe
    private void handleSolutionPanelSelectionChangedEvent(SolutionPanelSelectionChangedEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        loadSolutionPage(event.getNewSelection());
        isNewPageLoaded = true;
    }
}
