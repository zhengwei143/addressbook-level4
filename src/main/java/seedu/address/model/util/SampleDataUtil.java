package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Issue;
import seedu.address.model.ReadOnlySaveIt;
import seedu.address.model.SaveIt;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.IssueStatement;
import seedu.address.model.issue.Solution;
import seedu.address.model.issue.Tag;

/**
 * Contains utility methods for populating {@code SaveIt} with sample data.
 */
public class SampleDataUtil {
    public static Issue[] getSamplePersons() {
        return new Issue[] {
            new Issue(new IssueStatement("Alex Yeoh"), new Description("87438807"), getSolutionSet("StackOverflow newSolution"), getTagSet("friends")),
            new Issue(new IssueStatement("Bernice Yu"), new Description("99272758"), getSolutionSet("IVLE newBug", "WiKiPedia remark1"), getTagSet("colleagues", "friends")),
            new Issue(new IssueStatement("Charlotte Oliveiro"), new Description("93210283"), getSolutionSet("ZhiHu newSolution"), getTagSet("neighbours")),
            new Issue(new IssueStatement("David Li"), new Description("91031282"), getSolutionSet("StackOverflow new"),
                getTagSet("family")),
            new Issue(new IssueStatement("Irfan Ibrahim"), new Description("92492021"), getSolutionSet("Forum solution"), getTagSet("classmates")),
            new Issue(new IssueStatement("Roy Balakrishnan"), new Description("92624417"), getSolutionSet("Tutorial new", "NoSolution dead"), getTagSet("colleagues"))
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
     * Returns a solution set containing the list of strings given.
     */
    public static Set<Solution> getSolutionSet(String... strings) {
//        Solution solution = new Solution(string.substring(0, string.indexOf(' ')), string.substring(string.indexOf(' ') + 1));
//        Set<Solution> set = new HashSet<>();
//        set.add(solution);
//        return set;
        Set<Solution> solutionSet = new HashSet<>();
        for (String solutions: strings){
            String link = solutions.substring(0, solutions.indexOf(' '));
            String remark = solutions.substring(solutions.indexOf(' ') + 1);
            solutionSet.add(new Solution(link,remark));
        }
        return solutionSet;
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
