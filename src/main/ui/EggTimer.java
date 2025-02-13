package ui;

import java.util.ArrayList;
import java.util.List;

import model.Egg;

public class EggTimer {
    private List<Egg> eggs;

    // EFFECTS: constructs an empty list of eggs and 
    // runs the timer application
    public EggTimer() {
        eggs = new ArrayList<Egg>();
    }

    // MODIFIES: this
    // EFFECTS: 
    public void startTimer() {
        try {
            while (!isAllDone()) {
                display();
                timeReduce();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {}
    }

    // EFFECTS: prints the time
    public void display() {
        System.out.println(String.join("\t", getEggMethods()));
        System.out.println(String.join("\t", toMinute()));
    }

    // MODIFIES: this
    // EFFECTS: reduce the remaining time of all eggs by 1
    public void timeReduce() {
        for (Egg egg : eggs) {
            egg.eggTimeReduce();
        }
    }

    // EFFECTS: checks if all the eggs are cooked
    public boolean isAllDone() {
        for (Egg egg : eggs) {
            if (!egg.isDone()) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: transforms seconds to minutes
    public List<String> toMinute() {
        List<String> eggTimeMinute = new ArrayList<String>();
        for (Egg egg : eggs) {
            int min = egg.getRemainingTime() / 60;
            int sec = egg.getRemainingTime() % 60;
            if (sec >= 0 && sec <= 9) {
                eggTimeMinute.add(min + ":" + "0" + sec);
            } else {
                eggTimeMinute.add(min + ":" + sec);
            }
        }
        return eggTimeMinute;
    }

    // EFFECTS: get a list of string with the methods and doneness of each egg
    public List<String> getEggMethods() {
        List<String> eggNames = new ArrayList<String>();
        for (Egg egg : eggs) {
            eggNames.add(egg.getMethod() + egg.getDoneness());
        }
        return eggNames;
    }

    public void addEgg(String method, int doneness) {
        Egg egg = new Egg(method, doneness);
        eggs.add(egg);
    }

    public List<Egg> getEggs() {
        return eggs;
    }
}
