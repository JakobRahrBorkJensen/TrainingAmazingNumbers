package numbers.solvers;

import static numbers.utils.NumericUtils.getDigits;

/**
 * In number theory, a happy number is a number that reaches 1 after a sequence during which
 * the number is replaced by the sum of each digit squares.
 * For example, 13 is a happy number, as 1^2 + 3^2 = 10 which leads to 1^2 + 0^2 = 1.
 * On the other hand, 4 is not a happy number because the sequence starts with 4^2 = 16, 1^2 + 6^2 = 37,
 * and finally reaches 2^2 + 0^2 = 4.
 * This is the number that started the sequence, so the process goes on in an infinite cycle.
 * A number that is not happy is called Sad (or Unhappy).
 */
public class HappySadSolver {
    public static boolean isHappy(long number) {
        while (!isOnlyOneDigit(number)) {
            number = getSummedSquares(number);
        }
        return willResultInHappyNumber(number);
    }

    private static boolean willResultInHappyNumber(long number) {
        return number == 1 || number == 7;
    }

    private static boolean isOnlyOneDigit(long number) {
        return getDigits(number).size() == 1;
    }

    private static long getSummedSquares(long sequenceResult) {
        var digits = getDigits(sequenceResult);
        return digits.stream()
                .mapToLong(d -> (long) d * d)
                .sum();
    }
}
