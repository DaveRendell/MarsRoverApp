package instructions;

import models.RoverPosition;

/**
 * Instruction for rotating a rover 90 degrees right while remaining stationary.
 */
public class RightInstruction extends Instruction {
    @Override
    public RoverPosition execute(RoverPosition startPosition) {
        // TODO - implement instruction
        return startPosition;
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof RightInstruction;
    }
}
