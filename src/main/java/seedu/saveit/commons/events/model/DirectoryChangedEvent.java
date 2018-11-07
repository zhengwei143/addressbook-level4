package seedu.saveit.commons.events.model;

import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.events.BaseEvent;

/**
 * Indicates model directory was changed.
 */
public class DirectoryChangedEvent extends BaseEvent {

    public final Directory directory;

    public DirectoryChangedEvent(Directory newDirectory) {
        this.directory = newDirectory;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
