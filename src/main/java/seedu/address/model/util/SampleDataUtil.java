package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Issue;
import seedu.address.model.ReadOnlySaveIt;
import seedu.address.model.SaveIt;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.IssueStatement;
import seedu.address.model.issue.Tag;

/**
 * Contains utility methods for populating {@code SaveIt} with sample data.
 */
public class SampleDataUtil {
    public static Issue[] getSamplePersons() {
        return new Issue[] {
            new Issue(new IssueStatement("Alex Yeoh"), new Description("87438807"), getTagSet("friends")),
            new Issue(new IssueStatement("Bernice Yu"), new Description("99272758"), getTagSet("colleagues", "friends")),
            new Issue(new IssueStatement("Charlotte Oliveiro"), new Description("93210283"), getTagSet("neighbours")),
            new Issue(new IssueStatement("David Li"), new Description("91031282"), getTagSet("family")),
            new Issue(new IssueStatement("Irfan Ibrahim"), new Description("92492021"), getTagSet("classmates")),
            new Issue(new IssueStatement("Roy Balakrishnan"), new Description("92624417"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlySaveIt getSampleSaveIt() {
        SaveIt sampleAb = new SaveIt();
        for (Issue sampleIssue : getSamplePersons()) {
            sampleAb.addPerson(sampleIssue);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
