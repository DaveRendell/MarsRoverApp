import exceptions.RoverMovementException;
import exceptions.UserInputException;
import instructions.Instruction;
import models.PlateauSize;
import models.RoverInput;
import models.RoverPosition;
import parsers.InstructionListParser;
import parsers.PlateauSizeParser;
import parsers.RoverPositionParser;
import processors.RoverProcessor;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarsRoverApp {
    private Scanner scanner;
    private PlateauSizeParser plateauSizeParser;
    private RoverPositionParser roverPositionParser;
    private InstructionListParser instructionListParser;
    private PrintStream outputStream;

    public MarsRoverApp(Scanner scanner, PlateauSizeParser plateauSizeParser, RoverPositionParser roverPositionParser, InstructionListParser instructionListParser, PrintStream outputStream) {
        this.scanner = scanner;
        this.plateauSizeParser = plateauSizeParser;
        this.roverPositionParser = roverPositionParser;
        this.instructionListParser = instructionListParser;
        this.outputStream = outputStream;
    }

    public void run() {
        PlateauSize plateauSize = readPlateauSize();
        List<RoverInput> roverInputs = readRoverInputs();

        RoverProcessor roverProcessor = new RoverProcessor(plateauSize);

        roverInputs.forEach(input -> {
            try {
                outputStream.println(roverProcessor.process(input));
            } catch (RoverMovementException e) {
                outputStream.println(e.getMessage());
            }
        });
    }

    private PlateauSize readPlateauSize() {
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

    private List<RoverInput> readRoverInputs() {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();
        RoverPositionParser roverPositionParser = new RoverPositionParser();
        InstructionListParser instructionListParser = new InstructionListParser();

        MarsRoverApp app = new MarsRoverApp(
                scanner, plateauSizeParser, roverPositionParser, instructionListParser, System.out);

        app.run();
    }
}
