package numbers.exceptions;

/**
 * Exception to be thrown if empty request is received
 */
public class EmptyRequestException extends RuntimeException {
    public EmptyRequestException() {
        super();
    }
}
