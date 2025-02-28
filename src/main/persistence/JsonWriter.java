package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import model.Egg;

// Reference: JsonWriter class in JsonSerializationDemo
// Represents a writer that writes JSON representation of List of eggs to file
public class JsonWriter {
    private String fileLocation;
    private PrintWriter writer;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // MODIFIES: this
    // EFFECTS: opnens writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of eggs to file
    public void write(List<Egg> eggs) {
        JSONArray jsonArray = new JSONArray();
        for (Egg egg : eggs) {
            jsonArray.put(egg.toJson());
        }
        writer.print(jsonArray.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
