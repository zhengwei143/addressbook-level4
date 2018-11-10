package seedu.saveit.ui;

import static org.junit.Assert.assertEquals;
import static seedu.saveit.testutil.EventsUtil.postNow;
import static seedu.saveit.testutil.TypicalDirectories.ISSUE_LEVEL;
import static seedu.saveit.testutil.TypicalDirectories.ROOT_LEVEL;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.ResultDisplayHandle;
import seedu.saveit.commons.events.model.DirectoryChangedEvent;
import seedu.saveit.commons.events.ui.NewResultAvailableEvent;


public class ResultDisplayTest extends GuiUnitTest {

    private static final NewResultAvailableEvent NEW_RESULT_EVENT_STUB = new NewResultAvailableEvent("Stub");
    private static final DirectoryChangedEvent  DIRECTORY_CHANGED_ROOT_EVENT_STUB =
            new DirectoryChangedEvent(ROOT_LEVEL);
    private static final DirectoryChangedEvent  DIRECTORY_CHANGED_ISSUE_EVENT_STUB =
            new DirectoryChangedEvent(ISSUE_LEVEL);

    private ResultDisplayHandle resultDisplayHandle;

    @Before
    public void setUp() {
        ResultDisplay resultDisplay = new ResultDisplay();
        uiPartRule.setUiPart(resultDisplay);

        resultDisplayHandle = new ResultDisplayHandle(resultDisplay.getRoot());
    }

    @Test
    public void display() {
        // default result text
        guiRobot.pauseForHuman();
        assertEquals("", resultDisplayHandle.getText());

        // new result received
        postNow(NEW_RESULT_EVENT_STUB);
        guiRobot.pauseForHuman();
        assertEquals(NEW_RESULT_EVENT_STUB.message, resultDisplayHandle.getText());

        //directory changed to root
        postNow(DIRECTORY_CHANGED_ROOT_EVENT_STUB);
        guiRobot.pauseForHuman();;
        assertEquals(ROOT_LEVEL.toString(), resultDisplayHandle.getDirectory());

        //directory changed to issue
        postNow(DIRECTORY_CHANGED_ISSUE_EVENT_STUB);
        guiRobot.pauseForHuman();
        assertEquals(ISSUE_LEVEL.toString(), resultDisplayHandle.getDirectory());
    }
}
