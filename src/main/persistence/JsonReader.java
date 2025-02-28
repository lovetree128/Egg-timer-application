package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Egg;

// Reference: JsonReader class in JsonSerializationDemo
// Represents a reader that reads eggs from JSON data stored in file
public class JsonReader {
    private String fileLocation;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String fileLocation) {

    }

    // EFFECTS: reads eggs from file and returns it
    public List<Egg> read() {

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String fileLocation) {

    }

    // EFFECTS: parses eggs from JSON array and return list of eggs
    private List<Egg> parseEggs(JSONArray jsonArray) {

    }
}
