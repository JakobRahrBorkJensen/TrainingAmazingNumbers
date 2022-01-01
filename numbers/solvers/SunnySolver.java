package numbers.solvers;

/**
 * N is a sunny number if N+1 is a perfect square number. See {@link SquareSolver} for information on square numbers
 */
public class SunnySolver {
    public static boolean isSunny(long number) {
        return SquareSolver.isSquare(number+1);
    }
}
