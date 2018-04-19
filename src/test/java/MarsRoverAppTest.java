import org.junit.Before;
import org.junit.Test;
import parsers.InstructionListParser;
import parsers.PlateauSizeParser;
import parsers.RoverPositionParser;

import java.io.*;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MarsRoverAppTest {

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