package model;

import exceptions.CheckmateException;
import exceptions.IllegalMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessGameTest {
    private ChessGame chessGameTest;
    private ChessGame chessGameWrongInput;
    private ChessGame chessGameTestInvalid;
    private ChessGame chessGameWhiteCheckmated;
    private ChessGame chessGameBlackCheckmated;

    @BeforeEach
    void runBefore() {
        chessGameTest = new ChessGame("white vs bot");
        chessGameWrongInput = new ChessGame("this is wrong");
        chessGameTestInvalid = new ChessGame("white vs bot");
        chessGameWhiteCheckmated = new ChessGame("white vs black");
        chessGameBlackCheckmated = new ChessGame("white vs black");

    }

    @Test
    void testConstructor() {
        assertTrue(chessGameWrongInput.getBlackPlayer() == null);
        assertTrue(chessGameWrongInput.getWhitePlayer() == null);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertTrue(chessGameWrongInput.getBoard()[i][j] == null);
            }
        }
    }

    @Test
    void testHandleTurnValid() {
        try {
            chessGameTest.handleTurn(7,1,5,0);
            assertTrue(chessGameTest.getBoard()[7][1] == null);
            Piece whitePiece = chessGameTest.getBoard()[5][0];
            assertTrue(whitePiece.getIsWhite());
            assertTrue(whitePiece.getPosX() == 0);
            assertTrue(whitePiece.getPosY() == 5);
            assertTrue(whitePiece instanceof Knight);
            assertTrue(chessGameTest.getWhiteToMove());
        } catch (IllegalMoveException e) {

        } catch (CheckmateException e) {

        }
    }

    @Test
    void testHandleTurnInvalid() {
        try {
            chessGameTestInvalid.handleTurn(5,5,5,5);
            fail("was not thrown");
        } catch (IllegalMoveException e) {

        } catch (CheckmateException e) {
            fail("wrong exception");
        }
    }

    @Test
    void testHandleTurnBlackCheckmateException() {
        try {
            chessGameBlackCheckmated.handleTurn(6,4,4,4);
            chessGameBlackCheckmated.handleTurn(0,1,2,0);
            chessGameBlackCheckmated.handleTurn(7,3,3,7);
            chessGameBlackCheckmated.handleTurn(2,0,0,1);
            chessGameBlackCheckmated.handleTurn(7,5,4,2);
            chessGameBlackCheckmated.handleTurn(0,1,2,0);
            chessGameBlackCheckmated.handleTurn(3,7,1,5);
            fail("supposed to throw a CheckmateException");
        } catch (IllegalMoveException e) {
            fail("supposed to throw a CheckmateException");
        } catch (CheckmateException e) {

        }
    }

    @Test
    void testHandleTurnWhiteCheckmateException() {
        try {
            chessGameWhiteCheckmated.handleTurn(7,1,5,0);
            chessGameWhiteCheckmated.handleTurn(1,4,3,4);
            chessGameWhiteCheckmated.handleTurn(5,0,7,1);
            chessGameWhiteCheckmated.handleTurn(0,5,3,2);
            chessGameWhiteCheckmated.handleTurn(7,1,5,0);
            chessGameWhiteCheckmated.handleTurn(0,3,4,7);
            chessGameWhiteCheckmated.handleTurn(5,0,7,1);
            chessGameWhiteCheckmated.handleTurn(4,7,6,5);
        } catch (CheckmateException e) {

        } catch (IllegalMoveException e) {
            fail("supposed to throw a CheckmateException");
        }
    }
}
