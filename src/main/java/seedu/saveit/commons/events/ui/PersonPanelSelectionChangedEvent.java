package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.events.BaseEvent;
import seedu.saveit.model.issue.Issue;

/**
 * Represents a selection change in the Issue List Panel
 */
public class PersonPanelSelectionChangedEvent extends BaseEvent {


    private final Issue newSelection;

    public PersonPanelSelectionChangedEvent(Issue newSelection) {
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
