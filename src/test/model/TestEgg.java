package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEgg {
    Egg egg;
    @BeforeEach
    void runBefore() {
        egg = new Egg("boiled",1);

    }

    @Test
    void sampleTest() {
        assertEquals("boiled", egg.getMethod());
    }
}
