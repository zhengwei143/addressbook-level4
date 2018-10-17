package seedu.address.model.issue;

public class IssueSearchFrequency {
    public int value;

    public IssueSearchFrequency(int frequency) {
        value = frequency;
    }

    public void increment() {
        value = value + 1;
    }

    public int compare(IssueSearchFrequency other) {
        return Integer.compare(value, other.value);
    }
}
