package ui;

import model.Egg;

public class EggThread extends Thread {
    
    private Egg egg;
    private Boolean running = true;

    public EggThread(Egg egg) {
        this.egg = egg;
    }

    @Override
    public void run() {
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
        }
    }

    // MODIFIES: this
    // EFFECTS: stop the thread
    public void stopThread() {
        this.running = false;
    }

    public void display() {
        System.out.println(egg.getDisplayName() + "\t" + egg.getRemainingTimeInMinute());;
    }

    public Egg getEgg() {
        return egg;
    }

    public Boolean getRunning() {
        return running;
    }

}
