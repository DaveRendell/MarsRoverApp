import exceptions.UserInputException;
import models.PlateauSize;
import parsers.PlateauSizeParser;

import java.util.Scanner;

public class MarsRoverApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlateauSizeParser plateauSizeParser = new PlateauSizeParser();

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

        System.out.println(plateauSize);
    }
}
