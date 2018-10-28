package seedu.saveit.commons.core.directory;

/**
 * Represent the directory of current edition by user. It can be passed as parameters to communicate with other
 * components as the directory design is rather complicated. There are three levels: root/issue/solution.
 */
public class Directory {
    private int issue;
    private int solution;

    /**
     * Directory can only be created by calling {@link Directory#formDirectory(int,int)}.
     */
    private Directory(int newIssue, int newSolution) {
        issue = newIssue;
        solution = newSolution;
    }

    public static Directory formDirectory(int newIssue, int newSolution) {
        return new Directory(newIssue, newSolution);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SaveIt/");
        if (issue != 0) {
            result.append("Issue ");
            result.append(issue);
            if (solution != 0) {
                result.append("Solution ");
                result.append(solution);
            }
        }
        return result.toString();
    }
}
