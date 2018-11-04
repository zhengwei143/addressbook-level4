package seedu.saveit.testutil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.saveit.logic.commands.EditCommand;
import seedu.saveit.logic.commands.EditCommand.EditIssueDescriptor;
import seedu.saveit.model.Issue;
import seedu.saveit.model.issue.Description;
import seedu.saveit.model.issue.IssueStatement;
import seedu.saveit.model.issue.Solution;
import seedu.saveit.model.issue.Tag;
import seedu.saveit.model.issue.solution.Remark;
import seedu.saveit.model.issue.solution.SolutionLink;

/**
 * A utility class to help with building EditIssueDescriptor objects.
 */
public class EditIssueDescriptorBuilder {

    private EditCommand.EditIssueDescriptor descriptor;

    public EditIssueDescriptorBuilder() {
        descriptor = new EditIssueDescriptor();
    }

    public EditIssueDescriptorBuilder(EditIssueDescriptor descriptor) {
        this.descriptor = new EditIssueDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditIssueDescriptor} with fields containing {@code issue}'s details
     */
    public EditIssueDescriptorBuilder(Issue issue) {
        descriptor = new EditIssueDescriptor();
        descriptor.setStatement(issue.getStatement());
        descriptor.setDescription(issue.getDescription());
        descriptor.setSolutions(issue.getSolutions());
        descriptor.setTags(issue.getTags());
    }


    /**
     * Sets the {@code IssueStatement} of the {@code EditIssueDescriptor} that we are building.
     */
    public EditIssueDescriptorBuilder withStatement(String statement) {
        descriptor.setStatement(new IssueStatement(statement));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditIssueDescriptor} that we are building.
     */
    public EditIssueDescriptorBuilder withDescription(String descriptions) {
        descriptor.setDescription(new Description(descriptions));
        return this;
    }

    /**
     * Parses the {@code solutions} into a {@code Set<Solution>} and set it to the {@code EditIssueDescriptor} that we
     * are building.
     */
    public EditIssueDescriptorBuilder withSolutions(String[]... solutions) {
        List<Solution> solutionSet = Stream.of(solutions)
                .map(s -> new Solution(new SolutionLink(s[0]), new Remark(s[1]))).collect(Collectors.toList());
        descriptor.setSolutions(solutionSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditIssueDescriptor} that we are
     * building.
     */
    public EditIssueDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditIssueDescriptor build() {
        return descriptor;
    }
}
