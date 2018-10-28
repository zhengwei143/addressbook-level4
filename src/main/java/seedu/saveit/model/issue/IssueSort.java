package seedu.saveit.model.issue;

import java.util.Comparator;

import seedu.saveit.commons.core.Messages;
import seedu.saveit.logic.commands.SortCommand;
import seedu.saveit.logic.parser.exceptions.ParseException;
import seedu.saveit.model.Issue;

/**
 * Create respective Comparator based on the command inputted.
 */
public class IssueSort {
    public static final String FREQUENCY_SORT = "freq";
    public static final String CHRONOLOGICAL_SORT = "chro";
    public static final String TAG_SORT = "tag";
    public static final String EMPTY_SORT = "";
    private static final String FREQUENCY = "search frequency";
    private static final String CHRONOLOGICAL = "chronological order";
    private static final String TAG = "first tag";
    private static final String DEFAULT = "adding order";
    private final Comparator<Issue> comparator;
    private final String sort_type;
    
    public IssueSort(String sort_type) {
        switch (sort_type) {
        case FREQUENCY_SORT:
            this.comparator = new IssueFreqComparator();
            this.sort_type = FREQUENCY;
            break;
        case CHRONOLOGICAL_SORT:
            this.comparator = new IssueChroComparator();
            this.sort_type = CHRONOLOGICAL;
            break;
        case TAG_SORT:
            this.comparator = new IssueTagComparator();
            this.sort_type = TAG;
            break;
        case EMPTY_SORT:default:
            this.comparator = null;
            this.sort_type = DEFAULT;
        }
    }

    public Comparator<Issue> getComparator() {
        return this.comparator;
    }

    public String getSortType() {
        return this.sort_type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueSort // instanceof handles nulls
                && sort_type.equals(((IssueSort) other).sort_type));
    }
}
