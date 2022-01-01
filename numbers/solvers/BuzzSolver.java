package numbers.solvers;

import static numbers.utils.NumericUtils.getLastDigit;
import static numbers.utils.NumericUtils.getRemainder;

/**
 * Buzz numbers are numbers that are either divisible by 7 or end with 7. 
 * For example, the number 14 is a buzz number, since it is divisible by 7 without a remainder; 
 * the number 17 ends with 7, so it is also a buzz number. 
 * However, the number 75 is not a Buzz number, since it is neither divisible by 7 nor does it end with 7. 
 * The number 7 is a Buzz number too.
 */
public class BuzzSolver {

    public static boolean isBuzz(long number){
        int lastDigit;
        long remainder = 0; // Number without the last digit

        // Initialize
        if (number > 9) {
            lastDigit = getLastDigit(number);
            remainder = getRemainder(number);
        } else {
            lastDigit = (int) number;
        }

        // Check if number ends with 7
        if (isEqualTo7(lastDigit)) {
            return true;
        }

        while (number > 9) {
            number = Math.abs(remainder - lastDigit * 2L);
            lastDigit = getLastDigit(number);
            remainder = getRemainder(number);
        }
        return isDivisibleBy7(number);
    }

    private static boolean isDivisibleBy7(long number) {
        return number % 7 == 0;
    }

    private static boolean isEqualTo7(int lastDigit) {
        return lastDigit == 7;
    }
}
