package numbers.utils;

public enum Parity {
    ODD("Odd"),
    EVEN("Even");

    String name;

    Parity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
