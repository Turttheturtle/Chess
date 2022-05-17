package persistence;

import model.ChessGame;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//modeled from example code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest {
    @Test
    void testInvalidFile() {
        try {
            ChessGame chessGame = new ChessGame("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail();
        } catch (FileNotFoundException e) {

        }
    }

    @Test
    void testWrite() {
        ChessGame chessGame = new ChessGame("white vs bot");
        JsonWriter jsonWriter = new JsonWriter("./data/testwrite.json");
        try {
            jsonWriter.open();
            jsonWriter.write(chessGame);
            jsonWriter.close();
            JsonReader jsonReader = new JsonReader("./data/testwrite.json");
            ChessGame newChessGame = jsonReader.read();
            assertEquals(chessGame.getWhiteToMove(),newChessGame.getWhiteToMove());
            assertEquals(chessGame.getWhitePlayer().getIsHuman(),
                    newChessGame.getWhitePlayer().getIsHuman());
            assertEquals(chessGame.getBlackPlayer().getIsHuman(),
                    newChessGame.getBlackPlayer().getIsHuman());
            assertEquals(chessGame.getBlackPlayer().getTakenPieces().size(),
                    newChessGame.getBlackPlayer().getTakenPieces().size());
            assertEquals(chessGame.getWhitePlayer().getTakenPieces().size(),
                    newChessGame.getWhitePlayer().getTakenPieces().size());
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        }

    }
}
