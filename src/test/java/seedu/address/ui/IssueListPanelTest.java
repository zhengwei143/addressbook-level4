package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ISSUE;
import static seedu.address.testutil.TypicalIssues.getTypicalIssues;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysIssue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

import guitests.guihandles.IssueCardHandle;
import guitests.guihandles.IssueListPanelHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.Issue;
import seedu.address.storage.XmlSerializableSaveIt;


public class IssueListPanelTest extends GuiUnitTest {
    private static final ObservableList<Issue> TYPICAL_ISSUES =
            FXCollections.observableList(getTypicalIssues());

    private static final JumpToListRequestEvent JUMP_TO_SECOND_EVENT = new JumpToListRequestEvent(INDEX_SECOND_ISSUE);

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "sandbox");

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private IssueListPanelHandle issueListPanelHandle;

    @Test
    @Ignore
    public void display() {
        initUi(TYPICAL_ISSUES);

        for (int i = 0; i < TYPICAL_ISSUES.size(); i++) {
            issueListPanelHandle.navigateToCard(TYPICAL_ISSUES.get(i));
            Issue expectedIssue = TYPICAL_ISSUES.get(i);
            IssueCardHandle actualCard = issueListPanelHandle.getIssueCardHandle(i);

            assertCardDisplaysIssue(expectedIssue, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
        }
    }

    @Test
    @Ignore
    public void handleJumpToListRequestEvent() {
        initUi(TYPICAL_ISSUES);
        postNow(JUMP_TO_SECOND_EVENT);
        guiRobot.pauseForHuman();

        IssueCardHandle expectedIssue = issueListPanelHandle.getIssueCardHandle(INDEX_SECOND_ISSUE.getZeroBased());
        IssueCardHandle selectedIssue = issueListPanelHandle.getHandleToSelectedCard();
        assertCardEquals(expectedIssue, selectedIssue);
    }

    /**
<<<<<<< HEAD
     * Verifies that creating and deleting large number of persons in {@code IssueListPanel} requires lesser than
=======
     * Verifies that creating and deleting large number of issues in {@code IssueListPanel} requires lesser than
>>>>>>> Documentation
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    @Ignore
    public void performanceTest() throws Exception {
        ObservableList<Issue> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of issue cards exceeded time limit");
    }

    /**
<<<<<<< HEAD
     * Returns a list of persons containing {@code issueCount} persons that is used to populate the
=======
     * Returns a list of issues containing {@code issueCount} issues that is used to populate the
>>>>>>> Documentation
     * {@code IssueListPanel}.
     */
    private ObservableList<Issue> createBackingList(int issueCount) throws Exception {
        Path xmlFile = createXmlFileWithIssues(issueCount);
        XmlSerializableSaveIt xmlSaveIt =
                XmlUtil.getDataFromFile(xmlFile, XmlSerializableSaveIt.class);
        return FXCollections.observableArrayList(xmlSaveIt.toModelType().getIssueList());
    }

    /**
     * Returns a .xml file containing {@code issueCount} issues. This file will be deleted when the JVM terminates.
     */
    private Path createXmlFileWithIssues(int issueCount) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        builder.append("<saveit>\n");
        for (int i = 0; i < issueCount; i++) {
            builder.append("<issues>\n");
            builder.append("<statement>").append(i).append("a</statement>\n");
            builder.append("<description>000</description>\n");
            builder.append("<solutions>www.example.com remark</solutions>\n");
            builder.append("</issues>\n");
        }
        builder.append("</saveit>\n");

        Path manyIssuesFile = Paths.get(TEST_DATA_FOLDER + "manyIssues.xml");
        FileUtil.createFile(manyIssuesFile);
        FileUtil.writeToFile(manyIssuesFile, builder.toString());
        manyIssuesFile.toFile().deleteOnExit();
        return manyIssuesFile;
    }

    /**
<<<<<<< HEAD
     * Initializes {@code personListPanelHandle} with a {@code IssueListPanel} backed by {@code backingList}.
=======
     * Initializes {@code issueListPanelHandle} with a {@code IssueListPanel} backed by {@code backingList}.
>>>>>>> Documentation
     * Also shows the {@code Stage} that displays only {@code IssueListPanel}.
     */
    private void initUi(ObservableList<Issue> backingList) {
        IssueListPanel issueListPanel = new IssueListPanel(backingList);
        uiPartRule.setUiPart(issueListPanel);

<<<<<<< HEAD
        personListPanelHandle = new PersonListPanelHandle(getChildNode(issueListPanel.getRoot(),
                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
=======
        issueListPanelHandle = new IssueListPanelHandle(getChildNode(issueListPanel.getRoot(),
                IssueListPanelHandle.ISSUE_LIST_VIEW_ID));
>>>>>>> Documentation
    }
}
