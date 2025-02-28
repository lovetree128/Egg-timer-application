package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import persistence.JsonReader;
import persistence.JsonWriter;

public class TestJsonWriter {

    @Test
    void testWriter() {
        try {
            Egg egg = new Egg("boiled", 1);
            List<Egg> eggs = new ArrayList<>();
            eggs.add(egg);
            JsonWriter writer = new JsonWriter("./data/testWriter.json");
            writer.open();
            writer.write(eggs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriter.json");
            List<Egg> readEggs = new ArrayList<>();
            readEggs = reader.read();
            assertEquals(readEggs.get(0).getMethod(), eggs.get(0).getMethod());
            assertEquals(readEggs.get(0).getDoneness(), eggs.get(0).getDoneness());
            assertEquals(readEggs.get(0).getRemainingTime(), eggs.get(0).getRemainingTime());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testWriterNoSuchFileLocation() {
        try {
            Egg egg = new Egg("boiled", 1);
            List<Egg> eggs = new ArrayList<>();
            eggs.add(egg);
            JsonWriter writer = new JsonWriter("./data/my\\0illegal:fileName.json");
            writer.open();
            fail();
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            List<Egg> eggs = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(eggs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            List<Egg> readEggs = new ArrayList<>();
            assertEquals(eggs, readEggs);
            readEggs = reader.read();
        } catch (Exception e) {
            fail();
        }
    }
}
