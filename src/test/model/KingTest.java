package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class KingTest extends PiecesTest {
    private King kingTest;

    @BeforeEach
    void runBefore() {
        Piece[][] board = new Piece[8][8];
        kingTest = new King(0,3,false,board);
       new Pawn(1,3,true,board);
       new Pawn(1,4,false,board);
    }

    @Test
    void testShowValidMoves() {
        List<int[]> expectedMoves = new ArrayList<>();
        List<int[]> actualMoves = kingTest.showValidMoves();
        expectedMoves.add(new int[] {1,2});
        expectedMoves.add(new int[] {1,3});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }
}
