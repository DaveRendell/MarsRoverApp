package processors;

import exceptions.RoverMovementException;
import instructions.Instruction;
import models.PlateauSize;
import models.RoverInput;
import models.RoverPosition;

import java.util.List;

/**
 * Class responsible for taking an initial rover position and a list of instructions
 * and returning a final position.
 */
public class RoverProcessor {
    private PlateauSize plateauSize;

    public RoverProcessor(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    /**
     * If the initial position and instruction set are valid, returns the final position of the rover.
     * If the instructions lead the rover off the plateau, throws RoverMovementException
     */
    public RoverPosition process(RoverInput roverInput) throws RoverMovementException {
        RoverPosition position = roverInput.getInitialPosition();
        List<Instruction> instructions = roverInput.getInstructions();

        for (Instruction instruction : instructions) {
            instruction.execute(position);
            if (!plateauSize.contains(position)) {
                throw new RoverMovementException(position, instruction, plateauSize);
            }
        }

        return position;
    }
}
