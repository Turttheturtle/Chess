package model;

import exceptions.CheckmateException;
import exceptions.IllegalMoveException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//models the entire game where two players are represented by white and black players, the board is modeled by board
//and the turn is modeled by whiteToMove
public class ChessGame implements Writable {
    private Player whitePlayer;
    private Player blackPlayer;
    private Piece[][] board;
    private boolean whiteToMove;

    //REQUIRES: string is "white vs bot" "black vs bot" "bot vs bot" or "white vs black"
    //EFFECTS: sets up the game depending on what you want to play against
    public ChessGame(String typeOfGame) {
        setUp(typeOfGame);
    }

    //EFFECTS: constructs a chess game with an empty board and no players
    public ChessGame() {
        board = new Piece[8][8];
    }

    public void setWhitePlayer(Player p) {
        Event clear = new Event("white's taken pieces has been cleared, and its pieces read from save");
        EventLog.getInstance().logEvent(clear);
        System.out.println(clear + "\n");
        whitePlayer = p;
        for (Piece piece : p.getTakenPieces()) {
            Event event = new Event("added " + piece + " to white's taken pieces");
            EventLog.getInstance().logEvent(event);
            System.out.println(event + "\n");
        }
    }

    public void setBlackPlayer(Player p) {
        Event clear = new Event("black's taken pieces has been cleared, and its pieces read from save");
        EventLog.getInstance().logEvent(clear);
        System.out.println(clear + "\n");
        blackPlayer = p;
        for (Piece piece : p.getTakenPieces()) {
            Event event = new Event("added " + piece + " to black's taken pieces");
            EventLog.getInstance().logEvent(event);
            System.out.println(event + "\n");
        }
    }

    public void setBoard(Piece[][] b) {
        board = b;
    }

    public void setWhiteToMove(boolean w) {
        whiteToMove = w;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean getWhiteToMove() {
        return whiteToMove;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    //REQUIRES: string is "white vs bot" "black vs bot" "bot vs bot" or "white vs black"
    // (only does "white vs bot" for now)
    //MODIFIES: this
    //EFFECTS: sets up the game depending on what you want to play against
    private void setUp(String typeOfGame) {
        board = new Piece[8][8];
        whiteToMove = true;
        if (typeOfGame.equals("white vs bot")) {
            this.whitePlayer = new Player(true, board, true);
            this.blackPlayer = new Player(false, board, false);
        } else if (typeOfGame.equals("white vs black")) {
            this.whitePlayer = new Player(true, board,true);
            this.blackPlayer = new Player(false, board,true);
        }  else if (typeOfGame.equals("black vs bot")) {
            this.whitePlayer = new Player(true, board,false);
            this.blackPlayer = new Player(false, board,true);

        } /* else if (typeOfGame.equals("bot vs bot")) {
            this.whitePlayer = new Bot(true, board);
            this.blackPlayer = new Bot(false, board);
        }
        */
    }

    //todo
    private void botMove() {

    }

    //MODIFIES: this
    //EFFECTS: Moves piece on the array with indexes oldY oldX to newY newX, throws CheckmateException if there are
    //no moves to make and IllegalMoveException if it is not a valid move
    public void handleTurn(int oldY, int oldX, int newY, int newX) throws IllegalMoveException, CheckmateException {
        if (!(board[oldY][oldX] instanceof Piece)) {
            throw new IllegalMoveException();
        }

        if (whiteToMove) {
            handleTurnWhite(oldY,oldX,newY,newX);
        } else {
            handleTurnBlack(oldY,oldX,newY,newX);
        }
    }

    //MODIFIES: this
    //EFFECTS: helper function for handleTurn() when it is white's turn to move
    private void handleTurnWhite(int oldY, int oldX, int newY, int newX)
            throws IllegalMoveException, CheckmateException {
        //only true when the bot puts you in checkmate which is kind of impossible when it makes random moves
        // so can't really test this part until proper ai is made
        if (whitePlayer.getAllMoves().size() == 0) {
            throw new CheckmateException();
        }
        if (whitePlayer.getIsHuman()) {
            whitePlayer.movePiece(oldY,oldX,newY,newX);
            whiteToMove = false;
        }
        if (blackPlayer.getAllMoves().size() == 0) {
            throw new CheckmateException();
        }
        if (!blackPlayer.getIsHuman()) {
            blackPlayer.movePiece();
            whiteToMove = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: helper function for handleTurn() when it is black's turn to move
    private void handleTurnBlack(int oldY, int oldX, int newY, int newX)
            throws IllegalMoveException, CheckmateException {

        if (blackPlayer.getAllMoves().size() == 0) {
            throw new CheckmateException();
        }

        if (blackPlayer.getIsHuman()) {
            blackPlayer.movePiece(oldY,oldX,newY,newX);
            whiteToMove = true;
        }
        if (whitePlayer.getAllMoves().size() == 0) {
            throw new CheckmateException();
        }

        if (!whitePlayer.getIsHuman()) {
            whitePlayer.movePiece();
            whiteToMove = false;
        }

    }

    //modeled by the example
    //EFFECTS: returns a JsonObject that contains this objects information
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("whiteToMove", whiteToMove);
        jsonObject.put("pieces", piecesToJson());
        jsonObject.put("players", playersToJson());
        return jsonObject;
    }

    //EFFECTS: returns a JSONArray that contains the players information
    public JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(whitePlayer.toJson());
        jsonArray.put(blackPlayer.toJson());
        return jsonArray;
    }

    //EFFECTS: returns a JSONArray that contains the pieces information
    public JSONArray piecesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    jsonArray.put(board[i][j].toJson());
                }
            }
        }
        return jsonArray;
    }

}
