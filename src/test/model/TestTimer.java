package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ui.EggTimer;

public class TestTimer {
    EggTimer testTimer;

    @BeforeEach
    void runBefore() {
        testTimer = new EggTimer();
    }

    @Test
    void testEggTimer() {
        assertTrue(testTimer.getEggs().isEmpty());
    }

    @Test
    void testIsAllDoneOneEgg() {
        testTimer.addEgg("boiled", 1);
        assertFalse(testTimer.isAllDone());
        testTimer.getEggs().get(0).setRemainingTime(0);
        assertTrue(testTimer.isAllDone());
    }

    @Test
    void testIsAllDoneMultipleEggs() {
        testTimer.addEgg("boiled", 1);
        testTimer.addEgg("boiled", 2);
        testTimer.addEgg("boiled", 3);
        assertFalse(testTimer.isAllDone());
        testTimer.getEggs().get(0).setRemainingTime(0);
        assertFalse(testTimer.isAllDone());
        testTimer.getEggs().get(1).setRemainingTime(0);
        testTimer.getEggs().get(2).setRemainingTime(0);
        assertTrue(testTimer.isAllDone());
    }

    @Test
    void testTimeReduce() {
        testTimer.addEgg("boiled", 1);
        testTimer.addEgg("boiled", 2);
        testTimer.addEgg("boiled", 3);
        testTimer.timeReduce();
        assertEquals(359, testTimer.getEggs().get(0).getRemainingTime());
        assertEquals(479, testTimer.getEggs().get(1).getRemainingTime());
        assertEquals(599, testTimer.getEggs().get(2).getRemainingTime());
    }

    @Test
    void testToMinute() {
        List<String> testMinute = new ArrayList<String>();
        testTimer.addEgg("boiled", 1);
        testMinute.add("6:00");
        assertEquals(testMinute, testTimer.toMinute());
        testTimer.getEggs().get(0).setRemainingTime(122);
        testMinute.clear();
        testMinute.add("2:02");
        assertEquals(testMinute, testTimer.toMinute());
        testTimer.getEggs().get(0).setRemainingTime(59);
        testMinute.clear();
        testMinute.add("0:59");
        assertEquals(testMinute, testTimer.toMinute());
        testTimer.getEggs().get(0).setRemainingTime(0);
        testMinute.clear();
        testMinute.add("boiled" + "soft" + "is cooked!");
        assertEquals(testMinute, testTimer.toMinute());
        testTimer.getEggs().get(0).setRemainingTime(129);
        testMinute.clear();
        testMinute.add("2:09");
        assertEquals(testMinute, testTimer.toMinute());
    }

    @Test
    void testGetEggMethids() {
        List<String> testMethods = new ArrayList<String>();
        testTimer.addEgg("boiled", 1);
        testMethods.add("boiled" + "soft");
        assertEquals(testMethods, testTimer.getEggMethods());
    }

    @Test
    void testHandleCommand() {
        List<String> testMethods = new ArrayList<String>();
        testMethods.add("boiled" + "soft");
        testMethods.add("fried" + "soft");
        testMethods.add("scrambled" + "soft");
        testTimer.handleCommand("b", 1);
        testTimer.handleCommand("f", 1);
        testTimer.handleCommand("s", 1);
        assertEquals(testMethods, testTimer.getEggMethods());
    }

    @Test
    void testStartTimer() {

    }
}
