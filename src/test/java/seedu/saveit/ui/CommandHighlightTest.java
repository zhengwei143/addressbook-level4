package seedu.saveit.ui;

import static junit.framework.TestCase.assertTrue;
import static seedu.saveit.model.util.SampleDataUtil.isPrefixParameter;

import java.util.List;

import org.junit.Test;

public class CommandHighlightTest extends CommandBoxTest {

    @Test
    public void testCorrectHighlightWithCorrectCommands() {
        String input1 = "addtag 1-5 t/python t/java";
        assertCorrectHighlightAfterInput(input1);

        String input2 = "refactortag t/python n/java";
        assertCorrectHighlightAfterInput(input2);

        String input3 = "refactortag t/java t/python n/solved n/unsolved";
        assertCorrectHighlightAfterInput(input3);

        String input4 = "add i/map function d/how to add map function t/map t/python";
        assertCorrectHighlightAfterInput(input4);

        String input5 = "select 2";
        assertCorrectHighlightAfterInput(input5);

        String input6 = "addtag 1 2 5 t/python t/java";
        assertCorrectHighlightAfterInput(input6);

    }

    @Test
    public void testCorrectHighlightIndexAppearWithoutHighlightCommand() {
        String input7 = "findtag 123";
        assertCorrectHighlightAfterInput(input7);
    }

    @Test
    public void testCorrectHighlightWithLeadingSpaceCommand() {
        String input = "   findtag test";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithIncorrectParameterCommand() {
        String input = "add g/map function a/how to add map function h/map t/python";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithEmptyCommand() {
        String input = "";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithCommandWordEnd() {
        String input = "exit";
        assertCorrectHighlightAfterInput(input);
    }

    @Test
    public void testCorrectHighlightWithKeyEnd() {
        String input = "add t/";
        assertCorrectHighlightAfterInput(input);
    }

    private void assertCorrectHighlightAfterInput(String input) {
        commandBoxHandle.enterCommand(input);
        assertCorrectHighlight();
    }


    /**
     * check if the the highlight is correct, assertTrue if yes
     */
    private void assertCorrectHighlight() {
        List<String> wordsInCommandWordStyle =
            commandBoxHandle.getWordListWithStyle(CommandHighlightManager.STYLE_COMMAND_WORD);
        assertTrue(wordsInCommandWordStyle.size() <= 1);

        List<String> wordsInIndexWordStyle = commandBoxHandle.getWordListWithStyle(CommandHighlightManager.STYLE_INDEX);
        for (String word : wordsInIndexWordStyle) {
            assertTrue(word.matches("\\d+"));
        }

        List<String> wordsInParameterWordStyle = commandBoxHandle
            .getWordListWithStyle(CommandHighlightManager.STYLE_PARAMETER_KEY);
        for (String word : wordsInParameterWordStyle) {
            assertTrue(isPrefixParameter(word));
        }
    }
}
