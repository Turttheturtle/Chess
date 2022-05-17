package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class BishopTest extends PiecesTest {
    private Bishop bishopCorner;
    private Bishop bishopXEdge;
    private Bishop bishopYEdge;
    private Bishop bishopTest;
    private Bishop bishopBlockedTest;

    @BeforeEach
    void runBefore(){
        Piece[][] board = new Piece[8][8];
        bishopCorner = new Bishop(0,0,true, board);
        bishopXEdge = new Bishop(4, 7, true, board);
        bishopYEdge = new Bishop(7, 6, true, board);
        bishopTest = new Bishop( 2, 5, false, board);
        Piece[][] blockedBoard = new Piece[8][8];
        bishopBlockedTest = new Bishop(2 , 3,false, blockedBoard);
        blockedBoard[1][2] = new Pawn(1 ,2,false,blockedBoard);
        blockedBoard[4][5] = new Pawn(4,5,true,blockedBoard);
    }

    @Test
    void testShowValidMovesCorner(){
        List<int[]> moves = bishopCorner.showValidMoves();
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {1,1});
        expectedMoves.add(new int[] {2,2});
        expectedMoves.add(new int[] {3,3});
        expectedMoves.add(new int[] {4,4});
        expectedMoves.add(new int[] {5,5});
        expectedMoves.add(new int[] {6,6});
        expectedMoves.add(new int[] {7,7});
        assertTrue(listOfMovesEquals(expectedMoves,moves));
    }

    @Test
    void testShowValidMovesXEdge() {
        List<int[]> moves = bishopXEdge.showValidMoves();
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {3,6});
        expectedMoves.add(new int[] {2,5});
        expectedMoves.add(new int[] {5,6});
        expectedMoves.add(new int[] {6,5});
        expectedMoves.add(new int[] {7,4});
        assertTrue(listOfMovesEquals(expectedMoves,moves));
    }

    @Test
    void testShowValidMovesXGeneral() {
        List<int[]> moves = bishopTest.showValidMoves(); // 2 5
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {1,4});
        expectedMoves.add(new int[] {0,3});
        expectedMoves.add(new int[] {1,6});
        expectedMoves.add(new int[] {0,7});
        expectedMoves.add(new int[] {3,4});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {5,2});
        expectedMoves.add(new int[] {6,1});
        expectedMoves.add(new int[] {7,0});
        expectedMoves.add(new int[] {3,6});
        expectedMoves.add(new int[] {4,7});
        assertTrue(listOfMovesEquals(expectedMoves,moves));
    }

    @Test
    void testShowValidMovesYEdge(){
        List<int[]> moves = bishopYEdge.showValidMoves();
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {6,7});
        expectedMoves.add(new int[] {1,0});
        expectedMoves.add(new int[] {2,1});
        expectedMoves.add(new int[] {3,2});
        expectedMoves.add(new int[] {4,3});
        expectedMoves.add(new int[] {5,4});
        expectedMoves.add(new int[] {6,5});
        assertTrue(listOfMovesEquals(expectedMoves,moves));
    }

    @Test
    void testShowValidMovesBlocked() {
        List<int[]> moves = bishopBlockedTest.showValidMoves();
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {1, 4});
        expectedMoves.add(new int[] {0, 5});
        expectedMoves.add(new int[] {3, 2});
        expectedMoves.add(new int[] {4, 1});
        expectedMoves.add(new int[] {5, 0});
        expectedMoves.add(new int[] {3, 4});
        expectedMoves.add(new int[] {4, 5});
        assertTrue(listOfMovesEquals(expectedMoves,moves));
    }
}
