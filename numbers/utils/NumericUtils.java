package numbers.utils;

import numbers.exceptions.NotNaturalNumberException;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with methods related to numeric convertions and manipulations
 */
public class NumericUtils {
    public static long convertStringToLong(String input, String message) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new NotNaturalNumberException(message);
        }
    }

    public static int convertStringToInt(String input, String message) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNaturalNumberException(message);
        }
    }

    public static int getLastDigit(long number) {
        long digit = number % 10;
        return (int) digit;
    }

    public static List<Integer> getDigits(long number) {
        var digits = new ArrayList<Integer>();
        while (number > 9) {
            digits.add(getLastDigit(number));
            number = getRemainder(number);
        }
        digits.add((int) number);
        return digits;
    }

    public static int getFirstDigit(long number) {
        return Integer.parseInt(Long.toString(number).substring(0, 1));
    }

    public static long getRemainder(long number) {
        return number / 10;
    }
}
