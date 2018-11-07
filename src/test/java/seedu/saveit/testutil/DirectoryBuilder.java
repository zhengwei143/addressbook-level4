package seedu.saveit.testutil;

import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;

/**
 * A utility class help with building Directories objects.
 */
public class DirectoryBuilder {
    private static final int DEFAULT_ISSUE_INDEX = 0;
    private static final int DEFAULT_SOLUTION_INDEX = 0;

    private int issueIndex;
    private int solutionIndex;

    public DirectoryBuilder() {
        this.issueIndex = DEFAULT_ISSUE_INDEX;
        this.solutionIndex = DEFAULT_SOLUTION_INDEX;
    }

    /**
     * Returns a issue level directory with the specific issueIndex.
     */
    public DirectoryBuilder withIssueIndex(Index issueIndex) {
        this.issueIndex = issueIndex.getOneBased();
        return this;
    }

    /**
     * Returns a solution level directory with the specific issueIndex and
     * solution index.
     */
    public DirectoryBuilder withSolutionIndex(Index solutionIndex) {
        this.solutionIndex = solutionIndex.getOneBased();
        return this;
    }

    public Directory build() {
        return new Directory(this.issueIndex, this.solutionIndex);
    }
}
