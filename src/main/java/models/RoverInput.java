package models;

import instructions.Instruction;

import java.util.List;

/**
 * Class containing user input for a single rover, containing its initial position
 * and a list of instructions for it to execute.
 */
public class RoverInput {
    private RoverPosition initialPosition;
    private List<Instruction> instructions;

    public RoverInput(RoverPosition initialPosition, List<Instruction> instructions) {
        this.initialPosition = initialPosition;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        String position = initialPosition.toString();
        StringBuilder instructionStringBuilder = new StringBuilder();
        for (Instruction instruction : instructions) {
            instructionStringBuilder.append(instruction.toString());
        }
        return String.format("%s\n%s", position, instructionStringBuilder.toString());
    }
}
