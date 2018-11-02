package seedu.saveit.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;
    private final int position;

    public Prefix(String prefix) {
        this.prefix = prefix;
        // In this case, the position is just assigned to 0
        // equality is not affected by the value of the position
        this.position = 0;
    }

    /**
     * Additional parameter used to (optionally) store the position of the
     *  {@code prefix} in the argument string
     *  to dynamically control the position of the autosuggestion box
     */
    public Prefix(String prefix, int position) {
        this.prefix = prefix;
        this.position = position;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Prefix)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Prefix otherPrefix = (Prefix) obj;
        return otherPrefix.getPrefix().equals(getPrefix());
    }
}
