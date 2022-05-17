package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//models a piece on the board, the position on the board is given by posX and posY, the color is represented by
//is white, and it needs the other pieces on the board for some methods which is given by the board field
public abstract class Piece implements Writable {
    private int posX;
    private int posY;
    final boolean isWhite;
    private final Piece[][] board;

    //EFFECTS: contructor for abstract piece
    public Piece(int posY, int posX, boolean isWhite, Piece[][] board) {
        this.posX = posX;
        this.posY = posY;
        this.isWhite = isWhite;
        board[posY][posX] = this;
        this.board = board;
        Event clear;
    }

    //REQUIRES: 0 <= posY <= 7 and 0 <= posX <= 7
    //MODIFIES: this
    //EFFECTS: changes the piece's position to given x and y
    public void movePiece(int posY, int posX) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public Piece[][] getBoard() {
        return board;
    }

    //EFFECTS: tells you if the position you are looking at is empty, occupied by a black piece, occupied a white piece
    // or and invalid position
    protected String checkPosition(int posY, int posX) {
        if (posX > 7 || posY > 7 || posX < 0 || posY < 0) {
            return "invalid";
        }
        if (board[posY][posX] instanceof Piece) {
            if (board[posY][posX].getIsWhite()) {
                return "white";
            } else {
                return "black";
            }
        }
        return "empty";
    }

    //EFFECTS: Helper function for some Piece's showValidMoves(), adds the move to the list if the new position is
    // either empty or able to be taken by the piece in question
    protected void ifValidAddToList(List<int[]> moves, int y, int x) {
        String check = checkPosition(y,x);
        if (! check.equals("invalid") && ((check.equals("white") != isWhite) || check.equals("empty"))) {
            moves.add(new int[] {y,x});
        }
    }

    //EFFECTS: check if the Piece is allowed to move to given position
    public boolean checkValidMove(int y, int x) {
        int[] check = {y, x};
        List<int[]> moves = showValidMoves();
        for (int[] move : moves) {
            if (move[0] == check[0]
                    && move[1] == check[1]) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: moves are all valid
    //MODIFIES: moves
    //EFFECTS: helper function for showValidMoves, removes all moves that put yourself in check
    private void removeCheckMoves(List<int[]> moves) {
        for (int i = 0; i < moves.size(); i++) {
            if (putSelfInCheck(moves.get(i))) {
                moves.remove(i);
                i--;
            }
        }
    }

    //EFFECTS: Helper function for removeCheckMoves, returns true if move puts you in check
    private boolean putSelfInCheck(int[] move) {
        Piece[][] copyOfBoard = copyBoard();
        Piece movingPiece = copyOfBoard[posY][posX];
        copyOfBoard[move[0]][move[1]] = movingPiece;
        movingPiece.movePiece(move[0],move[1]);
        copyOfBoard[posY][posX] = null;
        List<int[]> nextMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyOfBoard[i][j] != null && copyOfBoard[i][j].getIsWhite() != isWhite) {
                    List<int[]> tempMoves = copyOfBoard[i][j].showValidMovesWithoutCheck();
                    for (int[] tempMove : tempMoves) {
                        nextMoves.add(new int[] {tempMove[0], tempMove[1]});
                    }
                }
            }
        }
        int[] posKing = getPosOfKing(copyOfBoard);
        for (int[] nextMove : nextMoves) {
            if (nextMove[0] == posKing[0] && nextMove[1] == posKing[1]) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: helper function for putSelfInCheck, creates a copy this pieces board
    private Piece[][] copyBoard() {
        Piece[][] newBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    Piece tempPiece = board[i][j];
                    newBoard[i][j] = board[i][j].copyPiece(newBoard);
                }
            }
        }
        return newBoard;
    }

    //REQUIRES: newBoard is a 8 by 8 array of Pieces
    //Effects: helper function for copy board, creates a copy of this piece and adds it to the given newBoard.
    public Piece copyPiece(Piece[][] newBoard) {
        if (this instanceof Rook) {
            Rook newRook = new Rook(this.posY,this.posX, this.isWhite, newBoard);
            if (((Rook) this).hasMoved) {
                newRook.setHasMovedToTrue();
            }
            return newRook;
        } else if (this instanceof Pawn) {
            Pawn newPawn = new Pawn(this.posY,this.posX, this.isWhite, newBoard);
            if (((Pawn) this).hasMoved) {
                newPawn.setHasMovedToTrue();
            }
            return newPawn;
        } else if (this instanceof King) {
            return new King(this.posY,this.posX, this.isWhite, newBoard);
        } else if (this instanceof Knight) {
            return new Knight(this.posY,this.posX, this.isWhite, newBoard);
        } else if (this instanceof Queen) {
            return new Queen(this.posY,this.posX, this.isWhite, newBoard);
        } else {
            return new Bishop(this.posY,this.posX, this.isWhite, newBoard);
        }
    }

    //REQUIRES: exactly 1 King of same color is in copyOfBoard
    //EFFECTS: gets position of the same colored king
    private int[] getPosOfKing(Piece[][] copyOfBoard) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (copyOfBoard[i][j] != null && copyOfBoard[i][j] instanceof King
                        && copyOfBoard[i][j].getIsWhite() == getIsWhite()) {
                    y = i;
                    x = j;
                    break;
                }
            }
        }
        return new int[] {y,x};
    }

    //EFFECTS: shows all valid moves for this piece
    public List<int[]> showValidMoves() {
        List<int[]> moves = new ArrayList<>();
        moves = showValidMovesWithoutCheck();
        removeCheckMoves(moves);
        return moves;
    }

    //EFFECTS: shows all valid moves for this piece without checking if moves will put yourself in check
    public abstract List<int[]> showValidMovesWithoutCheck();

    //todo add a toString method for printing taken pieces


    //EFFECTS: returns a string describing the type of piece
    @Override
    public String toString() {
        if (this instanceof Pawn) {
            return "Pawn";
        } else if (this instanceof Rook) {
            return "Rook";
        } else if (this instanceof Bishop) {
            return "Bishop";
        } else if (this instanceof Knight) {
            return "Knight";
        } else if (this instanceof King) {
            return "King";
        } else {
            return "Queen";
        }
    }

    //EFFECTS: returns the JSONObject form of a piece
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (this instanceof Pawn) {
            json.put("type","Pawn");
        } else if (this instanceof Rook) {
            json.put("type","Rook");
        } else if (this instanceof Bishop) {
            json.put("type","Bishop");
        } else if (this instanceof Knight) {
            json.put("type","Knight");
        } else if (this instanceof King) {
            json.put("type","King");
        } else {
            json.put("type","Queen");
        }
        json.put("y",posY);
        json.put("x",posX);
        json.put("isWhite",isWhite);
        return json;
    }

}