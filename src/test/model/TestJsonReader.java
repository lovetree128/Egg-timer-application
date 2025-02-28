package model;

import org.junit.jupiter.api.Test;

import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
    
    @Test
    void testReaderFileNotFound() {
        JsonReader reader = new JsonReader("./data/nihao.json");
        try {
            List<Egg> eggs = reader.read();
            fail();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyList() {
        List<Egg> emptyList = new ArrayList<>();
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            List<Egg> eggs = reader.read();
            assertEquals(emptyList, eggs);
        } catch (Exception e) {
            fail();
        }
    }
}
