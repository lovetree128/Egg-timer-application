package model;

// Represents an egg with user input method of cooking a specified time of cooking.
public class Egg {
    final int BOILED_EGG_SOFT_TIME = 360;
    final int BOILED_EGG_MEDIUM_TIME = 480;
    final int BOILED_EGG_HARD_TIME = 600;

    final int FRIED_EGG_SOFT_TIME = 120;
    final int FRIED_EGG_MEDIUM_TIME = 180;
    final int FRIED_EGG_HARD_TIME = 240;

    final int SCRAMBLED_EGG_SOFT_TIME = 180;
    final int SCRAMBLED_EGG_MEDIUM_TIME = 240;
    final int SCRAMBLED_EGG_HARD_TIME = 300;

    private String method;
    private int doneness;
    private int remainingTime;

    // REQUIRES: method must not be an empty string and method can't be
    // any string other than "boiled", "fried" or "scrambled"
    // EFFECTS: constructs an egg with a user desired method
    // and a remainingTime of cooking according to the method.
    public Egg(String method, int doneness) {
        this.method = method;
        this.doneness = doneness;
        assignTime();
    }

    public void assignTime() {
        int cook = 0;
        switch (method) {
            case "boiled":
                cook += 10;
                break;
            case "fried":
                cook += 20;
                break;
            case "scrambled":
                cook += 30;
                break;
        }
        cook += doneness;
        switch (cook) {
            case 11:
                remainingTime = BOILED_EGG_SOFT_TIME;
                break;
            case 21:
                remainingTime = FRIED_EGG_SOFT_TIME;
                break;
            case 31:
                remainingTime = SCRAMBLED_EGG_SOFT_TIME;
                break;
            case 12:
                remainingTime = BOILED_EGG_MEDIUM_TIME;
                break;
            case 22:
                remainingTime = FRIED_EGG_MEDIUM_TIME;
                break;
            case 32:
                remainingTime = SCRAMBLED_EGG_MEDIUM_TIME;
                break;
            case 13:
                remainingTime = BOILED_EGG_HARD_TIME;
                break;
            case 23:
                remainingTime = FRIED_EGG_HARD_TIME;
                break;
            case 33:
                remainingTime = SCRAMBLED_EGG_HARD_TIME;
        }
    }

    // MODIFIES: this
    // EFFECTS: reduce the remaining time of the egg by 1
    public void eggTimeReduce() {
        remainingTime -= 1;
    }

    public boolean isDone() {
        return remainingTime == 0;
    }

    public String getMethod() {
        return method;
    }

    public String getDoneness() {
        switch (doneness) {
            case 1:
                return "soft";
            case 2:
                return "medium";
            case 3:
                return "hard";
            default:
                return null;
        }
    }

    public int getRemainingTime() {
        return remainingTime;
    }

}
