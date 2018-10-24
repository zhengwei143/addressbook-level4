package seedu.saveit.model.issue.solution;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a Issue's solution link in saveit.
 */
public class SolutionLink {

    public static final String MESSAGE_SOLUTION_LINK_CONSTRAINTS =
            "SolutionLinks can only take a validate url.";

    public final String value;

    private URL url = null;

    /**
     * Construct a new solution link.
     * Since the the link value will always be checked before creating new solution link, MalformedURLException
     * is redundant.
     * @param value url to the solution website.
     */
    public SolutionLink(String value) {
        this.value = value;
        try {
            this.url = new URL(value);
        } catch (MalformedURLException e) {
            // redundant
        }
    }

    /**
     * Returns if a given string is a valid URL.
     * Not sure if this method is needed. Depends on later implementation.
     */
    public static boolean isValidLink(String test) {
        try {
            URL url = new URL(test);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SolutionLink // instanceof handles nulls
                && value.equals(((SolutionLink) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
