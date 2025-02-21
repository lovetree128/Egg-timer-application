package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Egg;

public class EggTimer {
    private List<Egg> eggs;
    private Scanner input;

    // EFFECTS: constructs an empty list of eggs and
    // a scanner to receive user input.
    public EggTimer() {
        eggs = new ArrayList<Egg>();
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: starts the timer
    public void startTimer() {
        chooseEgg();
        try {
            while (!isAllDone()) {
                display();
                timeReduce();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("All eggs are cooked!");
    }

    // EFFECTS: takes input from user and display menus
    public void chooseEgg() {
        boolean start = false;
        String type = null;
        int doneness = 0;
        while (!start) {
            displayEggType();
            type = input.next();
            if (type.equals("start")) {
                start = true;
            } else {
                displayDoneness();
                doneness = input.nextInt();
                handleCommand(type, doneness);
            }
        }
    }

    // EFFECTS: prints the time
    public void display() {
        System.out.println(String.join("\n", assembleList()));
    }

    // EFFECTS: prints the type choose menu
    public void displayEggType() {
        System.out.println("Select from:"
                + "\n\tb -> Boiled"
                + "\n\tf -> Fried"
                + "\n\ts -> Scrambled"
                + "\n\tstart -> start the timer");
    }

    // EFFECTS: prints the doneness choose menu
    public void displayDoneness() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Soft");
        System.out.println("\t2 -> Medium");
        System.out.println("\t3 -> Hard");
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

    // EFFECRS: assembles the two lists of strings together
    public List<String> assembleList() {
        List<String> eggNameList = new ArrayList<String>();
        for (int i = 0; i < eggs.size(); i++) {
            eggNameList.add(getEggMethods().get(i) + "\t" + toMinute().get(i));
        }
        return eggNameList;
    }

    // EFFECTS: transforms seconds to minutes
    public List<String> toMinute() {
        List<String> eggTimeMinute = new ArrayList<String>();
        for (Egg egg : eggs) {
            if (egg.isDone()) {
                eggTimeMinute.add(egg.getMethod() + egg.getDonenessInString() + "is cooked!");
            } else {
                int min = egg.getRemainingTime() / 60;
                int sec = egg.getRemainingTime() % 60;
                if (sec <= 9) {
                    eggTimeMinute.add(min + ":" + "0" + sec);
                } else {
                    eggTimeMinute.add(min + ":" + sec);
                }
            }
        }
        return eggTimeMinute;
    }

    // EFFECTS: get a list of string with the methods and doneness of each egg
    public List<String> getEggMethods() {
        List<String> eggNames = new ArrayList<String>();
        for (Egg egg : eggs) {
            eggNames.add(egg.getMethod() + egg.getDonenessInString());
        }
        return eggNames;
    }

    // EFFECTS: translate the user input and adds egg to eggs list
    public void handleCommand(String command, int doneness) {
        switch (command) {
            case "b":
                addEgg("boiled", doneness);
                break;
            case "f":
                addEgg("fried", doneness);
                break;
            case "s":
                addEgg("scrambled", doneness);
                break;
        }
    }

    // EFFECTS: adds eggs to eggslist
    public void addEgg(String method, int doneness) {
        Egg egg = new Egg(method, doneness);
        eggs.add(egg);
    }

    public List<Egg> getEggs() {
        return eggs;
    }
}
