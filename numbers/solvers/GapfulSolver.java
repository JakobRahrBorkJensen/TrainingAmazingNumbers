package numbers.solvers;

import static numbers.utils.NumericUtils.getFirstDigit;
import static numbers.utils.NumericUtils.getLastDigit;

/**
 * A Gapful number is a number that contains at least 3 digits and is divisible by the concatenation 
 * of its first and last digit without a remainder. 
 * 12 is not a Gapful number, as it has only two digits. 
 * 132 is a Gapful number, as 132 % 12 == 0. 
 * 7881 is another example of a Gapful number, as 7881 % 71 == 0.
 */
public class GapfulSolver {
    public static boolean isGapful(long number) {
        if (number < 100) {
            return false;
        }

        int firstDigit = getFirstDigit(number);
        int lastDigit = getLastDigit(number);
        int divisionNumber = firstDigit * 10 + lastDigit;

        return number % divisionNumber == 0;
    }
}
