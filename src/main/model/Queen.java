package model;

import java.util.ArrayList;
import java.util.List;

//models a Queen
public class Queen extends DiagonalPiece {
    //REQUIRES: posY and posX are between 0 and 7
    //EFFECTS: creates a Queen with given parameters
    public Queen(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
    }

    //MODIFIES: this
    //EFFECTS: shows valid moves for this queen without checking is illegal due to putting self in Check
    public List<int[]> showValidMovesWithoutCheck() {
        ArrayList<int[]> moves = new ArrayList();
        moves.addAll(showValidMovesUpRight());
        moves.addAll(showValidMovesUpLeft());
        moves.addAll(showValidMovesDownRight());
        moves.addAll(showValidMovesDownLeft());
        moves.addAll(showValidMovesXHelper1());
        moves.addAll(showValidMovesXHelper2());
        moves.addAll(showValidMovesYHelper1());
        moves.addAll(showValidMovesYHelper2());
        return moves;
    }

    //EFFECTS: helper for showValidMovesWithoutCheck, shows the valid moves made by going left
    private List<int[]> showValidMovesXHelper1() {
        int x = getPosX();
        int y = getPosY();
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            String tempCheckPosition = checkPosition(y, x - i);
            if (tempCheckPosition.equals("empty")) {
                moves.add(new int[] {y,x - i});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                moves.add(new int[] {y,x - i});
                break;
            }
        }
        return moves;
    }

    //EFFECTS: helper for showValidMovesWithoutCheck, shows the valid moves made by going right
    private List<int[]> showValidMovesXHelper2() {
        int x = getPosX();
        int y = getPosY();
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i <= 7 - x; i++) {
            String tempCheckPosition = checkPosition(y, x + i);
            if (tempCheckPosition.equals("empty")) {
                moves.add(new int[] {y,x + i});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                moves.add(new int[] {y,x + i});
                break;
            }
        }
        return moves;
    }

    //EFFECTS: helper for showValidMovesWithoutCheck, shows the valid moves made by going down
    private List<int[]> showValidMovesYHelper1() {
        int x = getPosX();
        int y = getPosY();
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i <= 7 - y; i++) {
            String tempCheckPosition = checkPosition(y + i,x);
            if (tempCheckPosition.equals("empty")) {
                moves.add(new int[] {y + i,x});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                moves.add(new int[] {y + i,x});
                break;
            }
        }
        return moves;
    }

    //EFFECTS: helper for showValidMovesWithoutCheck, shows the valid moves made by going up
    private List<int[]> showValidMovesYHelper2() {
        int x = getPosX();
        int y = getPosY();
        ArrayList<int[]> moves = new ArrayList<>();
        for (int i = 1; i <= y; i++) {
            String tempCheckPosition = checkPosition(y - i,x);
            if (tempCheckPosition.equals("empty")) {
                moves.add(new int[] {y - i,x});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                moves.add(new int[] {y - i,x});
                break;
            }
        }
        return moves;
    }
}
