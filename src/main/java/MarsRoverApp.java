import exceptions.UserInputException;
import instructions.Instruction;
import models.PlateauSize;
import models.RoverInput;
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

        PlateauSize plateauSize = readPlateauSize(scanner, plateauSizeParser);

        List<RoverInput> roverInputs = readRoverInputs(scanner, roverPositionParser, instructionListParser);

        System.out.println(plateauSize);
        for (RoverInput roverInput : roverInputs) {
            System.out.println(roverInput);
        }
    }

    private static PlateauSize readPlateauSize(Scanner scanner, PlateauSizeParser plateauSizeParser) {
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

        return plateauSize;
    }

    private static List<RoverInput> readRoverInputs(
            Scanner scanner,
            RoverPositionParser roverPositionParser,
            InstructionListParser instructionListParser) {
        List<RoverInput> roverInputs = new ArrayList<>();

        System.out.println(
                "Please enter initial position and cardinal direction of a rover, in format \"<x> <y> <N|E|S|W>\"");
        String roverPositionLine = scanner.nextLine();
        do {
            RoverPosition roverPosition = null;
            while (roverPosition == null) {
                try {
                    roverPosition = roverPositionParser.parse(roverPositionLine);
                } catch (UserInputException e){
                    System.out.println(e.getMessage());
                    roverPositionLine = scanner.nextLine();
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

            roverInputs.add(new RoverInput(roverPosition, instructions));

            System.out.println(
                    "Please enter initial position and cardinal direction of a rover, in format \"<x> <y> <N|E|S|W>\"" +
                            "\n (or leave empty to finish)");
            roverPositionLine = scanner.nextLine();
        } while (!roverPositionLine.isEmpty());

        return roverInputs;
    }
}
