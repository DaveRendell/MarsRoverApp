import org.junit.Before;
import org.junit.Test;
import parsers.InstructionListParser;
import parsers.PlateauSizeParser;
import parsers.RoverPositionParser;

import java.io.*;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Set of tests designed to test the full feature set of the app, passing in input and
 * checking the output matches the results we expect.
 */
public class MarsRoverAppTest {

    // Mock result stream used to check correct output is returned by app
    PrintStream resultStream;

    @Before
    public void setUp() {
        resultStream = mock(PrintStream.class);
    }

    @Test
    public void standardTestCase() {
        String input =
                "5 5" +
                "\n1 2 N" +
                "\nLMLMLMLMM" +
                "\n3 3 E" +
                "\nMMRMMRMRRM" +
                "\n\n";

        runAppWithInput(input);

        verify(resultStream).println("1 3 N");
        verify(resultStream).println("5 1 E");
        verifyNoMoreInteractions(resultStream);
    }

    @Test
    public void runningOffPlateauPrintsError() {
        String input =
                "0 0" +
                        "\n0 0 N" +
                        "\nM" +
                        "\n\n";

        runAppWithInput(input);

        verify(resultStream).println(
                "Unable to execute command M as it will take rover to position 0 1 N. " +
                "This will cause the rover to fall from the plateau with width and height 0 0");
        verifyNoMoreInteractions(resultStream);
    }

    @Test
    public void userEntersIncorrectInput_allowedToReenter() {
        String input =
                "5 5" +
                "\n1 2 M" +
                "\n1 2 N" +
                "\nLMLMLMLMM" +
                "\n3 3 E" +
                "\nMMRMMRMRRM" +
                "\n\n";

        runAppWithInput(input);

        verify(resultStream).println("1 3 N");
        verify(resultStream).println("5 1 E");
        verifyNoMoreInteractions(resultStream);
    }

    private void runAppWithInput(String input) {
        MarsRoverApp marsRoverApp = new MarsRoverApp(
                getScannerReturningInput(input),
                new PlateauSizeParser(),
                new RoverPositionParser(),
                new InstructionListParser(),
                nullOutputStream(),
                resultStream);
        marsRoverApp.run();
    }

    private Scanner getScannerReturningInput(String string) {
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        return new Scanner(inputStream);
    }

    /**
     * Generates a PrintStream that ignores all input. Used to ignore the usage instructions during testing.
     */
    private PrintStream nullOutputStream() {
        return new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        });
    }
}