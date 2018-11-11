package seedu.saveit.testutil;

import static seedu.saveit.testutil.TypicalSolutions.SOLUTION_REPO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import seedu.saveit.commons.core.index.Index;
import seedu.saveit.model.Issue;
import seedu.saveit.model.issue.Description;
import seedu.saveit.model.issue.IssueSearchFrequency;
import seedu.saveit.model.issue.IssueStatement;
import seedu.saveit.model.issue.Solution;
import seedu.saveit.model.issue.Tag;
import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;
import seedu.saveit.model.util.SampleDataUtil;

/**
 * A utility class to help with building Issue objects.
 */
public class IssueBuilder {

    private static final String DEFAULT_STATEMENT = "Java Problem";
    private static final String DEFAULT_DESCRIPTION = "new bug";
    private static final String DUMMY_STATEMENT = "dummyStatement";
    private static final String DUMMY_DESCRIPTION = "dummyDescription";
    private static final String DUMMY_SOLUTION_REMARK = "dummySolutionRemark";
    private static final String DUMMY_SOLUTION_LINK = "https://www.dummySolutionLink.com";

    private IssueStatement statement;
    private Description description;
    private List<Solution> solutions = new ArrayList<>();
    private Set<Tag> tags;
    private IssueSearchFrequency frequency;
    private Timestamp createdTime;
    private Timestamp lastModifiedTime;

    public IssueBuilder() {
        statement = new IssueStatement(DEFAULT_STATEMENT);
        description = new Description(DEFAULT_DESCRIPTION);
        solutions = new ArrayList<>();
//        solutions.add(SOLUTION_REPO);
        tags = new LinkedHashSet<>();
        frequency = new IssueSearchFrequency(TypicalIssues.COMMON_ISSUE_FREQUENCY);
        createdTime = new Timestamp(new Date().getTime());
        lastModifiedTime = createdTime;
    }

    /**
     * Initializes the IssueBuilder with the data of {@code issueToCopy}.
     */
    public IssueBuilder(Issue issueToCopy) {
        statement = issueToCopy.getStatement();
        description = issueToCopy.getDescription();
        solutions = new ArrayList<>(issueToCopy.getSolutions());
        tags = new LinkedHashSet<>(issueToCopy.getTags());
        frequency = issueToCopy.getFrequency();
        createdTime = issueToCopy.getCreatedTime();
        lastModifiedTime = issueToCopy.getLastModifiedTime();
    }

    /**
     * Sets the {@code IssueStatement} of the {@code Issue} that we are building.
     */
    public IssueBuilder withStatement(String statement) {
        this.statement = new IssueStatement(statement);
        return this;
    }

    /**
     * Sets the {@code IssueStatement} of the {@code Issue} that we are building
     * to dummy statement.
     */
    public IssueBuilder withDummyStatement() {
        this.statement = new IssueStatement(DUMMY_STATEMENT);
        return this;
    }

    /**
     * Parses the {@code solutions} into a {@code Set<Solution>} and set it to the {@code Issue} that we are building.
     */
    public IssueBuilder withSolutions (Solution... solutions) {
        this.solutions = SampleDataUtil.getSolutionList(solutions);
        return this;
    }

    /**
     * Parses the {@code solution} into a {@code Set<Solution>} and set it to the {@code Issue} that we are building.
     */
    public IssueBuilder withSolutionLink (Index index, String solutionLink) {
        Solution solution = processSolutionWithSolutionLink(index, solutionLink);
        solutions.set(index.getZeroBased(), solution);
        return this;
    }

    /**
     * Parses the {@code solution} into a {@code Set<Solution>} and set it to the {@code Issue} that we are building.
     */
    public IssueBuilder withRemark (Index index, String solutionRemark) {
        Solution solution = processSolutionWithSolutionRemark(index, solutionRemark);
        solutions.set(index.getZeroBased(),solution);
        return this;
    }

    /**
     * Parses the {@code solution} into a {@code Set<Solution>} and set it to the {@code Issue} that we are building.
     */


    public Solution processSolutionWithSolutionLink (Index index, String solutionLink){
        SolutionLink link = new SolutionLink(solutionLink);
        Remark remark = this.solutions.get(index.getZeroBased()).getRemark();
        return new Solution(link, remark);
    }

    public Solution processSolutionWithSolutionRemark (Index index, String solutionRemark){
        SolutionLink link = this.solutions.get(index.getZeroBased()).getLink();
        Remark remark = new Remark(solutionRemark);
        return new Solution(link, remark);
    }

    public IssueBuilder withSolution(Index index, Solution solution) {
        solutions.set(index.getZeroBased(), solution);
        return this;
    }

    /**
     * Parses the {@code solution} into a {@code Set<Solution>} and set it to the {@code Issue} that we are building.
     */
    public IssueBuilder withSolutionRemark (Index index, String solutionRemark) {
        Solution solution = processSolutionWithSolutionRemark(index, solutionRemark);
        solutions.set(index.getZeroBased(), solution);
        return this;
    }



    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Issue} that we are building.
     */
    public IssueBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Issue} that we are building.
     */
    public IssueBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Issue} that we are
     * building to dummy description.
     */
    public IssueBuilder withDummyDescription() {
        this.description = new Description(DUMMY_DESCRIPTION);
        return this;
    }

    /**
     * Sets the {@code IssueSearchFrequency} of the {@code Issue} that we are building.
     */
    public IssueBuilder withFrequency(Integer frequency) {
        this.frequency = new IssueSearchFrequency(frequency);
        return this;
    }

    /**
     * Sets the {@code Timestamp} of the {@code Issue} that we are building.
     */
    public IssueBuilder withLastModifiedTime(Long time) {
        this.lastModifiedTime = new Timestamp(time);
        return this;
    }

    /**
     * Create new Issue.
     */
    public Issue build() {
        return new Issue(statement, description, solutions, tags, frequency,
                new Timestamp(new Date().getTime()), lastModifiedTime);
    }

}
