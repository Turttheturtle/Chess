package model;

import exceptions.IllegalMoveException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


//models Player, has access to the ches board with the board field, thier color which is represented by isWhite, and
//taken pieces is a list of Pieces that you have taken from the other player
public class Player implements Writable {

    Piece[][] board;
    protected boolean isWhite;
    private List<Piece> takenPieces;
    private boolean isHuman;

    //EFFECTS: creates a player with given parameters
    public Player(boolean isWhite, boolean isHuman, List<Piece> pieces, Piece[][] board) {
        this.board = board;
        this.isWhite = isWhite;
        this.isHuman = isHuman;
        this.takenPieces = pieces;
    }

    //Effects sets up the Player according to if it is black or white
    Player(boolean isWhite, Piece[][] board, boolean isHuman) {
        takenPieces = new ArrayList<>();
        this.board = board;
        this.isWhite = isWhite;
        if (isWhite) {
            this.setUpWhite();
        } else {
            this.setUpBlack();
        }
        this.isHuman = isHuman;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }


    public boolean getIsHuman() {
        return isHuman;
    }

/*
    private Piece[][] copyBoard() {
        Piece[][] newBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    newBoard[i][j] = board[i][j].copyPiece(newBoard);
                }
            }
        }
        return newBoard;
    }

 */


    //MODIFIES: this
    //EFFECTS: moves a piece according to what getMove() returns propagates exception to caller
    public void movePiece() throws IllegalMoveException { //ToDo should handle exception , move is always valid
        //AI ai = new AI(copyBoard(),false);
        //int[] move = ai.generateBestMove();
        int[] move = getMove();
        movePiece(move[0],move[1],move[2],move[3]);
    }

    //MODIFIES: this
    //EFFECTS: moves a piece from the position oldY,oldX to newY, newX, throws IllegalMoveException if you are allowed
    // to make given move
    public void movePiece(int oldY, int oldX, int newY, int newX) throws IllegalMoveException {
        Piece movingPiece = board[oldY][oldX];
        if ((movingPiece.checkValidMove(newY,newX)) && (movingPiece.getIsWhite() == isWhite)) {
            movingPiece.movePiece(newY,newX);
            logMoveEvent(oldY,oldX,newY,newX);
            board[oldY][oldX] = null;
            if (board[newY][newX] != null) {
                Event event;
                if (isWhite) {
                    event = new Event("added " + board[newY][newX] + " to white's " + "taken pieces");
                } else {
                    event = new Event("added " + board[newY][newX] + " to black's " + "taken pieces");
                }
                EventLog.getInstance().logEvent(event);
                System.out.println(event + "\n");
                takenPieces.add(board[newY][newX]);
            }
            board[newY][newX] = movingPiece;
        } else {
            throw new IllegalMoveException();
        }
    }

    //EFFECTS: logs a move event
    public void logMoveEvent(int oldY, int oldX, int newY, int newX) {
        Event clear;
        if (isWhite) {
            clear = new Event("white has moved a " + board[oldY][oldX] + " to " + newY + "," + newX);
        } else {
            clear = new Event("black has moved a " + board[oldY][oldX] + " to " + newY + "," + newX);
        }
        EventLog.getInstance().logEvent(clear);
        System.out.println(clear + "\n");
    }

    //EFFECTS: for now only returns a random valid move, will implement proper ai later
    private int[] getMove() {
        List<int[]> allMoves = getAllMoves();
        return allMoves.get((int)(Math.random() * (allMoves.size())));
    }



    //EFFECTS: returns the list of all possible moves that the player can make
    public List<int[]> getAllMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getIsWhite() == isWhite) {
                    List<int[]> tempMoves = board[i][j].showValidMoves();
                    for (int[] tempMove : tempMoves) {
                        moves.add(new int[] {i,j,tempMove[0], tempMove[1]});
                    }
                }
            }
        }
        return moves;
    }


    // Modifes: this
    // Effects: adds starting white pieces to the board
    public void setUpWhite() {
        board[6][0] = new Pawn(6, 0, true, board);
        board[6][1] = new Pawn(6, 1, true, board);
        board[6][2] = new Pawn(6, 2, true, board);
        board[6][3] = new Pawn(6, 3, true, board);
        board[6][4] = new Pawn(6, 4, true, board);
        board[6][5] = new Pawn(6, 5, true, board);
        board[6][6] = new Pawn(6, 6, true, board);
        board[6][7] = new Pawn(6, 7, true, board);
        board[7][0] = new Rook(7, 0, true, board);
        board[7][1] = new Knight(7, 1, true, board);
        board[7][2] = new Bishop(7, 2, true, board);
        board[7][3] = new Queen(7, 3, true, board);
        board[7][4] = new King(7, 4, true, board);
        board[7][5] = new Bishop(7, 5, true, board);
        board[7][6] = new Knight(7, 6, true, board);
        board[7][7] = new Rook(7, 7, true, board);
    }

    // Modifes: this
    // Effects: adds starting black pieces to the board
    public void setUpBlack() {
        board[1][0] = new Pawn(1, 0, false, board);
        board[1][1] = new Pawn(1, 1, false, board);
        board[1][2] = new Pawn(1, 2, false, board);
        board[1][3] = new Pawn(1, 3, false, board);
        board[1][4] = new Pawn(1, 4, false, board);
        board[1][5] = new Pawn(1, 5, false, board);
        board[1][6] = new Pawn(1, 6, false, board);
        board[1][7] = new Pawn(1, 7, false, board);
        board[0][0] = new Rook(0, 0, false, board);
        board[0][1] = new Knight(0, 1, false, board);
        board[0][2] = new Bishop(0, 2, false, board);
        board[0][3] = new Queen(0, 3, false, board);
        board[0][4] = new King(0, 4, false, board);
        board[0][5] = new Bishop(0, 5, false, board);
        board[0][6] = new Knight(0, 6, false, board);
        board[0][7] = new Rook(0, 7, false, board);
    }

    public List<Piece> getTakenPieces() {
        return takenPieces;
    }

    //EFFECTS: returns a JSONObject representing this players information
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isHuman", isHuman);
        jsonObject.put("isWhite",isWhite);
        JSONArray pieces = new JSONArray();
        for (Piece piece : takenPieces) {
            pieces.put(piece.toJson());
        }
        jsonObject.put("pieces",pieces);
        return jsonObject;
    }
}
