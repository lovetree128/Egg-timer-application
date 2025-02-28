package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Egg;
import persistence.JsonReader;
import persistence.JsonWriter;

// Adds the threads to a list. Run them together and handle user input to 
// determine the type of the egg and add or stop the specific egg while running
public class EggTimer {
    private static final String FILE_LOCATION = "./data/eggs.json";
    private List<EggThread> eggThreads;
    private Scanner input;
    private List<Egg> eggs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs an empty list of eggs and
    // a scanner to receive user input.
    public EggTimer() {
        eggThreads = new ArrayList<EggThread>();
        input = new Scanner(System.in);
        eggs = new ArrayList<>();
        jsonReader = new JsonReader(FILE_LOCATION);
        jsonWriter = new JsonWriter(FILE_LOCATION);
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

    // MODIFIES: this
    // EFFECTS: takes input from user and display menus
    public void chooseEgg() {
        boolean start = false;
        String type = null;
        while (!start) {
            displayEggType();
            type = input.next();
            input.nextLine();
            if (!type.equals("b") && !type.equals("f") && !type.equals("s") && !type.equals("start")
                    && !type.equals("l")) {
                if (type.equals("q")) {
                    eggThreads.clear();
                    break;
                }
                System.out.println("Please enter a valid egg type or enter start to start the timers");
            } else {
                if (type.equals("start")) {
                    start = checkEmpty();
                } else {
                    displayDoneness();
                    handleException(type);
                }
            }
        }
    }

    // EFFECTS: throws error information to the user when input is not number
    public void handleException(String type) {
        if (type.equals("l")) {
            handleCommand(type, 0);
        } else {
            int doneness = 0;
            try {
                doneness = input.nextInt();
                if (doneness >= 4 | doneness < 1) {
                    System.out.println("Please enter a valid number (1-3)");
                } else {
                    handleCommand(type, doneness);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer");
                input.nextLine();
            }
        }
    }

    // EFFECTS: checks if the list of thread is empty and inform user to select an
    // egg
    public boolean checkEmpty() {
        if (eggThreads.isEmpty()) {
            System.out.println("Please select an egg before start");
            return false;
        }
        return true;
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
                + "\n\tstart -> start the timer"
                + "\n\tl -> load timers from file"
                + "\n\tq -> Quit");
    }

    // EFFECTS: prints the doneness choose menu
    public void displayDoneness() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Soft");
        System.out.println("\t2 -> Medium");
        System.out.println("\t3 -> Hard");
    }

    // MODIFIES: this
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
            case "l":
                readTimer();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds egg to eggThreads
    public void addEgg(String method, int doneness) {
        Egg egg = new Egg(method, doneness);
        eggThreads.add(new EggThread(egg));
    }

    // MODIFIES: this
    // EFFECTS: handles the user input while the egg threads are running
    public void handleRunningInput() {
        while (!isAllDone()) {
            System.out.println("Select from:"
                    + "\n\tName of the egg -> Stop the egg"
                    + "\n\tshow time -> See the remaining time of the timers"
                    + "\n\tadd -> Add new timer"
                    + "\n\tsave -> save the current timers to file and stop"
                    + "\n\tstop -> stop all the timers");
            String userInput = input.nextLine();
            if (userInput.equals("save")) {
                saveTimer();
                stopAll();
                break;
            } else if (userInput.equals("add")) {
                addTimer(userInput);
            } else if (userInput.equals("stop")) {
                stopAll();
                break;
            } else {
                stopAndShow(userInput);
            }
        }
        System.out.println("All eggs are cooked or the application is stopped by the user");
    }

    // MODIFIES: this
    // EFFECTS: reads the timers from the file
    public void readTimer() {
        List<Egg> eggs = new ArrayList<>();
        try {
            eggs = jsonReader.read();
            for (Egg egg : eggs) {
                eggThreads.add(new EggThread(egg));
            }
            System.out.println("Loaded " + jsonReader.read().size() + " eggs from " + FILE_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + FILE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: stops all the timer and the whole application
    public void stopAll() {
        for (EggThread eggThread : eggThreads) {
            eggThread.stopThread();
        }
    }

    // EFFECTS: saves the egg timers to file
    public void saveTimer() {
        for (EggThread eggThread : eggThreads) {
            eggs.add(eggThread.getEgg());
        }
        try {
            jsonWriter.open();
            jsonWriter.write(eggs);
            jsonWriter.close();
            System.out.println("Saved all eggs to " + FILE_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FILE_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: add a new egg timer thread to the list of threads
    public void addTimer(String userInput) {
        chooseEgg();
        try {
            eggThreads.get(eggThreads.size() - 1).start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Adding stopped by the user");
        }
    }

    // MODIFIES: this
    // EFFECTS: stops the timer
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
