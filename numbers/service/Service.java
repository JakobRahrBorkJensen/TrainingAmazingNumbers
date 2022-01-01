package numbers.service;

import numbers.request.Request;
import numbers.result.Result;
import numbers.solvers.*;

import java.util.ArrayList;
import java.util.List;

import static numbers.request.Request.IsAnyPropertiesSpecified;

/**
 * This service class contains all logic for creation results and determining whether these are relevant or not
 */
public class Service {

    /**
     * Create a new result for a specified number, where all properties are determined prior to creation.
     */
    public static Result getResult(long number) {// Construct result
        return new Result(
                number,
                ParitySolver.isEven(number),
                BuzzSolver.isBuzz(number),
                DuckSolver.isDuck(number),
                PalindromicSolver.isPalindromic(number),
                GapfulSolver.isGapful(number),
                SpySolver.isSpy(number),
                SquareSolver.isSquare(number),
                SunnySolver.isSunny(number),
                JumpingSolver.isJumping(number),
                HappySadSolver.isHappy(number));
    }

    /**
     * Determines the list of returned results. Identifies if each number/result contains the expected properties.
     * 
     * A weak iterator strategy is used, iterating through all numbers one by one. 
     * For some properties, this has turned out to be inefficient when the provided number is large, especially 
     * properties Jumping and Square, as the "distance" in between numbers with this trait can be huge.
     */
    public static List<Result> determineResults(Request request) {
        var results = new ArrayList<Result>();
        long number = request.getNumber();
        do {
            var result = getResult(number);

            if (IsAnyPropertiesSpecified(request)) {
                if (doesResultComplyWithRequestProperties(request, result)) {
                    results.add(result);
                }
            } else {
                results.add(result);
            }
            number++;
        } while (results.size() < request.getCount());
        return results;
    }

    /**
     * Determines if result complies with desired and unwanted property specifications
     */
    private static boolean doesResultComplyWithRequestProperties(Request request, Result result) {
        String resultProperties = result.toStringSlim();
        boolean containsAllDesired = request.getDesiredProperties().stream()
                .allMatch(property -> resultProperties.contains(property.getName()));
        boolean containsNoUnwanted = request.getUnwantedProperties().stream()
                .noneMatch(property -> resultProperties.contains(property.getName()));

        return containsAllDesired && containsNoUnwanted;
    }
}
