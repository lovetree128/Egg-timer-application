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
    public void start() {

    }

    public void addEgg(Egg egg) {
        eggs.add(egg);
    }

    public List<Egg> getEggs() {
        return eggs;
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
            if (egg.isDone()) {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: transforms seconds to minutes
    public String toMinute(Egg egg) {
        int min = egg.getRemainingTime() / 60;
        int sec = egg.getRemainingTime() % 60;
        if (sec >= 0 && sec <= 9) {
            return min + ":" + "0" + sec;
        }
        return min + ":" + sec;
    }
}
