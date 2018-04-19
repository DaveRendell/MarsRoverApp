package exceptions;

/**
 * An exception thrown when a user inputs a line that cannot be converted into the required data.
 */
public class UserInputException extends Exception {
    private String invalidInput;
    private String correctUsage;

    public UserInputException(String invalidInput, String correctUsage) {
        this.invalidInput = invalidInput;
        this.correctUsage = correctUsage;
    }

    @Override
    public String getMessage() {
        return String.format("Unable to parse input %s.\n%s", invalidInput, correctUsage);
    }
}
