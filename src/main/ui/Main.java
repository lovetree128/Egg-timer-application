package ui;

public class Main {
    public static void main(String[] args) throws Exception {
        EggTimer myEggTimer = new EggTimer();
        myEggTimer.addEgg("boiled", 1);
        myEggTimer.addEgg("boiled", 2);
        myEggTimer.addEgg("boiled", 3);
        myEggTimer.startTimer();
    }
}
