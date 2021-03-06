package instructions;

import models.RoverPosition;

/**
 * Instruction for moving a rover forward one space on its current heading.
 */
public class MoveInstruction extends Instruction {
    @Override
    public void execute(RoverPosition startPosition) {
        startPosition.moveAtCurrentHeading();
    }

    @Override
    public String toString() {
        return "M";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MoveInstruction;
    }
}
