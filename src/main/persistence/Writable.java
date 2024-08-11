package persistence;

import org.json.JSONObject;

// Writeable Interface with toJSon method
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJSon();
}
