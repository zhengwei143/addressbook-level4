package seedu.saveit.logic.parser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with {@code prefix} key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated
     * @param argValue Argument value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of {@code prefix}.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of {@code prefix}.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Attempts to find the {@code Prefix} used as the key with the same position as the caret
     * @param caretPosition
     * @return Prefix if found and null if not
     */
    public Prefix findPrecedingPrefixKey(int caretPosition) {
        Set<Prefix> keySet = argMultimap.keySet();
        List<Prefix> filtered = keySet.stream()
                .filter(prefix -> prefix.getPrefix() != ArgumentTokenizer.END_MARKER)
                .filter(prefix -> (prefix.getPosition() + prefix.getPrefix().length()) <= caretPosition)
                .collect(Collectors.toList());

        filtered.sort(Comparator.comparing(prefix -> prefix.getPosition()));

        if (filtered.size() == 0) {
            return null;
        }
        // Should get the last matched Prefix (as it is the closest to the caret)
        return filtered.get(filtered.size() - 1);
    }

    /**
     * Attempts to find the {@code Prefix} that is positioned after
     * @param currentPrefix
     */
    public Prefix findSucceedingPrefixKey(Prefix currentPrefix) {
        Set<Prefix> keySet = argMultimap.keySet();
        // TODO: Might need to filter by <= as the end marker might have the same position
        List<Prefix> filtered = keySet.stream()
                .filter(prefix -> currentPrefix.getPosition() <= prefix.getPosition())
                .collect(Collectors.toList());

        filtered.sort(Comparator.comparing(prefix -> prefix.getPosition()));

        if (filtered.size() <= 1) {
            return currentPrefix;
        }
        // Return the second matched Prefix (which would be the succeeding Prefix after currentPrefix)
        // The first matched should be the currentPrefix
        return filtered.get(1);
    }

    /**
     * Offsets all the {@code Prefix.positions} by {@code offset}
     */
    public void offsetPositions(int offset) {
        argMultimap.keySet().stream().forEach(prefix -> prefix.offset(offset));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }
}
