package seedu.address.model.person;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;
import seedu.saveit.model.issue.IssueStatement;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new IssueStatement(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new IssueStatement(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> IssueStatement.isValidName(null));

        // invalid name
        assertFalse(IssueStatement.isValidName("")); // empty string
        assertFalse(IssueStatement.isValidName(" ")); // spaces only
        assertFalse(IssueStatement.isValidName("^")); // only non-alphanumeric characters
        assertFalse(IssueStatement.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(IssueStatement.isValidName("peter jack")); // alphabets only
        assertTrue(IssueStatement.isValidName("12345")); // numbers only
        assertTrue(IssueStatement.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(IssueStatement.isValidName("Capital Tan")); // with capital letters
        assertTrue(IssueStatement.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
