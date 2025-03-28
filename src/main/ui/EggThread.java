package ui;

import java.awt.Toolkit;

import model.Egg;

// Creates a thread for an egg to run individually
public class EggThread extends Thread {
    
    private Egg egg;
    private Boolean running = true;
    private boolean start = false;
    private int originalTime;

    public EggThread(Egg egg) {
        this.egg = egg;
        originalTime = egg.getRemainingTime();
    }

    // EFFECTS: runs the individual timer thread
    @Override
    public void run() {
        start = true;
        while (!egg.isDone()) {
            try {
                if (!running) {
                    System.out.println(egg.getDisplayName() + " " + "is stopped by user");
                    break;
                }
                egg.eggTimeReduce();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (egg.isDone()) {
            System.out.println(egg.getDisplayName() + " " + "is done");
            Toolkit.getDefaultToolkit().beep();
        }
    }

    // MODIFIES: this
    // EFFECTS: stop the thread
    public void stopThread() {
        this.running = false;
    }

    // EFFECTS: shows the name and the current remaining time of the egg
    public void display() {
        System.out.println(egg.getDisplayName() + "\t" + egg.getRemainingTimeInMinute());;
    }

    // MODIFIES: this
    // EFFECTS: restart the timer
    public void restartTimer() {
        egg.setRemainingTime(originalTime);
    }

    public Egg getEgg() {
        return egg;
    }

    public Boolean getRunning() {
        return running;
    }

    public boolean getStart() {
        return start;
    }

}
