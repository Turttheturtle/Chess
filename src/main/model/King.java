package model;

import java.util.List;
import java.util.ArrayList;

//Models a king, hasMoved will be used for castling but not implented yet
public class King extends Piece {
    private final boolean hasMoved;

    //REQUIRES: posY and posX are between 0 and 7
    //EFFECTS: creates a King with given parameters
    public King(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
        hasMoved = false;
    }

    //EFFECTS: return a list of valid moves for this King without checking is illegal due to putting self in Check
    public List<int[]> showValidMovesWithoutCheck() {
        ArrayList<int[]> moves = new ArrayList<>();
        int x = getPosX();
        int y = getPosY();
        ifValidAddToList(moves,y + 1, x);
        ifValidAddToList(moves,y + 1, x + 1);
        ifValidAddToList(moves,y + 1, x - 1);
        ifValidAddToList(moves,y - 1, x);
        ifValidAddToList(moves,y - 1, x + 1);
        ifValidAddToList(moves,y - 1, x - 1);
        ifValidAddToList(moves,y,x + 1);
        ifValidAddToList(moves,y,x - 1);
        return moves;
    }

}
