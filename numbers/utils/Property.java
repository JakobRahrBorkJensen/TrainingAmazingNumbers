package numbers.utils;

/**
 * Enum contains all allowed properties for each number. See explanation for each property in its solver class
 */
public enum Property {
    EVEN,
    ODD,
    BUZZ,
    DUCK,
    PALINDROMIC,
    GAPFUL,
    SPY,
    SQUARE,
    SUNNY,
    JUMPING,
    SAD,
    HAPPY;

    public String getName(){
        return this.name().toLowerCase();
    }
}
