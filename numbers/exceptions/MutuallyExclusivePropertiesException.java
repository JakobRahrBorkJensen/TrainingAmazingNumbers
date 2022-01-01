package numbers.exceptions;

/**
 * Exception to be thrown if combination of provided properties is not valid
 */
public class MutuallyExclusivePropertiesException extends RuntimeException {
    public MutuallyExclusivePropertiesException(String message) {
        super(message);
    }
}
