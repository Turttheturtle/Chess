package model;

import java.util.ArrayList;
import java.util.List;

//models a bishop
public class Bishop extends DiagonalPiece {
    //EFFECTS: creates a bishop with given parameters
    public Bishop(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
    }

    //EFFECTS: returns a list of valid moves for this bishop without checking is illegal due to putting self in Check
    public List<int[]> showValidMovesWithoutCheck() {
        ArrayList<int[]> moves = new ArrayList();
        moves.addAll(showValidMovesUpRight());
        moves.addAll(showValidMovesUpLeft());
        moves.addAll(showValidMovesDownRight());
        moves.addAll(showValidMovesDownLeft());
        return moves;
    }

}
