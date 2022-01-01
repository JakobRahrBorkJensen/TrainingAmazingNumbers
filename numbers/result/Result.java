package numbers.result;

import numbers.utils.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a POJO class for the result of each number investigated.
 */
public class Result {
    private final long number;
    private final boolean isEven;
    private final boolean isBuzz;
    private final boolean isDuck;
    private final boolean isPalindromic;
    private final boolean isGapful;
    private final boolean isSpy;
    private final boolean isSquare;
    private final boolean isSunny;
    private final boolean isJumping;
    private final boolean isHappy;
    private final List<String> positiveProperties;

    public Result(long number, boolean isEven, boolean isBuzz, boolean isDuck, boolean isPalindromic, boolean isGapful, boolean isSpy, boolean isSquare, boolean isSunny, boolean isJumping, boolean isHappy) {
        this.number = number;
        this.isEven = isEven;
        this.isBuzz = isBuzz;
        this.isDuck = isDuck;
        this.isPalindromic = isPalindromic;
        this.isGapful = isGapful;
        this.isSpy = isSpy;
        this.isSquare = isSquare;
        this.isSunny = isSunny;
        this.isJumping = isJumping;
        this.isHappy = isHappy;

        // Determine traits (positive properties) upon creation as attributes are final
        positiveProperties = getPositiveProperties();
    }

    /**
     * Detailed toString provides all attribute values
     */
    public String toStringDetailed() {
        return "Properties of " + number + "\n" +
                "buzz: " + isBuzz + "\n" +
                "duck: " + isDuck + "\n" +
                "palindromic: " + isPalindromic + "\n" +
                "gapful: " + isGapful + "\n" +
                "spy: " + isSpy + "\n" +
                "square: " + isSquare + "\n" +
                "sunny: " + isSunny + "\n" +
                "jumping: " + isJumping + "\n" +
                "sad: " + !isHappy + "\n" +
                "happy: " + isHappy + "\n" +
                "even: " + isEven + "\n" +
                "odd: " + !isEven + "\n";
    }

    /**
     * Slim toString provides only actual traits (positive properties)
     */
    public String toStringSlim() {
        var sb = new StringBuilder();
        sb.append(number).append(" is ");
        int i = 0;
        while (i < positiveProperties.size()) {
            sb.append(positiveProperties.get(i));
            if (i + 1 < positiveProperties.size()) {
                sb.append(", ");
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * Determine list of positive properties
     */
    private List<String> getPositiveProperties() {
        var positiveProperties = new ArrayList<String>();
        if (isBuzz) {
            positiveProperties.add(Property.BUZZ.getName());
        }
        if (isDuck) {
            positiveProperties.add(Property.DUCK.getName());
        }
        if (isPalindromic) {
            positiveProperties.add(Property.PALINDROMIC.getName());
        }
        if (isGapful) {
            positiveProperties.add(Property.GAPFUL.getName());
        }
        if (isSpy) {
            positiveProperties.add(Property.SPY.getName());
        }
        if (isSquare) {
            positiveProperties.add(Property.SQUARE.getName());
        }
        if (isSunny) {
            positiveProperties.add(Property.SUNNY.getName());
        }
        if (isJumping) {
            positiveProperties.add(Property.JUMPING.getName());
        }
        if (isHappy) {
            positiveProperties.add(Property.HAPPY.getName());
        } else {
            positiveProperties.add(Property.SAD.getName());
        }
        if (isEven) {
            positiveProperties.add(Property.EVEN.getName());
        } else {
            positiveProperties.add(Property.ODD.getName());
        }
        return positiveProperties;
    }
}
