package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PieceTest extends PiecesTest{
    private Piece pieceTest;

    @BeforeEach
    void runBefore() {
        Piece[][] board = new Piece[8][8];
        pieceTest = new King(0,1,true,board);
        new Rook(1,6,false,board);
        new Queen(2,6,false,board);
        new Bishop(3,6,false,board);
        new Knight(4,6,false,board);
        new Pawn(5,6,false,board);
        Pawn movedPawn = new Pawn(6,6,false,board);
        movedPawn.setHasMovedToTrue();
        Rook movedRook = new Rook(7,6,false,board);
        movedRook.setHasMovedToTrue();
    }

    @Test
    void testShowValidMoves() {
        List<int[]> actualMoves = pieceTest.showValidMoves();
        List<int[]> expectedMoves = new ArrayList<>();
        expectedMoves.add(new int[] {0,0});
        expectedMoves.add(new int[] {0,2});
        assertTrue(listOfMovesEquals(actualMoves,expectedMoves));
    }

    @Test
    void testCheckValidMove() {
        assertTrue(pieceTest.checkValidMove(0,0));
        assertFalse(pieceTest.checkValidMove(1,0));
    }

    @Test
    void testToString() {
        assertTrue("Rook".equals(pieceTest.getBoard()[1][6].toString()));
        assertTrue("Queen".equals(pieceTest.getBoard()[2][6].toString()));
        assertTrue("Bishop".equals(pieceTest.getBoard()[3][6].toString()));
        assertTrue("Knight".equals(pieceTest.getBoard()[4][6].toString()));
        assertTrue("Pawn".equals(pieceTest.getBoard()[5][6].toString()));
        assertTrue("King".equals(pieceTest.toString()));
    }

}
