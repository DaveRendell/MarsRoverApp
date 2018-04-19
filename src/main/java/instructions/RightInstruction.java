package instructions;

import models.RoverPosition;

/**
 * Instruction for rotating a rover 90 degrees right while remaining stationary.
 */
public class RightInstruction extends Instruction {
    @Override
    public void execute(RoverPosition startPosition) {
        startPosition.rotateRight();
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
