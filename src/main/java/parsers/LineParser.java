package parsers;

import exceptions.UserInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Inheriting classes should be able to parse a line of user input and either return the relevant
 * data, or throw an exception with usage instructions.
 */
public abstract class LineParser<T> {
    public T parse(String input) throws UserInputException {
        Matcher matcher = getExpectedPattern().matcher(input);
        if (matcher.find()) {
            return convertFromMatcher(matcher);
        } else {
            throw createUserInputException(input);
        }
    }

    protected abstract Pattern getExpectedPattern();
    protected abstract T convertFromMatcher(Matcher matcher);
    protected abstract UserInputException createUserInputException(String input);
}
