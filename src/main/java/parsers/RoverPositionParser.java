package parsers;

import exceptions.UserInputException;
import models.Direction;
import models.RoverPosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a line of input describing the initial position and direction of a rover.
 */
public class RoverPositionParser extends LineParser<RoverPosition> {
    @Override
    protected Pattern getExpectedPattern() {
        return Pattern.compile("^(\\d+) (\\d+) ([NESW])$");
    }

    @Override
    protected RoverPosition convertFromMatcher(Matcher matcher) {
        int x = Integer.parseInt(matcher.group(1));
        int y = Integer.parseInt(matcher.group(2));
        Direction direction = Direction.getDirectionForSymbol(matcher.group(3));
        return new RoverPosition(x, y, direction);
    }

    @Override
    protected UserInputException createUserInputException(String input) {
        return new UserInputException(
                input,
                "Enter rover position as two integers, and a single letter representing a cardinal direction:" +
                        "\n\t<width> <height> <N|E|S|W>" +
                        "\n E.g.:" +
                        "\n\t25 30 N" +
                        "\n\t0 13 E");
    }
}
