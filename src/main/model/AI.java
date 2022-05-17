package model;

import java.util.ArrayList;
import java.util.List;

public class AI {

    /*
    private Piece[][] board;
    private boolean whiteToMove;
    private List<Piece> whitePiecesInPlay;
    private List<Piece> blackPiecesInPlay;
    //todo add field for depth / difficulty

    public AI(Piece[][] board, boolean whiteToMove) {
        this.board = board; // please make new copy of board
        this.whiteToMove = whiteToMove;
        initPiecesInPlay();
    }

    private void initPiecesInPlay() {
        whitePiecesInPlay = new ArrayList<>();
        blackPiecesInPlay = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].getIsWhite()) {
                        whitePiecesInPlay.add(board[i][j]);
                    } else {
                        blackPiecesInPlay.add(board[i][j]);
                    }
                }
            }
        }
    }

    public int[] generateBestMove() {
        return miniMax(new ArrayList<>(), new ArrayList<>(), 0, whiteToMove);
    }

    public int[] miniMaxStart() {
        return miniMax(new ArrayList<>(), new ArrayList<>(), 0, whiteToMove);
    }

    //todo alpha beta pruning
    private int[] miniMax(List<int[]> path, List<Piece> takenPieces, int depth, boolean maximizing) {
        if (depth == 4) { //max is 6 anymore than that will take forever to load
            undoMove(path.get(path.size() - 1), takenPieces);
            return new int[] {path.get(0)[0],path.get(0)[1],path.get(0)[2],path.get(0)[3],evaluatePieces(takenPieces)};
        }
        if (maximizing) {
            List<int[]> allMoves = generateAllMoves(whitePiecesInPlay);
            int[] maxValue = {0,0,0,0,Integer.MIN_VALUE};
            for (int[] move : allMoves) {
                List<int[]> newPath = copyPath(path);
                List<Piece> newTakenPieces = copyTakenPieces(takenPieces);
                makeMove(move, newTakenPieces);
                newPath.add(move);
                maxValue = maximum(maxValue, miniMax(newPath,newTakenPieces,depth + 1, false));
            }
            if (path.size() != 0) {
                undoMove(path.get(path.size() - 1), takenPieces); // something  wong
            }
            return maxValue;
        } else {
            List<int[]> allMoves = generateAllMoves(blackPiecesInPlay);
            int[] minValue = {0,0,0,0,Integer.MAX_VALUE};
            for (int[] move : allMoves) {
                List<int[]> newPath = copyPath(path);
                List<Piece> newTakenPieces = copyTakenPieces(takenPieces);
                makeMove(move, newTakenPieces);
                newPath.add(move);
                minValue = minimum(minValue, miniMax(newPath,newTakenPieces,depth + 1, true));
            }
            if (path.size() != 0) {
                undoMove(path.get(path.size() - 1), takenPieces);
            }
            return minValue;
        }
    }

    private List<int[]> copyPath(List<int[]> path) {
        List<int[]> newPath = new ArrayList<>();
        for (int[] move : path) {
            newPath.add(move);
        }
        return newPath;
    }

    private List<Piece> copyTakenPieces(List<Piece> takenPieces) {
        List<Piece> newTakenPieces = new ArrayList<>();
        for (Piece piece : takenPieces) {
            newTakenPieces.add(piece);
        }
        return newTakenPieces;
    }

    //makes move and adds piece to taken pieces if takes a piece
    private void makeMove(int[] move, List<Piece> takenPieces) { //
        if (move[4] == 1) { //maybe replace with move[4]
            takenPieces.add(board[move[2]][move[3]]);
            for (int i = 0; i < blackPiecesInPlay.size(); i++) {
                if (blackPiecesInPlay.get(i) == board[move[2]][move[3]]) {
                    blackPiecesInPlay.remove(i);
                }
            }
            for (int i = 0; i < whitePiecesInPlay.size(); i++) {
                if (whitePiecesInPlay.get(i) == board[move[2]][move[3]]) {
                    whitePiecesInPlay.remove(i);
                }
            }
        }
        board[move[2]][move[3]] = board[move[0]][move[1]];
        board[move[0]][move[1]] = null;
    }


    private void undoMove(int[] move, List<Piece> takenPieces) { //undo move
        if (move[4] == 0) {
            board[move[0]][move[1]] = board[move[2]][move[3]];
            board[move[2]][move[3]] = null;
        } else {
            board[move[0]][move[1]] = board[move[2]][move[3]];
            board[move[2]][move[3]] = takenPieces.get(takenPieces.size() - 1);
            if (board[move[2]][move[3]].getIsWhite()) {
                whitePiecesInPlay.add(board[move[2]][move[3]]);
            } else {
                blackPiecesInPlay.add(board[move[2]][move[3]]);
            }
        }
    }

    private List<int[]> generateAllMoves(List<Piece> listOfPieces) {
        ArrayList<int[]> allMoves = new ArrayList<>();
        for (Piece piece : listOfPieces) {
            List<int[]> tempMoves = piece.showValidMovesWithoutCheck(); // should do check
            int oldX = piece.getPosX();
            int oldY = piece.getPosY();
            for (int[] newMove : tempMoves) {
                if (board[newMove[0]][newMove[1]] != null) {
                    allMoves.add(new int[]{oldY, oldX, newMove[0], newMove[1], 1});
                } else {
                    allMoves.add(new int[]{oldY, oldX, newMove[0], newMove[1], 0});
                }
            }
        }
        return allMoves;
    }

    private int evaluatePieces(List<Piece> takenPieces) {
        //can still evaluate stuff if you dont have a king
        int value = 0;
        if (takenPieces.size() == 0) {
            return 0;
        }
        for (Piece piece : takenPieces) {
            int posOneIfWhite;
            if (piece.getIsWhite()) {
                posOneIfWhite = -1;
            } else {
                posOneIfWhite = 1;
            }
            if (piece instanceof King) {
                value += posOneIfWhite * 200;
            } else if (piece instanceof Queen) {
                value += posOneIfWhite * 9;
            } else if (piece instanceof Rook) {
                value += posOneIfWhite * 5;
            } else if (piece instanceof Bishop) {
                value += posOneIfWhite * 3;
            } else if (piece instanceof Knight) {
                value += posOneIfWhite * 3;
            } else if (piece instanceof Pawn) {
                value += posOneIfWhite * 1;
            }
        }
        return value;
    }

    private int[] maximum(int[] arr1, int[] arr2) {
        if (arr1[4] > arr2[4]) {
            return arr1;
        } else {
            return arr2;
        }
    }

    private int[] minimum(int[] arr1, int[] arr2) {
        if (arr1[4] < arr2[4]) {
            return arr1;
        } else {
            return arr2;
        }
    }


     */
}
