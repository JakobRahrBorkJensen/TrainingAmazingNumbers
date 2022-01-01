package numbers.utils;

import java.util.Arrays;

/**
 * Enum containes all error messages to be used with custom exceptions
 */
public enum ErrorMessage {
    FIRST_PARAMETER("The first parameter should be a natural number or zero."),
    SECOND_PARAMETER("The second parameter should be a natural number."),
    SINGLE_WRONG_PROPERTY("The property [%s] is wrong.\n" +
            "Available properties: "+ Arrays.toString(Property.values())),
    MULTIPLE_WRONG_PROPERTIES("The properties [%s] are wrong.\n" +
            "Available properties: "+ Arrays.toString(Property.values())),
    MUTUALLY_EXCLUSIVE_PROPERTIES("The request contains mutually exclusive properties: [%s, %s]\n" +
            "There are no numbers with these properties.");

    final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
