package instructions;

import models.RoverPosition;

/**
 * Instruction for rotating a rover 90 degrees left while remaining stationary.
 */
public class LeftInstruction extends Instruction {
    @Override
    public RoverPosition execute(RoverPosition startPosition) {
        // TODO - implement instruction
        return startPosition;
    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof LeftInstruction;
    }
}
