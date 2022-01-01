package numbers.exceptions;

/**
 * Exception to be thrown upon wrongful request parameter, that was expected to be an integer > 0.
 */
public class NotNaturalNumberException extends RuntimeException {
    public NotNaturalNumberException(String message) {
        super(message);
    }
}
