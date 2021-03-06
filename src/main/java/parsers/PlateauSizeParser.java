package parsers;

import exceptions.UserInputException;
import models.PlateauSize;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a line of user input containing the dimensions of the plateau.
 */
public class PlateauSizeParser extends LineParser<PlateauSize> {
    protected Pattern getExpectedPattern() {
        return Pattern.compile("^(\\d+) (\\d+)$");
    }

    protected PlateauSize convertFromMatcher(Matcher matcher) {
        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));
        return new PlateauSize(width, height);
    }

    protected UserInputException createUserInputException(String input) {
        return new UserInputException(
                input,
                "Enter coordinates of the north-east corner of the plateau (where the south-west coordinate is 0 0) separated by a space:" +
                        "\n\t<x> <y>" +
                        "\n E.g.:" +
                        "\n\t25 30");
    }
}
