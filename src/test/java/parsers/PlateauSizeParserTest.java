package parsers;

import exceptions.UserInputException;
import models.PlateauSize;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PlateauSizeParserTest {
    private PlateauSizeParser plateauSizeParser;

    @Before
    public void setUp() {
        plateauSizeParser = new PlateauSizeParser();
    }

    @Test
    public void validInput_parse_returnsOutputWithSameSize() throws Exception {
        String input = "5 6";

        PlateauSize output = plateauSizeParser.parse(input);

        assertThat(output).isEqualTo(new PlateauSize(5, 6));
    }

    @Test
    public void validInputWIthMultipleDigits_parse_returnsOutputWithSameSize() throws Exception {
        String input = "25 103";

        PlateauSize output = plateauSizeParser.parse(input);

        assertThat(output).isEqualTo(new PlateauSize(25, 103));
    }

    @Test
    public void additionalInformation_parse_throwsPlateauSizeParseError() throws Exception {
        final String input = "25 103 N";

        assertThatThrownBy(() -> plateauSizeParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }

    @Test
    public void negativeSize_parse_throwsPlateauSizeParseError() throws Exception {
        final String input = "25 -103";

        assertThatThrownBy(() -> plateauSizeParser.parse(input))
                .isInstanceOf(UserInputException.class)
                .hasMessageContaining(input);
    }
}