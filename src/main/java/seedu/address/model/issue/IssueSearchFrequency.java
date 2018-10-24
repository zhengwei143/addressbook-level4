package seedu.address.model.issue;

/**
 * Represents an Issue's Search Frequency
 *      Each time an issue is successfully filtered through a predicate
 *      Its search frequency increments by 1
 *      Used to display search terms in order of their search frequency
 *      So that the most frequently searched issues are listed at the top
 */
public class IssueSearchFrequency {
    private Integer value;

    public IssueSearchFrequency(int frequency) {
        value = frequency;
    }

    public void increment() {
        value = value + 1;
    }

    public Integer getValue() {
        return value;
    }

    public int compare(IssueSearchFrequency other) {
        return Integer.compare(value, other.value);
    }
}
