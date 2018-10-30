package seedu.saveit.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.saveit.testutil.EventsUtil.postNow;
import static seedu.saveit.ui.BrowserPanel.DEFAULT_PAGE;
import static seedu.saveit.ui.BrowserPanel.JAVADOC_PAGE;
import static seedu.saveit.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;
import seedu.saveit.MainApp;
import seedu.saveit.commons.events.ui.SolutionPanelSelectionChangedEvent;

public class BrowserPanelTest extends GuiUnitTest {
    private SolutionPanelSelectionChangedEvent selectionChangedEventStub;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        //selectionChangedEventStub = new SolutionPanelSelectionChangedEvent(ALICE);

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    @Ignore
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of an issue
        postNow(selectionChangedEventStub);
        URL expectedIssueUrl = new URL(JAVADOC_PAGE);

        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedIssueUrl, browserPanelHandle.getLoadedUrl());
    }
}
