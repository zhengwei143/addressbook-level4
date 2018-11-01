package seedu.saveit.logic.suggestion;

/**
 * Represents a suggestion with hidden internal SuggestionLogic and the ability to evaluate
 */
public interface Suggestion {

    /**
     * Returns a {@code SuggestionResult}
     */
    SuggestionResult evaluate();
}
