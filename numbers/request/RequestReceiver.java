package numbers.request;

import numbers.exceptions.*;
import numbers.utils.ErrorMessage;
import numbers.utils.Property;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static numbers.utils.NumericUtils.convertStringToInt;
import static numbers.utils.NumericUtils.convertStringToLong;

/**
 * This class handles the receival and validation of a user request
 */
public class RequestReceiver {

    /**
     * Receives and validates user request
     */
    public static Request receiveRequest() {
        Scanner scanner = new Scanner(System.in);
        var request = new Request();

        String rawInput;
        rawInput = scanner.nextLine();

        // Check if request is empty
        if ("".equals(rawInput)) throw new EmptyRequestException();

        // Check number of inputs (separated by " ")
        String[] rawInputs = rawInput.split(" ");

        // Handle first input - number
        request.setNumber(handleNumberInput(rawInputs[0]));

        // Handle second input - count
        if (rawInputs.length > 1) {
            request.setCount(handleCountInput(rawInputs[1]));
        }

        // Handle properties (input 3 and 4)
        List<String> propertyInputs = new ArrayList<>();
        if (rawInputs.length > 2) {
            for (int i = 2; i < rawInputs.length; i++) {
                propertyInputs.add(rawInputs[i].toUpperCase());
            }
        }

        if (propertyInputs.size() > 0) {
            // Validate that all provided properties are valid
            validatePropertyInputs(propertyInputs);

            // Split into desired and unwanted properties
            var desiredProperties = separateProperties(propertyInputs);
            request.setDesiredProperties(convertPropertyInputs(desiredProperties.get(true)));
            request.setUnwantedProperties(convertPropertyInputs(desiredProperties.get(false)));

            // Check for mutual exclusiveness in each property set
            checkForMutualExclusiveness(request.getDesiredProperties(), true);
            checkForMutualExclusiveness(request.getUnwantedProperties(), false);

            // Check for mutual exclusiveness across property sets (same property in both)
            checkForMutualExclusiveness(request.getDesiredProperties(), request.getUnwantedProperties());
        }

        return request;
    }

    /**
     * Separates list of property inputs into separate lists for desired and unwanted properties
     */
    private static Map<Boolean, List<String>> separateProperties(List<String> propertyInputs) {
        return propertyInputs.stream().collect(Collectors.partitioningBy(RequestReceiver::isDesiredProperty));
    }

    /**
     * Validates that provided properties are valid options
     */
    private static void validatePropertyInputs(List<String> propertyInputs) {
        Map<Boolean, List<String>> validProperties =
                propertyInputs.stream().collect(Collectors.partitioningBy(RequestReceiver::isValidProperty));

        // Throw exception if any input property was not valid
        throwErrorIfAnyIncorrectPropertiesProvided(validProperties.get(false));
    }

    /**
     * The number should be the first parameter. Number indicates the starting number to be investigated
     */
    private static long handleNumberInput(String rawInput) {
        String errorMessage = ErrorMessage.FIRST_PARAMETER.getMessage();

        // Convert string input to integer
        long number = convertStringToLong(rawInput, errorMessage);

        // Throw Termination exception if 0 is provided as first parameter
        checkForTerminationStatement(number);

        // Throw exception if number is below 1 (not natural)
        checkValidNaturalNumber(number, errorMessage);

        return number;
    }

    /**
     * The count should be the second parameter. Count specifies the number of results desired
     */
    private static int handleCountInput(String rawInput) {
        String errorMessage = ErrorMessage.SECOND_PARAMETER.getMessage();

        // Convert string input to integer
        int count = convertStringToInt(rawInput, errorMessage);

        // Throw exception if number is below 1 (not natural)
        checkValidNaturalNumber(count, errorMessage);

        return count;
    }

    private static List<Property> convertPropertyInputs(List<String> propertyInputs) {
        // Map all to properties
        return propertyInputs.stream()
                .map(RequestReceiver::convertToProperty)
                .collect(Collectors.toList());
    }

    private static boolean isValidProperty(String input) {
        String sanitisedPropertyInput = getSanitisedPropertyInput(input);
        return Arrays.stream(Property.values())
                .anyMatch(property -> property.name().equals(sanitisedPropertyInput));
    }

    /**
     * Sanitises property input by removing characters used to identify property as unwanted.
     */
    private static String getSanitisedPropertyInput(String input) {
        return input.replace("-", "");
    }

    /**
     * Unwanted properties is specified with a "-" as the first character. Otherwise it is desired
     */
    private static boolean isDesiredProperty(String input) {
        return input.charAt(0) != '-';
    }

    /**
     * Throws error upon incorrect properties provided with specific error message depending on
     * number of incorrect inputs
     */
    private static void throwErrorIfAnyIncorrectPropertiesProvided(List<String> incorrectProperties) {
        if (incorrectProperties.size() > 1) {
            throw new IncorrectPropertyException(format(ErrorMessage.MULTIPLE_WRONG_PROPERTIES.getMessage(), String.join(", ", incorrectProperties)));
        }
        if (incorrectProperties.size() == 1) {
            throw new IncorrectPropertyException(format(ErrorMessage.SINGLE_WRONG_PROPERTY.getMessage(), incorrectProperties.get(0)));
        }
    }

    /**
     * If first parameter is 0, the flow will be terminated
     */
    private static void checkForTerminationStatement(long number) {
        if (number == 0) throw new TerminationException();
    }

    /**
     * Only integer numbers greater than 0 is allowed as natural numbers
     */
    private static void checkValidNaturalNumber(long number, String message) {
        if (number < 1) throw new NotNaturalNumberException(message);
    }

    private static Property convertToProperty(String providedProperty) {
        try {
            return Property.valueOf(getSanitisedPropertyInput(providedProperty));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Mutual exclusiveness pairs include even/odd, duck/spy, sunny/square and sad/happy
     */
    private static void checkForMutualExclusiveness(List<Property> properties, boolean areDesiredProperties) {
        if (properties.contains(Property.EVEN) && properties.contains(Property.ODD)) {
            throwMutualExclusivenessException(Property.EVEN.name(), Property.ODD.name(), areDesiredProperties);
        }
        if (properties.contains(Property.DUCK) && properties.contains(Property.SPY)) {
            throwMutualExclusivenessException(Property.DUCK.name(), Property.SPY.name(), areDesiredProperties);
        }
        if (properties.contains(Property.SUNNY) && properties.contains(Property.SQUARE)) {
            throwMutualExclusivenessException(Property.SUNNY.name(), Property.SQUARE.name(), areDesiredProperties);
        }
        if (properties.contains(Property.SAD) && properties.contains(Property.HAPPY)) {
            throwMutualExclusivenessException(Property.SAD.name(), Property.HAPPY.name(), areDesiredProperties);
        }
    }

    private static void throwMutualExclusivenessException(String first, String second, boolean areDesiredProperties) {
        if (!areDesiredProperties) {
            first = "-" + first;
            second = "-" + second;
        }
        throw new MutuallyExclusivePropertiesException(
                format(ErrorMessage.MUTUALLY_EXCLUSIVE_PROPERTIES.getMessage(), first, second));
    }

    /**
     * A provided property cannot both be desired and unwanted. If such a scenario is provided, an exception is thrown
     */
    private static void checkForMutualExclusiveness(List<Property> desiredProperties, List<Property> unwantedProperties) {
        desiredProperties.forEach(property -> {
            if (unwantedProperties.contains(property)) {
                throw new MutuallyExclusivePropertiesException(
                        format(ErrorMessage.MUTUALLY_EXCLUSIVE_PROPERTIES.getMessage(), property, "-"+property));
            }
        });
    }
}
