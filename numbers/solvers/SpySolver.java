package numbers.solvers;

import java.util.List;

import static numbers.utils.NumericUtils.*;

/**
 * A number is said to be Spy if the sum of all digits is equal to the product of all digits.
 */
public class SpySolver {
    public static boolean isSpy(long number) {

        // Split into digits
        var digits = getDigits(number);

        // Calculate sum
        long sum = getSum(digits);

        // Calculate product
        long product = getProduct(digits);

        return sum == product;
    }



    private static long getSum(List<Integer> digits) {
        return digits.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static long getProduct(List<Integer> digits) {
        return digits.stream()
                .mapToLong(Integer::intValue)
                .reduce(1, Math::multiplyExact);
    }
}
