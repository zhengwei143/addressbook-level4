package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.events.BaseEvent;

/**
 * Indicates a request to change model directory.
 */
public class ChangeDirectoryRequestEvent extends BaseEvent {

    public final Directory directory;

    public ChangeDirectoryRequestEvent(Directory newDirectory) {
        this.directory = newDirectory;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
