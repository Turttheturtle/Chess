package model;

import java.util.ArrayList;
import java.util.List;

//Models a piece that can move diagonally
public abstract class DiagonalPiece extends Piece {
    DiagonalPiece(int posY, int posX, boolean isWhite, Piece[][] board) {
        super(posY, posX, isWhite, board);
    }

    //EFFECTS: helper function for DiagonalPiece's showValidMoves, return a list of valid moves where the piece goes
    // diagonally up right.
    protected List<int[]> showValidMovesUpRight() {
        ArrayList<int[]> tempMoves = new ArrayList();
        int distToXBoundary = 7 - getPosX();
        int steps;
        if (getPosY() < distToXBoundary) {
            steps = getPosY();
        } else {
            steps = distToXBoundary;
        }
        for (int i = 1; i <= steps; i++) {
            int tempPosY = getPosY() - i;
            int tempPosX = getPosX() + i;
            String tempCheckPosition = checkPosition(tempPosY,tempPosX);
            if (tempCheckPosition.equals("empty")) {
                tempMoves.add(new int[] {tempPosY, tempPosX});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                tempMoves.add(new int[] {tempPosY, tempPosX});
                break;
            }
        }
        return tempMoves;
    }

    //EFFECTS: helper function for DiagonalPiece's showValidMoves, return a list of valid moves where the piece goes
    // diagonally down right.
    protected List<int[]> showValidMovesDownRight() {
        ArrayList<int[]> tempMoves = new ArrayList();
        int distToYBoundary = 7 - getPosY();
        int distToXBoundary = 7 - getPosX();
        int steps;
        if (distToYBoundary < distToXBoundary) {
            steps = distToYBoundary;
        } else {
            steps = distToXBoundary;
        }
        for (int i = 1; i <= steps; i++) {
            int tempPosY = getPosY() + i;
            int tempPosX = getPosX() + i;
            String tempCheckPosition = checkPosition(tempPosY,tempPosX);
            if (tempCheckPosition.equals("empty")) {
                tempMoves.add(new int[] {tempPosY, tempPosX});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                tempMoves.add(new int[] {tempPosY, tempPosX});
                break;
            }
        }
        return tempMoves;
    }

    //EFFECTS: helper function for DiagonalPiece's showValidMoves, return a list of valid moves where the piece goes
    // diagonally up left.
    protected List<int[]> showValidMovesUpLeft() {
        ArrayList<int[]> tempMoves = new ArrayList();
        int steps;
        if (getPosY() < getPosX()) {
            steps = getPosY();
        } else {
            steps = getPosX();
        }
        for (int i = 1; i <= steps; i++) {
            int tempPosY = getPosY() - i;
            int tempPosX = getPosX() - i;
            String tempCheckPosition = checkPosition(tempPosY,tempPosX);
            if (tempCheckPosition.equals("empty")) {
                tempMoves.add(new int[] {tempPosY, tempPosX});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                tempMoves.add(new int[] {tempPosY, tempPosX});
                break;
            }
        }
        return tempMoves;
    }

    //EFFECTS: helper function for DiagonalPiece's showValidMoves, return a list of valid moves where the piece goes
    // diagonally down left.
    protected List<int[]> showValidMovesDownLeft() {
        ArrayList<int[]> tempMoves = new ArrayList();
        int distToYBoundary = 7 - getPosY();
        int steps;
        if (distToYBoundary < getPosX()) {
            steps = distToYBoundary;
        } else {
            steps = getPosX();
        }
        for (int i = 1; i <= steps; i++) {
            int tempPosY = getPosY() + i;
            int tempPosX = getPosX() - i;
            String tempCheckPosition = checkPosition(tempPosY,tempPosX);
            if (tempCheckPosition.equals("empty")) {
                tempMoves.add(new int[] {tempPosY, tempPosX});
            } else if (tempCheckPosition.equals("white") == getIsWhite()) {
                break;
            } else {
                tempMoves.add(new int[] {tempPosY, tempPosX});
                break;
            }
        }
        return tempMoves;
    }
}
