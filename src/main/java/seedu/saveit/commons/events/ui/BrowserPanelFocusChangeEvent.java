package seedu.saveit.commons.events.ui;

import seedu.saveit.commons.events.BaseEvent;

/**
 * indicate focus changed to {@code BrowserPanel} when loading a web-page with input text-field.
 */
public class BrowserPanelFocusChangeEvent extends BaseEvent {

    public BrowserPanelFocusChangeEvent() {}

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
