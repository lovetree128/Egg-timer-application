package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.EggTimer;

public class TestTimer {
    EggTimer testTimer;
    Egg boiledEgg;
    Egg friedEgg;
    Egg scrambledEgg;

    @BeforeEach
    void runBefore() {
        testTimer = new EggTimer();
        boiledEgg = new Egg("boiled",1);
        friedEgg = new Egg("fried",1);
        scrambledEgg = new Egg("scrambledEgg",1);
    }

}
