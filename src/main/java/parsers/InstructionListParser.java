package parsers;

import exceptions.UserInputException;
import instructions.Instruction;
import instructions.LeftInstruction;
import instructions.MoveInstruction;
import instructions.RightInstruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Parses a line of input representing a list of instructions to be sent to a rover.
 */
public class InstructionListParser extends LineParser<List<Instruction>> {
    @Override
    protected Pattern getExpectedPattern() {
        return Pattern.compile("^[LRM]+$");
    }

    @Override
    protected List<Instruction> convertFromMatcher(Matcher matcher) {
        List<String> symbols = new ArrayList<>(Arrays.asList(matcher.group(0).split("")));
        return symbols.stream().map(symbol -> {
            switch (symbol) {
                case "M":
                    return new MoveInstruction();
                case "L":
                    return new LeftInstruction();
                case "R":
                    return new RightInstruction();
                    default:
                        throw new IllegalArgumentException(
                                "Only commands with symbols 'L', 'R', and 'M' can be processed");
            }
        }).collect(Collectors.toList());
    }

    @Override
    protected UserInputException createUserInputException(String input) {
        return new UserInputException(
                input,
                "Enter list of instructions represented as either 'M', 'L' or 'R' (in capitals)");
    }
}
