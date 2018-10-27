package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.commons.events.BaseEvent;

/**
 * Indicates a request to jump to the list of issues
 */
public class JumpToListRequestEvent extends BaseEvent {

    public final int targetIndex;

    public JumpToListRequestEvent(Index targetIndex) {
        this.targetIndex = targetIndex.getZeroBased();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
