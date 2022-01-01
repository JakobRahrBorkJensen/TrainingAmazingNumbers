package numbers.exceptions;

/**
 * Exception to be thrown if the received request indicates desire to terminate execution.
 */
public class TerminationException extends RuntimeException {
    public TerminationException(){
        super("Goodbye!");
    }

}
