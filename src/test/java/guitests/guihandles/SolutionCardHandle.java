package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.saveit.model.issue.Solution;

/**
 * Provides a handle to an solution card in the solution list panel.
 */
public class SolutionCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String LINK_FIELD_ID = "#link";
    private static final String REMARK_FIELD_ID = "#remark";

    private final Label idLabel;
    private final Label linkLabel;
    private final Label remarkLabel;


    public SolutionCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        linkLabel = getChildNode(LINK_FIELD_ID);
        remarkLabel = getChildNode(REMARK_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getLink() {
        return linkLabel.getText();
    }

    public String getRemark() {
        return remarkLabel.getText();
    }


    /**
     * Returns true if this handle contains {@code solution}.
     */
    public boolean equals(Solution solution) {
        return getLink().equals(solution.getLink().getValue())
                && getRemark().equals(solution.getRemark().toString());
    }
}
