package seedu.saveit.ui;

import static guitests.guihandles.WebViewUtil.waitUntilBrowserLoaded;
import static org.junit.Assert.assertEquals;
import static seedu.saveit.testutil.EventsUtil.postNow;
import static seedu.saveit.testutil.TypicalSolutions.REPO;
import static seedu.saveit.ui.BrowserPanel.DEFAULT_PAGE;
import static seedu.saveit.ui.UiPart.FXML_FILE_FOLDER;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;
import seedu.saveit.MainApp;
import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;
import seedu.saveit.commons.events.ui.ChangeDirectoryRequestEvent;
import seedu.saveit.commons.events.ui.JumpToListRequestEvent;
import seedu.saveit.commons.events.ui.SolutionPanelSelectionChangedEvent;

public class BrowserPanelTest extends GuiUnitTest {
    private SolutionPanelSelectionChangedEvent selectionChangedEventStub;
    private JumpToListRequestEvent jumpToListRequestEventStub;
    private ChangeDirectoryRequestEvent changeDirectoryRequestEventStub;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStub = new SolutionPanelSelectionChangedEvent(REPO);
        jumpToListRequestEventStub = new JumpToListRequestEvent(Index.fromOneBased(1));
        changeDirectoryRequestEventStub = new ChangeDirectoryRequestEvent(new Directory(0, 0));

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    public void display() throws Exception {
        // default web page
        URL expectedDefaultPageUrl = MainApp.class.getResource(FXML_FILE_FOLDER + DEFAULT_PAGE);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        // associated web page of an solution
        postNow(selectionChangedEventStub);
        URL expectedSolutionUrl = new URL(REPO.getLink().getValue());

        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedSolutionUrl, browserPanelHandle.getLoadedUrl());

        postNow(changeDirectoryRequestEventStub);
        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

        postNow(selectionChangedEventStub);
        postNow(jumpToListRequestEventStub);
        waitUntilBrowserLoaded(browserPanelHandle);
        assertEquals(expectedDefaultPageUrl, browserPanelHandle.getLoadedUrl());

    }
}
