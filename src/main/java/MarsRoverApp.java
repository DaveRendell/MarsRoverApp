import exceptions.RoverMovementException;
import exceptions.UserInputException;
import instructions.Instruction;
import models.PlateauSize;
import models.RoverInput;
import models.RoverPosition;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
    private PrintStream usageInstructionOutputStream;
    private PrintStream resultOutputStream;

    public MarsRoverApp(Scanner scanner, PlateauSizeParser plateauSizeParser, RoverPositionParser roverPositionParser, InstructionListParser instructionListParser, PrintStream usageInstructionOutputStream, PrintStream resultOutputStream) {
        this.scanner = scanner;
        this.plateauSizeParser = plateauSizeParser;
        this.roverPositionParser = roverPositionParser;
        this.instructionListParser = instructionListParser;
        this.usageInstructionOutputStream = usageInstructionOutputStream;
        this.resultOutputStream = resultOutputStream;
    }

    public void run() {
        PlateauSize plateauSize = readPlateauSize();
        List<RoverInput> roverInputs = readRoverInputs();

        RoverProcessor roverProcessor = new RoverProcessor(plateauSize);

        roverInputs.forEach(input -> {
            try {
                resultOutputStream.println(roverProcessor.process(input).toString());
            } catch (RoverMovementException e) {
                resultOutputStream.println(e.getMessage());
            }
        });
    }

    private PlateauSize readPlateauSize() {
        usageInstructionOutputStream.println(
                "Please enter coordinates of the north-east corner of the plateau " +
                        "(where the south-west coordinate is 0 0) separated by a space:" +
                        "\n\t<x> <y>");

        PlateauSize plateauSize = null;
        while (plateauSize == null) {
            String plateauLine = scanner.nextLine();
            try {
                plateauSize = plateauSizeParser.parse(plateauLine);
            } catch (UserInputException e){
                usageInstructionOutputStream.println(e.getMessage());
            }
        }

        return plateauSize;
    }

    private List<RoverInput> readRoverInputs() {
        List<RoverInput> roverInputs = new ArrayList<>();

        usageInstructionOutputStream.println(
                "Please enter initial position and cardinal direction of a rover, in format \"<x> <y> <N|E|S|W>\"");
        String roverPositionLine = scanner.nextLine();
        do {
            RoverPosition roverPosition = null;
            while (roverPosition == null) {
                try {
                    roverPosition = roverPositionParser.parse(roverPositionLine);
                } catch (UserInputException e){
                    usageInstructionOutputStream.println(e.getMessage());
                    roverPositionLine = scanner.nextLine();
                }
            }

            usageInstructionOutputStream.println(
                    "Please enter list of rover instructions, represented by one of 'L', 'R', and 'M'");

            List<Instruction> instructions = new ArrayList<>();
            while (instructions.isEmpty()) {
                String instructionLine = scanner.nextLine();
                try {
                    instructions = instructionListParser.parse(instructionLine);
                } catch (UserInputException e){
                    usageInstructionOutputStream.println(e.getMessage());
                }
            }

            roverInputs.add(new RoverInput(roverPosition, instructions));

            usageInstructionOutputStream.println(
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

        // Two separate output streams are used. In production code we want both to output to stdout, but they are
        // passed in separately to allow easily tracking the output for the results in test code.
        MarsRoverApp app = new MarsRoverApp(
                scanner, plateauSizeParser, roverPositionParser, instructionListParser, System.out, System.out);

        app.run();
    }
}
