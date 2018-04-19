package instructions;

import models.RoverPosition;

/**
 * Abstract class describing an instruction that can be sent to a rover
 */
public abstract class Instruction {
    public abstract RoverPosition execute(RoverPosition startPosition);
}
