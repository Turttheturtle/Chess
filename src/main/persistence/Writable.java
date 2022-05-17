package persistence;

import org.json.JSONObject;

//modeled by the example from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    JSONObject toJson();
}
