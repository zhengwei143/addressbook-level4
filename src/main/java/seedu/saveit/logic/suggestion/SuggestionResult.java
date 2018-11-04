package seedu.saveit.logic.suggestion;

import java.util.LinkedList;

/**
 * Represents the result of a {@code Suggestion}
 */
public class SuggestionResult {

    public final LinkedList<SuggestionValue> values;

    // start & end positions between which to replace the String with a selected option
    public final int startPosition;
    public final int endPosition;
    public final String feedbackToUser;

    public SuggestionResult(LinkedList<SuggestionValue> values, String feedbackToUser,
            int startPosition, int endPosition) {
        this.values = values;
        this.feedbackToUser = feedbackToUser;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    /**
     * A valid result should have suggestion labels
     */
    public boolean validResult() {
        return values.size() > 0;
    }
}
