package numbers.solvers;

/**
 * A Duck number is a positive number that contains zeroes. 
 * For example, 3210, 8050896, 70709 are Duck numbers. 
 * Note that a number with a leading 0 is not a Duck number. So, numbers like 035 or 0212 are not Duck numbers. 
 * Although, 01203 is a Duck, since it has a trailing 0.
 */
public class DuckSolver {
    public static boolean isDuck(long number) {
        // Turn number into char array
        var digits = ("" + number).toCharArray();

        // Go through each char in number
        for (char digit : digits) {
            // Check if any digit is 0
            if (digit == '0') return true;
        }
        return false;
    }
}
