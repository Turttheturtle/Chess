package persistence;

import model.*;
import org.json.JSONObject;

import java.io.*;

//modeled by the example from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs JsonWriter with destination equal to given destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: creates new PrintWriter
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //EFFECTS: puts the chess game into the json file
    public void write(ChessGame chessGame) {
        JSONObject json = chessGame.toJson();
        saveToFile(json.toString(4));
    }

    //MODIFES: this
    //EFFECTS closes the writer
    public void close() {
        writer.close();
    }

    //EFFECTS saves the text to the file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
