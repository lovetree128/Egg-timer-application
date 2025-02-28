package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents an egg with user input method of cooking a specified time of cooking.
public class Egg implements Writable {
    public static final int BOILED_EGG_SOFT_TIME = 360;
    public static final int BOILED_EGG_MEDIUM_TIME = 480;
    public static final int BOILED_EGG_HARD_TIME = 600;

    public static final int FRIED_EGG_SOFT_TIME = 120;
    public static final int FRIED_EGG_MEDIUM_TIME = 180;
    public static final int FRIED_EGG_HARD_TIME = 240;

    public static final int SCRAMBLED_EGG_SOFT_TIME = 180;
    public static final int SCRAMBLED_EGG_MEDIUM_TIME = 240;
    public static final int SCRAMBLED_EGG_HARD_TIME = 300;

    private String method;
    private int doneness;
    private int remainingTime;
    private int cookIndex;

    // REQUIRES: method must not be an empty string and method can't be
    // any string other than "boiled", "fried" or "scrambled"
    // EFFECTS: constructs an egg with a user desired method
    // and a remainingTime of cooking according to the method.
    public Egg(String method, int doneness) {
        this.method = method;
        this.doneness = doneness;
        this.cookIndex = 0;
        assignTime();
    }

    // MODIFIES: this
    // EFFECTS: transforms the method and doneness to index
    public void toIndex() {
        switch (method) {
            case "boiled":
                cookIndex += 10;
                break;
            case "fried":
                cookIndex += 20;
                break;
            case "scrambled":
                cookIndex += 30;
                break;
        }
        cookIndex += doneness;
    }

    // MODIFIES: this
    // EFFECTS: call methods to assign cooking time according to the index
    public void assignTime() {
        toIndex();
        assignTimeForBoiled();
        assignTimeForFried();
        assignTimeForScrambled();
    }

    // MODIFIES: this
    // EFFECTS: assign time for the boiled method eggs
    public void assignTimeForBoiled() {
        switch (cookIndex) {
            case 11:
                remainingTime = BOILED_EGG_SOFT_TIME;
                break;
            case 12:
                remainingTime = BOILED_EGG_MEDIUM_TIME;
                break;
            case 13:
                remainingTime = BOILED_EGG_HARD_TIME;
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: assign time for the fried method eggs
    public void assignTimeForFried() {
        switch (cookIndex) {
            case 21:
                remainingTime = FRIED_EGG_SOFT_TIME;
                break;
            case 22:
                remainingTime = FRIED_EGG_MEDIUM_TIME;
                break;
            case 23:
                remainingTime = FRIED_EGG_HARD_TIME;
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: assign time for the scrambled method eggs
    public void assignTimeForScrambled() {
        switch (cookIndex) {
            case 31:
                remainingTime = SCRAMBLED_EGG_SOFT_TIME;
                break;
            case 32:
                remainingTime = SCRAMBLED_EGG_MEDIUM_TIME;
                break;
            case 33:
                remainingTime = SCRAMBLED_EGG_HARD_TIME;
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: reduce the remaining time of the egg by 1
    public void eggTimeReduce() {
        remainingTime -= 1;
    }

    // MODIFIES: this
    // EFFECTS: check if the egg is done (remainingTime == 0)
    public boolean isDone() {
        return remainingTime <= 0;
    }

    // EFFECTS: gets the remainingtime of the egg in minutes
    public String getRemainingTimeInMinute() {
        String eggTimeMinute;
        if (isDone()) {
            eggTimeMinute = (getDisplayName() + " is cooked!");
        } else {
            int min = getRemainingTime() / 60;
            int sec = getRemainingTime() % 60;
            if (sec <= 9) {
                eggTimeMinute = (min + ":" + "0" + sec);
            } else {
                eggTimeMinute = (min + ":" + sec);
            }
        }
        return eggTimeMinute;
    }

    // EFFECTS: gets the name of the egg for display
    public String getDisplayName() {
        return getMethod() + " " + getDonenessInString();
    }
 
    // EFFECTS: gets the string version of doneness
    public String getDonenessInString() {
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

    public String getMethod() {
        return method;
    }

    public int getDoneness() {
        return doneness;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getCookIndex() {
        return cookIndex;
    }

    public void setRemainingTime(int time) {
        remainingTime = time;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Method", method);
        json.put("Doneness", doneness);
        json.put("Remaining time", remainingTime);
        return json;
    }
}
