package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.events.BaseEvent;
import seedu.saveit.model.issue.Solution;

/**
 * Represents a selection change in the Solution List Panel
 */
public class SolutionPanelSelectionChangedEvent extends BaseEvent {


    private final Solution newSelection;

    public SolutionPanelSelectionChangedEvent(Solution newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Solution getNewSelection() {
        return newSelection;
    }
}
