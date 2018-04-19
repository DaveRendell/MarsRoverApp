package exceptions;

import instructions.Instruction;
import models.PlateauSize;
import models.RoverPosition;

public class RoverMovementException extends Exception {
    private RoverPosition position;
    private Instruction instruction;
    private PlateauSize plateauSize;

    public RoverMovementException(RoverPosition position, Instruction instruction, PlateauSize plateauSize) {
        this.position = position;
        this.instruction = instruction;
        this.plateauSize = plateauSize;
    }

    @Override
    public String getMessage() {
        return String.format(
                "Unable to execute command %s as it will take rover to position %s. This will cause " +
                        "the rover to fall from the plateau with width and height %s",
                instruction, position, plateauSize);
    }
}
