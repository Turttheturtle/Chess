package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class QueenTest extends PiecesTest {
    private Queen blackCollisionTestQueen;
    private Queen whiteCollisionTestQueen;
    private Queen boundaryCollisionTestQueen;

    @BeforeEach
    void runBefore() {
        Piece[][] board1 = new Piece[8][8];
        Piece[][] board2 = new Piece[8][8];
        Piece[][] board3 = new Piece[8][8];
        blackCollisionTestQueen = new Queen(4,4,true,board1);
        whiteCollisionTestQueen = new Queen(4,4,true,board2);
        boundaryCollisionTestQueen = new Queen(0,0,true,board3);
        new Pawn(2,4,false,board1);
        new Pawn(6,4,false,board1);
        new Pawn(4,2,false,board1);
        new Pawn(4,6,false,board1);
        new Pawn(2,2,false,board1);
        new Pawn(2,6,false,board1);
        new Pawn(6,2,false,board1);
        new Pawn(6,6,false,board1);
        new Pawn(2,4,true,board2);
        new Pawn(6,4,true,board2);
        new Pawn(4,2,true,board2);
        new Pawn(4,6,true,board2);
        new Pawn(2,2,true,board2);
        new Pawn(2,6,true,board2);
        new Pawn(6,2,true,board2);
        new Pawn(6,6,true,board2);
    }

    @Test
    void testShowValidMovesBlackCollision() {
        List<int[]> expectedMoves= new ArrayList<>();
        List<int[]> actualMoves = blackCollisionTestQueen.showValidMoves();
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {4,5});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {5,5});
        expectedMoves.add(new int[] {5,3});
        expectedMoves.add(new int[] {3,3});
        expectedMoves.add(new int[] {3,5});
        expectedMoves.add(new int[] {4,6});
        expectedMoves.add(new int[] {4,2});
        expectedMoves.add(new int[] {2,2});
        expectedMoves.add(new int[] {2,6});
        expectedMoves.add(new int[] {2,4});
        expectedMoves.add(new int[] {6,2});
        expectedMoves.add(new int[] {6,4});
        expectedMoves.add(new int[] {6,6});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }

    @Test
    void testShowValidMovesWhiteCollision() {
        List<int[]> expectedMoves= new ArrayList<>();
        List<int[]> actualMoves = whiteCollisionTestQueen.showValidMoves();
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {4,5});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {5,5});
        expectedMoves.add(new int[] {5,3});
        expectedMoves.add(new int[] {3,3});
        expectedMoves.add(new int[] {3,5});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }

    @Test
    void testShowValidMovesBoundaryCollision() {
        List<int[]> expectedMoves= new ArrayList<>();
        List<int[]> actualMoves = boundaryCollisionTestQueen.showValidMoves();
        expectedMoves.add(new int[] {1,0});
        expectedMoves.add(new int[] {2,0});
        expectedMoves.add(new int[] {3,0});
        expectedMoves.add(new int[] {4,0});
        expectedMoves.add(new int[] {5,0});
        expectedMoves.add(new int[] {6,0});
        expectedMoves.add(new int[] {7,0});
        expectedMoves.add(new int[] {0,1});
        expectedMoves.add(new int[] {0,2});
        expectedMoves.add(new int[] {0,3});
        expectedMoves.add(new int[] {0,4});
        expectedMoves.add(new int[] {0,5});
        expectedMoves.add(new int[] {0,6});
        expectedMoves.add(new int[] {0,7});
        expectedMoves.add(new int[] {1,1});
        expectedMoves.add(new int[] {2,2});
        expectedMoves.add(new int[] {3,3});
        expectedMoves.add(new int[] {4,4});
        expectedMoves.add(new int[] {5,5});
        expectedMoves.add(new int[] {6,6});
        expectedMoves.add(new int[] {7,7});
        assertTrue(listOfMovesEquals(expectedMoves,actualMoves));
    }
}
