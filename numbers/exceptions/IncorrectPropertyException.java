package numbers.exceptions;

/**
 * Exception to be thrown if invalid property is provided
 */
public class IncorrectPropertyException extends RuntimeException {
    public IncorrectPropertyException(String message) {
        super(message);
    }
}
