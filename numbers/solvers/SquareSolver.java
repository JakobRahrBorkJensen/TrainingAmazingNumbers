package numbers.solvers;

/**
 * In mathematics, a square number or a perfect square is an integer that is the square of an integer; 
 * in other words, it is the product of an integer with itself. 
 * For example, 9 is a square number, since it equals 3^2 and can be written as 3×3.
 */
public class SquareSolver {
    public static boolean isSquare(long number) {
        double squareRoot = Math.sqrt(number);
        return squareRoot - Math.floor(squareRoot) == 0;
    }
}
