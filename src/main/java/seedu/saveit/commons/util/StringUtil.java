package seedu.saveit.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.saveit.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.stream.Stream;

import seedu.saveit.logic.parser.ArgumentMultimap;
import seedu.saveit.logic.parser.Prefix;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    private static final String PARTIAL_MATCH_REGEX = "(.*)";

    /**
     * Returns true if the {@code sentence} is partially matched with the {@code word}
     *   Ignores cases and full word is not required.
     *   Examples:
     *      partialMatch("hello there", "ello") == true
     *      partialMatch("hello there", "heLLO") == true
     * @param sentence cannot be null
     * @param word cannot be null or empty, must be a single word
     * @return
     */
    public static boolean partialMatch(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        return sentence.toLowerCase().matches(PARTIAL_MATCH_REGEX + preppedWord.toLowerCase() + PARTIAL_MATCH_REGEX);
    }

    /**
     * Returns true if the {@code word} matches the start of the {@code sentence} (case-insensitive)
     */
    public static boolean partialMatchFromStart(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");

        return sentence.toLowerCase().matches(preppedWord.toLowerCase() + PARTIAL_MATCH_REGEX);
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if all the CliSyntax prefix contains in the given {@code
     * ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(String args, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> args.contains(prefix.getPrefix()));
    }

    /**
     * Returns true if there is at least one of the CliSyntax prefix not contain in the  given {@code
     * ArgumentMultimap}.
     */
    public static boolean arePrefixesNotPresent(String args, Prefix... prefixes) {
        return Stream.of(prefixes).noneMatch(prefix -> args.contains(prefix.getPrefix()));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
     * ArgumentMultimap}.
     */
    public static boolean arePrefixesValuePresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Replaces the substring of {@code stringToModify} from {@code startIndex} to {@code endIndex}
     * with the {@code stringToInsert}
     */
    public static String replaceAt(String stringToModify, String stringToInsert, int startIndex, int endIndex) {
        return stringToModify.substring(0, startIndex) + stringToInsert + stringToModify.substring(endIndex);
    }
}
