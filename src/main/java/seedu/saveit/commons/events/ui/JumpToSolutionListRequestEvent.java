package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.commons.events.BaseEvent;

/**
 * Indicates a request to jump to the list of issues
 */
public class JumpToSolutionListRequestEvent extends BaseEvent {

    public final int targetIndex;

    public JumpToSolutionListRequestEvent(Index targetIndex) {
        this.targetIndex = targetIndex.getZeroBased();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
