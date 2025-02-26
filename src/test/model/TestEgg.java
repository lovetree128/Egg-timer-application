package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEgg {
    Egg boiledEgg;
    Egg friedEgg;
    Egg scrambledEgg;
    Egg boiledHardEgg;
    Egg boiledMediumEgg;
    Egg friedMediumEgg;
    Egg friedHardEgg;
    Egg scrambledMediumEgg;
    Egg scrambledHardEgg;

    @BeforeEach
    void runBefore() {
        boiledEgg = new Egg("boiled", 1);
        friedEgg = new Egg("fried", 1);
        scrambledEgg = new Egg("scrambled", 1);
        boiledMediumEgg = new Egg("boiled", 2);
        boiledHardEgg = new Egg("boiled", 3);
        friedMediumEgg = new Egg("fried", 2);
        friedHardEgg = new Egg("fried", 3);
        scrambledMediumEgg = new Egg("scrambled", 2);
        scrambledHardEgg = new Egg("scrambled", 3);
    }

    @Test
    void testEgg() {
        assertEquals("boiled", boiledEgg.getMethod());
        assertEquals("fried", friedEgg.getMethod());
        assertEquals(1, boiledEgg.getDoneness());
        assertEquals("soft", boiledEgg.getDonenessInString());
    }

    @Test
    void testToIndex() {
        assertEquals(11, boiledEgg.getCookIndex());
        assertEquals(21, friedEgg.getCookIndex());
        assertEquals(31, scrambledEgg.getCookIndex());
        assertEquals(12, boiledMediumEgg.getCookIndex());
        assertEquals(13, boiledHardEgg.getCookIndex());
    }

    @Test
    void testAssignTime() {
        assertEquals(360, boiledEgg.getRemainingTime());
        assertEquals(120, friedEgg.getRemainingTime());
    }

    @Test
    void testEggTimeReduce() {
        assertEquals(360, boiledEgg.getRemainingTime());
        boiledEgg.eggTimeReduce();
        assertEquals(359, boiledEgg.getRemainingTime());
    }

    @Test
    void testIsDone() {
        assertFalse(boiledEgg.isDone());
        boiledEgg.setRemainingTime(0);
        assertTrue(boiledEgg.isDone());
        boiledEgg.setRemainingTime(-1);
        assertTrue(boiledEgg.isDone());
    }

    @Test
    void testGetDonenessInTString() {
        Egg buggyEgg = new Egg("boiled", 4);
        assertEquals("soft", boiledEgg.getDonenessInString());
        assertEquals("medium", boiledMediumEgg.getDonenessInString());
        assertEquals("hard", boiledHardEgg.getDonenessInString());
        assertNull(buggyEgg.getDonenessInString());
    }

    @Test
    void testGetRemainingTimeInMinute() {
        assertEquals("6:00", boiledEgg.getRemainingTimeInMinute());
        boiledEgg.setRemainingTime(0);
        assertEquals("boiled soft is cooked!", boiledEgg.getRemainingTimeInMinute());
        boiledEgg.setRemainingTime(139);
        assertEquals("2:19", boiledEgg.getRemainingTimeInMinute());
    }
}
