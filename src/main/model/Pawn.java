package model;

import java.util.ArrayList;
import java.util.List;

//models a Pawn , hasMoved is used to see if the pawn has the ability to move up two spaces
public class Pawn extends Piece {
    protected boolean hasMoved;

    //REQUIRES: posY and posX are between 0 and 7
    //EFFECTS: creates a pawn with given parameters
    public Pawn(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
    }

    //EFFECTS: returns a list of valid moves for this pawn without checking is illegal due to putting self in Check
    public List<int[]> showValidMovesWithoutCheck() {
        if (getIsWhite()) {
            return showValidMovesWhite();
        } else {
            return showValidMovesBlack();
        }
    }

    //EFFECTS: helper function for Pawn's showValidMoves, returns a list of valid moves without checking if it is legal
    // due to putting itself in check when it is piece white;
    private List<int[]> showValidMovesWhite() {
        ArrayList<int[]> moves = new ArrayList();
        String tempCheckPosition1 = checkPosition(getPosY() - 1, getPosX());
        if (tempCheckPosition1.equals("empty")) {
            moves.add(new int[] {getPosY() - 1, getPosX()});
            if (getPosY() == 6) {
                if (checkPosition(getPosY() - 2,getPosX()).equals("empty")) {
                    moves.add(new int[] {getPosY() - 2, getPosX()});
                }
            }
        }
        ifValidAddToList(moves,getPosY() - 1, getPosX() + 1);
        ifValidAddToList(moves,getPosY() - 1, getPosX() - 1);
        return moves;
    }


    //EFFECTS: helper function for Pawn's showValidMoves, returns a list of valid moves without checking if it is legal
    // due to putting itself in check when it is piece black;
    private List<int[]> showValidMovesBlack() {
        ArrayList<int[]> moves = new ArrayList<>();
        String tempCheckPosition1 = checkPosition(getPosY() + 1, getPosX());
        if (tempCheckPosition1.equals("empty")) {
            moves.add(new int[] {getPosY() + 1, getPosX()});
            if (getPosY() == 1) {
                if (checkPosition(getPosY() + 2,getPosX()).equals("empty")) {
                    moves.add(new int[] {getPosY() + 2, getPosX()});
                }
            }
        }
        ifValidAddToList(moves,getPosY() + 1, getPosX() + 1);
        ifValidAddToList(moves,getPosY() + 1, getPosX() - 1);
        return moves;
    }

    //EFFECTS: Overrides IfValidAddToList for pawns so that you cant take pieces by moving only in y direction,
    //since you have to take pieces diagonally with pawns
    protected void ifValidAddToList(List<int[]> moves, int y, int x) {
        String check = checkPosition(y,x);
        if (! check.equals("invalid") && ((check.equals("white") && !isWhite) || (check.equals("black") && isWhite))) {
            moves.add(new int[] {y,x});
        }
    }

    public void setHasMovedToTrue() {
        hasMoved = true;
    }

}
