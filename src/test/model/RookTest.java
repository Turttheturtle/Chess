package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class RookTest extends PiecesTest {
    private Rook whiteCollisionTestRook;
    private Rook blackCollisionTestRook;
    private Rook borderCollisionRook;

    @BeforeEach
    void runBefore() {
        Piece[][] board1 = new Piece[8][8];
        Piece[][] board2 = new Piece[8][8];
        whiteCollisionTestRook = new Rook(4,4,true,board1);
        blackCollisionTestRook = new Rook(4,4,true, board2);
        new Pawn(2,4,true,board1);
        new Pawn(6,4,true,board1);
        new Pawn(4,2,true,board1);
        new Pawn(4,6,true,board1);
        new Pawn(2,4,false,board2);
        new Pawn(6,4,false,board2);
        new Pawn(4,2,false,board2);
        new Pawn(4,6,false,board2);
        Piece[][] board3 = new Piece[8][8];
        borderCollisionRook = new Rook(4,4,true,board3);
    }

    @Test
    void testShowValidMovesWhiteCollision() {
        List<int[]> expectedMoves = new ArrayList<>();
        List<int[]> actualMoves = whiteCollisionTestRook.showValidMoves();
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {4,5});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }

    @Test
    void testShowValidMovesBlackCollision() {
        List<int[]> expectedMoves = new ArrayList<>();
        List<int[]> actualMoves = blackCollisionTestRook.showValidMoves();
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {4,5});
        expectedMoves.add(new int[] {4,6});
        expectedMoves.add(new int[] {4,2});
        expectedMoves.add(new int[] {2,4});
        expectedMoves.add(new int[] {6,4});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }

    @Test
    void showValidMovesBorderCollision() {
        List<int[]> expectedMoves = new ArrayList<>();
        List<int[]> actualMoves = borderCollisionRook.showValidMoves();
        expectedMoves.add(new int[] {0,4});
        expectedMoves.add(new int[] {1,4});
        expectedMoves.add(new int[] {2,4});
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {6,4});
        expectedMoves.add(new int[] {7,4});
        expectedMoves.add(new int[] {4,0});
        expectedMoves.add(new int[] {4,1});
        expectedMoves.add(new int[] {4,2});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {4,5});
        expectedMoves.add(new int[] {4,6});
        expectedMoves.add(new int[] {4,7});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }

    @Test
    void testSetHasMovedToTrue() {
        borderCollisionRook.setHasMovedToTrue();
        assertTrue(borderCollisionRook.getHasMoved());
    }
}
