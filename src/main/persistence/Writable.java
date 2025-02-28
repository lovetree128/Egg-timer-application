package persistence;

import org.json.JSONObject;

// Reference: Writable interface from JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as jsonobject
    JSONObject toJson();
}
