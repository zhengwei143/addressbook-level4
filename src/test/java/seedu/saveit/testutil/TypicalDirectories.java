package seedu.saveit.testutil;

import seedu.saveit.commons.core.directory.Directory;
import seedu.saveit.commons.core.index.Index;

public class TypicalDirectories {
    private static final int ROOT_LEVEL_INDEX = 0;
    private static final int EMPTY_SOLUTION_LIST_INDEX = 0;

    public static final Directory ROOT_LEVEL = new Directory(ROOT_LEVEL_INDEX, EMPTY_SOLUTION_LIST_INDEX);

    public static Directory getCustomizedIssueLevel(Index issueIndex) {
        Directory issueLevel = new Directory(issueIndex.getOneBased(), EMPTY_SOLUTION_LIST_INDEX);
        assert (issueLevel.isIssueLevel());
        return issueLevel;
    }

    public static Directory getCustomizedSolutionLevel(Index issueIndex, Index solutionIndex) {
        Directory solutionLevel = new Directory(issueIndex.getOneBased(), solutionIndex.getOneBased());
        assert (solutionLevel.isSolutionLevel());
        return solutionLevel;
    }
}
