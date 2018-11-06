package seedu.saveit.logic.suggestion;

import java.util.LinkedList;

/**
 * Represents the result of a {@code Suggestion}
 */
public class SuggestionResult {

    private final LinkedList<SuggestionValue> values;

    // start & end positions between which to replace the String with a selected option
    private final int startPosition;
    private final int endPosition;
    private final String feedbackToUser;

    public SuggestionResult(LinkedList<SuggestionValue> values, String feedbackToUser,
            int startPosition, int endPosition) {
        this.values = values;
        this.feedbackToUser = feedbackToUser;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public LinkedList<SuggestionValue> getSuggestionValue() {
        return values;
    }

    /**
     * A valid result should have suggestion labels
     */
    public boolean validResult() {
        return values.size() > 0;
    }
}
