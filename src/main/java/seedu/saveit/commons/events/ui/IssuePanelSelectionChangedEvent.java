package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.events.BaseEvent;
import seedu.saveit.model.Issue;

/**
 * Represents a selection change in the Issue List Panel
 */
public class IssuePanelSelectionChangedEvent extends BaseEvent {


    private final Issue newSelection;

    public IssuePanelSelectionChangedEvent(Issue newSelection) {
        this.newSelection = newSelection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public Issue getNewSelection() {
        return newSelection;
    }
}
