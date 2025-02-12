package model;

// Represents an egg with user input method of cooking a specified time of cooking.
public class Egg {
    private static final int BOILED_EGG_TIME = 1000;
    private static final int FRIED_EGG_TIME = 1000;
    private static final int SCRAMBLED_EGG_TIME = 1000;
    private String method;
    private int remainingTime = 0;

    // REQUIRES: method must not be an empty string
    // EFFECTS: constructs an egg with a user desired method
    // and a remainingTime of cooking according to the method.
    public Egg(String method) {
        this.method = method;
        if (method == "boiled") {
            remainingTime = BOILED_EGG_TIME;
        } else if (method == "fried") {
            remainingTime = FRIED_EGG_TIME;
        } else {
            remainingTime = SCRAMBLED_EGG_TIME;
        }
    }

    public String getMethod() {
        return method;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

}
