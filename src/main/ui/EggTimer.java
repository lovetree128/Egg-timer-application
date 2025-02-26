package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Egg;

public class EggTimer {
    private List<EggThread> eggThreads;
    private Scanner input;
    private List<String> eggNames;

    // EFFECTS: constructs an empty list of eggs and
    // a scanner to receive user input.
    public EggTimer() {
        eggThreads = new ArrayList<EggThread>();
        input = new Scanner(System.in);
        eggNames = new ArrayList<>();
        for (EggThread thread : eggThreads) {
            eggNames.add(thread.getEgg().getDisplayName());
        }
    }

    // MODIFIES: this
    // EFFECTS: starts the timer
    public void startTimer() {
        chooseEgg();
        for (EggThread eggThread : eggThreads) {
            eggThread.start();
            System.out.println(eggThread.getEgg().getDisplayName() + " " + "is started");
        }
        Thread userInputThread = new Thread(() -> handleRunningInput());
        userInputThread.start();
    }

    // EFFECTS: takes input from user and display menus
    public void chooseEgg() {
        boolean start = false;
        String type = null;
        int doneness = 0;
        while (!start) {
            displayEggType();
            type = input.next();
            input.nextLine();
            if (type.equals("start")) {
                start = true;
            } else {
                displayDoneness();
                doneness = input.nextInt();
                handleCommand(type, doneness);
            }
        }
    }

    // EFFECTS: checks if all the eggs are cooked
    public boolean isAllDone() {
        for (EggThread eggThread : eggThreads) {
            if (!eggThread.getEgg().isDone()) {
                return false;
            }
        }
        return true;
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
        eggThreads.add(new EggThread(egg));
    }

    // EFFECTS: handles the user input while the egg threads are running
    public void handleRunningInput() {
        while (!isAllDone()) {
            System.out.println("Please enter the name of the timer you want to stop\nor\n"
                    + "Enter 'show time' to see the remaining time of the timers\nor\n"
                    + "Enter 'add' to add a new timer");
            String userInput = input.nextLine();
            if (!userInput.equals("add")) {
                stopAndShow(userInput);
            } else {
                addTimer(userInput);
            }
        }
        System.out.println("All eggs are cooked!");
    }

    public void addTimer(String userInput) {
        chooseEgg();
        eggThreads.get(eggThreads.size() - 1).start();
    }

    // EFFECTS: stops the timer or shows the remaining time of the timers
    public void stopAndShow(String userInput) {
        if (!userInput.equals("show time")) {
            for (EggThread eggThread : eggThreads) {
                if (eggThread.getEgg().getDisplayName().equals(userInput)) {
                    eggThread.stopThread();
                    System.out.println(userInput + " timer stopped.");
                    return;
                }
            }
        }
        if (userInput.equals("show time")) {
            for (EggThread eggThread : eggThreads) {
                if (eggThread.getRunning() && !eggThread.getEgg().isDone()) {
                    System.out.println("The remaining time of " + eggThread.getEgg().getDisplayName() + " is "
                            + eggThread.getEgg().getRemainingTimeInMinute());
                }
            }
            return;
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    public List<EggThread> getEggThreads() {
        return eggThreads;
    }
}
