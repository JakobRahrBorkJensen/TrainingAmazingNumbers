package numbers;

import numbers.exceptions.*;
import numbers.request.Request;
import numbers.request.RequestReceiver;
import numbers.result.Result;

import java.util.List;

import static numbers.service.Service.determineResults;

/**
 * This class handles the flow of the execution. It should be the only class that specifically works with the
 * output console, in order to enable easy refactoring if a GUI should be prefered instead at some point.
 */
public class Main {

    /**
     * Main method handles the flow of the execution.
     */
    public static void main(String[] args) {
        // Welcome
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();

        // Instructions
        printInstructions();
        System.out.println();

        // Receive requests until 0 is received
        while (true) {
            System.out.println("Enter a request:");

            // Receive, convert and validate input
            var request = new Request();
            try {
                request = RequestReceiver.receiveRequest();
            } catch (EmptyRequestException e) {
                printInstructions();
                continue;
            } catch (NotNaturalNumberException |
                    IncorrectPropertyException |
                    MutuallyExclusivePropertiesException e) {
                System.out.println(e.getMessage());
                System.out.println();
                continue;
            } catch(TerminationException e) {
                System.out.println(e.getMessage());
                return;
            } finally {
                System.out.println();
            }

            // Determine properties
            var results = determineResults(request);

            // Run method for printing result
            printResults(results, request);
        }
    }

    /**
     * Detailed view will be printed if only one number is desired. Otherwise a slim version is provided.
     */
    private static void printResults(List<Result> results, Request request) {
        if (request.getCount() == 0) {
            System.out.println(results.get(0).toStringDetailed());
        } else {
            results.stream()
                    .map(Result::toStringSlim)
                    .forEach(System.out::println);
            System.out.println();
        }
    }

    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }
}
