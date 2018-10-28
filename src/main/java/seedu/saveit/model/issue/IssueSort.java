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
    private static final String FREQUENCY_SORT = "freq";
    private static final String CHRONOLOGICAL_SORT = "chro";
    private static final String TAG_SORT = "tag";
    private static final String EMPTY_SORT = "";
    private static final String FREQUENCY = "search frequency";
    private static final String CHRONOLOGICAL = "chronological";
    private static final String TAG = "first tag";
    private static final String DEFAULT = CHRONOLOGICAL;
    private final Comparator<Issue> comparator;
    private final String sort_type;

    public IssueSort(String sortType) throws ParseException {
        switch (sortType) {
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
        case EMPTY_SORT:
            this.comparator = null;
            this.sort_type = DEFAULT;
            break;
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    public Comparator<Issue> getComparator() {
        return this.comparator;
    }

    public String getSortType() {
        return this.sort_type;
    }

}
