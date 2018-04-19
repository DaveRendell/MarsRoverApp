import exceptions.UserInputException;
import instructions.Instruction;
import models.PlateauSize;
import models.RoverPosition;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import parsers.InstructionListParser;
import parsers.PlateauSizeParser;
import parsers.RoverPositionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarsRoverApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        RoverPositionParser roverPositionParser = new RoverPositionParser();
        InstructionListParser instructionListParser = new InstructionListParser();

        System.out.println(
                "Please enter dimensions of the plateau, in format \"<width> <height>\", where width " +
                "and height are positive integers");

        PlateauSize plateauSize = null;
        while (plateauSize == null) {
            String plateauLine = scanner.nextLine();
            try {
                plateauSize = plateauSizeParser.parse(plateauLine);
            } catch (UserInputException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(
                "Please enter initial position and cardinal direction of a rover, in format \"<x> <y> <N|E|S|W>\"");

        RoverPosition roverPosition = null;
        while (roverPosition == null) {
            String roverPositionLine = scanner.nextLine();
            try {
                roverPosition = roverPositionParser.parse(roverPositionLine);
            } catch (UserInputException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(
                "Please enter list of rover instructions, represented by one of 'L', 'R', and 'M'");

        List<Instruction> instructions = new ArrayList<>();
        while (instructions.isEmpty()) {
            String instructionLine = scanner.nextLine();
            try {
                instructions = instructionListParser.parse(instructionLine);
            } catch (UserInputException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(plateauSize);
        System.out.println(roverPosition);
        for (Instruction instruction : instructions) {
            System.out.print(instruction);
        }
        System.out.print('\n');
    }
}
