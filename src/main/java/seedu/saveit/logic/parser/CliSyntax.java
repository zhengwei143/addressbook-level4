package seedu.saveit.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final String PREFIX_STATEMENT_STRING = "i/";
    public static final String PREFIX_SOLUTION_LINK_STRING = "s/";
    public static final String PREFIX_REMARK_STRING = "r/";
    public static final String PREFIX_DESCRIPTION_STRING = "d/";
    public static final String PREFIX_TAG_STRING = "t/";
    public static final String PREFIX_NEW_TAG_STRING = "n/";

    /* Prefix definitions */
    public static final Prefix PREFIX_STATEMENT = new Prefix(PREFIX_STATEMENT_STRING);
    public static final Prefix PREFIX_SOLUTION_LINK = new Prefix(PREFIX_SOLUTION_LINK_STRING);
    public static final Prefix PREFIX_REMARK = new Prefix(PREFIX_REMARK_STRING);
    public static final Prefix PREFIX_DESCRIPTION = new Prefix(PREFIX_DESCRIPTION_STRING);
    public static final Prefix PREFIX_TAG = new Prefix(PREFIX_TAG_STRING);
    public static final Prefix PREFIX_NEW_TAG = new Prefix(PREFIX_NEW_TAG_STRING);
}
