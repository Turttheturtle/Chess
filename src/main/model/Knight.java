package model;

import java.util.ArrayList;
import java.util.List;

//models Knight
public class Knight extends Piece {

    //REQUIRES: posY and posX are between 0 and 7
    //EFFECTS: creates a Knight with given parameters
    public Knight(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
    }

    //EFFECTS: returns a list of valid moves for this knight without checking is illegal due to putting self in Check
    public List<int[]> showValidMovesWithoutCheck() {
        ArrayList<int[]> moves = new ArrayList<>();
        int y = getPosY();
        int x = getPosX();
        ifValidAddToList(moves, y + 1, x + 2);
        ifValidAddToList(moves, y + 1, x - 2);
        ifValidAddToList(moves, y - 1, x + 2);
        ifValidAddToList(moves, y - 1, x - 2);
        ifValidAddToList(moves, y + 2, x + 1);
        ifValidAddToList(moves, y + 2, x - 1);
        ifValidAddToList(moves, y - 2, x + 1);
        ifValidAddToList(moves, y - 2, x - 1);
        return moves;
    }
}
