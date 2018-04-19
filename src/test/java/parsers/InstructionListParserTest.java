package parsers;

import exceptions.UserInputException;
import instructions.Instruction;
import instructions.LeftInstruction;
import instructions.MoveInstruction;
import instructions.RightInstruction;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InstructionListParserTest {
    private InstructionListParser instructionListParser;

    @Before
    public void setUp() {
        instructionListParser = new InstructionListParser();
    }

    @Test
    public void validInput_parse_returnsMatchingResultList() throws UserInputException {
        String input = "LMRLMR";

        List<Instruction> output = instructionListParser.parse(input);

        assertThat(output).containsExactly(
                new LeftInstruction(),
                new MoveInstruction(),
                new RightInstruction(),
                new LeftInstruction(),
                new MoveInstruction(),
                new RightInstruction());
    }

    @Test
    public void unknownInstructions_parse_throwsUserInputException() {
        String input = "LMRG";

        assertThatThrownBy(() -> instructionListParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }

    @Test
    public void incorrectInputType_parse_throwsUserInputException() {
        String input = "5 11 E";

        assertThatThrownBy(() -> instructionListParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }
}