package numbers.solvers;

import static numbers.utils.NumericUtils.getDigits;

/**
 * A number is a Jumping number if the adjacent digits inside the number differ by 1.
 * The difference between 9 and 0 is not considered as 1.
 * Single-digit numbers are considered Jumping numbers.
 * For example, 78987, and 4343456 are Jumping numbers, but 796 and 89098 are not.
 */
public class JumpingSolver {
    public static boolean isJumping(long number) {
        var digits = getDigits(number);

        // Single digit numbers is Jumping numbers
        if (digits.size() == 1) {
            return true;
        }

        int priorDigit = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            if (digits.get(i) != priorDigit - 1 && digits.get(i) != priorDigit + 1) {
                return false;
            }
            priorDigit = digits.get(i);
        }
        return true;
    }
}
