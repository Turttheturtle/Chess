package persistence;


import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//modelled from the example from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest {

    @BeforeEach
    void runBefore() {

    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ChessGame chessGame = reader.read();
            fail();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testRead() {
        JsonReader reader = new JsonReader("./data/test.json");
        try {
            ChessGame chessGame = reader.read();
            assertEquals(chessGame.getBlackPlayer().getTakenPieces().size(), 2);
            assertFalse(chessGame.getBlackPlayer().getIsHuman());
            assertEquals(chessGame.getWhitePlayer().getTakenPieces().size(), 1);
            assertTrue(chessGame.getWhitePlayer().getIsHuman());
            assertTrue(chessGame.getWhiteToMove());
            Piece[][] board = new Piece[8][8];
            new Rook(0,0,false,board);
            new Knight(0,1,false,board);
            new Bishop(0,2,false,board);
            new Queen(0,3,false,board);
            new King(0,4,false,board);
            new Rook(7,0,true,board);
            new Knight(7,1,true,board);
            new Bishop(7,2,true,board);
            new Queen(7,3,true,board);
            new King(7,4,true,board);
            boardEqualTo(chessGame.getBoard(),board);
        } catch (IOException e) {
            fail();
        }
    }

    public boolean boardEqualTo(Piece[][] board1, Piece[][] board2) {
        boolean equal = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board1[i][j]!= null || board2[i][j]!= null) {
                    if(!board1[i][j].toJson().similar(board2[i][j].toJson())) {
                        equal = false;
                    }
                }
            }
        }
        return equal;
    }
}
