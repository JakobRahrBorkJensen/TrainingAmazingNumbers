package numbers.solvers;

import static numbers.utils.NumericUtils.getLastDigit;

/**
 * A Palindromic number is symmetrical; 
 * in other words, it stays the same regardless of whether we read it from left or right. 
 * For example, 17371 is a palindromic number. 
 * 5 is also a palindromic number. 
 * 1234 is not. If read it from right, it becomes 4321.
 */
public class PalindromicSolver {
    /**
     * Last digit of provided number is separated and added to a new variable one by one.
     * In the end, the number and its original value is compared. If identical, the number is Palindromic
     */
    public static boolean isPalindromic(long number) {
        long copyOfNumber;
        int lastDigit;
        long reverse = 0;

        copyOfNumber = number;

        // Remove one digit at a time
        while (number > 0) {
            lastDigit = getLastDigit(number);
            // Multiplying by 10 adds a 0 to the end of the number
            reverse = (reverse * 10) + lastDigit;
            number /= 10;
        }

        return copyOfNumber == reverse;
    }
}
