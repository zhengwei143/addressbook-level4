package seedu.saveit.logic.suggestion;

/**
 * Used to represent a CustomMenuItem which should have
 *  -> a display {@code label}
 *  -> a {@code result} (to be inserted into the {@code CommandBox.commandTextArea})
 */
public class SuggestionValue {

    public final String label;
    public final String result;

    public SuggestionValue(String label, String result) {
        this.label = label;
        this.result = result;
    }
}
