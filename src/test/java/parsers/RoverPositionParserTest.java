package parsers;

import exceptions.UserInputException;
import models.Direction;
import models.RoverPosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.*;

public class RoverPositionParserTest {
    RoverPositionParser roverPositionParser;

    @Before
    public void setUp() {
        roverPositionParser = new RoverPositionParser();
    }

    @Test
    public void validInput_parse_returnsExpectedResult() throws Exception {
        String input = "0 0 N";

        RoverPosition result = roverPositionParser.parse(input);

        assertThat(result).isEqualTo(
                new RoverPosition(0, 0, Direction.NORTH));
    }

    @Test
    public void validInputWithMultiDigitCoordinates_parse_returnsExpectedResult() throws Exception {
        String input = "11 5 E";

        RoverPosition result = roverPositionParser.parse(input);

        assertThat(result).isEqualTo(
                new RoverPosition(11, 5, Direction.EAST));
    }

    @Test
    public void invalidDirection_parse_throwsRoverPositionParseError() {
        String input = "0 0 J";

        assertThatThrownBy(() -> roverPositionParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }

    @Test
    public void tooManyCoordinates_parse_throwsRoverPositionParseError() {
        String input = "1 2 3 E";

        assertThatThrownBy(() -> roverPositionParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }
}