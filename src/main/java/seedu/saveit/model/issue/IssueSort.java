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
    private final Comparator<Issue> comparator;

    public IssueSort(String sortType) throws ParseException {
        switch (sortType) {
        case FREQUENCY_SORT:
            this.comparator = new IssueFreqComparator();
            break;
        case CHRONOLOGICAL_SORT:
            this.comparator = new IssueChroComparator();
            break;
        case TAG_SORT:
            this.comparator = new IssueTagComparator();
            break;
        default:
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    public Comparator<Issue> getComparator() {
        return this.comparator;
    }

}
