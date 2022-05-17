package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class KnightTest extends PiecesTest {
    private Knight knightTest;

    @BeforeEach
    void runBefore() {
        Piece[][] board = new Piece[8][8];
        knightTest = new Knight(1,2,true,board);
        board[0][0] = new Knight (0,0,true,board);
        board[0][0] = new Knight (0,4,false,board);
        board[0][0] = new Knight (3,1,false,board);
        board[0][0] = new Knight (2,4,true,board);
    }

    @Test
    void testShowValidMoves() {
        List<int[]> expectedMoves = new ArrayList<>();
        List<int[]> actualMoves = knightTest.showValidMoves();
        expectedMoves.add(new int[] {0,4});
        expectedMoves.add(new int[] {2,0});
        expectedMoves.add(new int[] {3,1});
        expectedMoves.add(new int[] {3,3});
        assertTrue(listOfMovesEquals(actualMoves,expectedMoves));
    }
}
