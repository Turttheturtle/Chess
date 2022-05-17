package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class PawnTest extends PiecesTest {
    private Pawn blackStartPawn;
    private Pawn whiteStartPawn;
    private Pawn blackTestPawn;
    private Pawn whiteTestPawn;

    @BeforeEach
    void runBefore() {
        Piece[][] empty= new Piece[8][8];
        Piece[][] blockedBoard = new Piece[8][8];
        blackStartPawn = new Pawn(1 , 0 , false, blockedBoard);
        whiteStartPawn = new Pawn(6 , 5 , true, blockedBoard);
        blackTestPawn = new Pawn(3 ,5, false, blockedBoard);
        whiteTestPawn = new Pawn(4 , 4, true, blockedBoard);

        blackTestPawn.setHasMovedToTrue();
        whiteTestPawn.setHasMovedToTrue();
    }

    @Test
    void testShowValidMovesStart() {
        List<int[]> expectedMovesWhite = new ArrayList<>();
        List<int[]> actualMovesWhite = whiteStartPawn.showValidMoves();
        expectedMovesWhite.add(new int[] {5, 5});
        expectedMovesWhite.add(new int[] {4, 5});
        List<int[]> expectedMovesBlack = new ArrayList<>();
        List<int[]> actualMovesBlack = blackStartPawn.showValidMoves();
        expectedMovesBlack.add(new int[] {2, 0});
        expectedMovesBlack.add(new int[] {3, 0});
        assertTrue(listOfMovesEquals(expectedMovesWhite,actualMovesWhite));
        assertTrue(listOfMovesEquals(expectedMovesBlack,actualMovesBlack));
    }

    @Test
    void testShowValidMovesGeneral() {
        List<int[]> expectedMovesWhite = new ArrayList<>();
        List<int[]> actualMovesWhite = whiteTestPawn.showValidMoves();
        expectedMovesWhite.add(new int[] {3, 4});
        expectedMovesWhite.add(new int[] {3, 5});
        List<int[]> expectedMovesBlack = new ArrayList<>();
        List<int[]> actualMovesBlack = blackTestPawn.showValidMoves();
        expectedMovesBlack.add(new int[] {4, 4});
        expectedMovesBlack.add(new int[] {4, 5});
        assertTrue(listOfMovesEquals(expectedMovesWhite,actualMovesWhite));
        assertTrue(listOfMovesEquals(expectedMovesBlack,actualMovesBlack));
    }
}
