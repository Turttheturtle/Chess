package ui;

import model.Piece;

import javax.swing.*;
import java.awt.*;

//JPanel that has specific methods of drawing a piece to a location
public abstract class PieceDrawer extends JPanel {
    public static final ImageIcon whitePawn = new ImageIcon("./data/pieces/whitePawn.png");
    public static final ImageIcon whiteRook = new ImageIcon("./data/pieces/whiteRook.png");
    public static final ImageIcon whiteKnight = new ImageIcon("./data/pieces/whiteKnight.png");
    public static final ImageIcon whiteBishop = new ImageIcon("./data/pieces/whiteBishop.png");
    public static final ImageIcon whiteKing = new ImageIcon("./data/pieces/whiteKing.png");
    public static final ImageIcon whiteQueen = new ImageIcon("./data/pieces/whiteQueen.png");
    public static final ImageIcon blackPawn = new ImageIcon("./data/pieces/blackPawn.png");
    public static final ImageIcon blackRook = new ImageIcon("./data/pieces/blackRook.png");
    public static final ImageIcon blackKnight = new ImageIcon("./data/pieces/blackKnight.png");
    public static final ImageIcon blackBishop = new ImageIcon("./data/pieces/blackBishop.png");
    public static final ImageIcon blackKing = new ImageIcon("./data/pieces/blackKing.png");
    public static final ImageIcon blackQueen = new ImageIcon("./data/pieces/blackQueen.png");

    //REQUIRES: x and y have to be inside this components bounds and all ImageIcons have to be properly initialized
    //EFFECTS: draws a piece centered at x, y
    protected void drawPiece(Graphics g, Piece piece, int x, int y) {
        if (piece != null) {
            if (piece.getIsWhite()) {
                drawPieceWhite(g, piece, x, y);
            } else {
                drawPieceBlack(g, piece, x, y);
            }
        }
    }

    //REQUIRES: x and y have to be inside this components bounds and all ImageIcons have to be properly initialized
    //EFFECTS: draws a black piece centered at x, y
    public void drawPieceBlack(Graphics g, Piece piece, int x, int y) {
        switch (piece.toString()) {
            case "King":
                blackKing.paintIcon(this, g,  x  - blackKing.getIconWidth() / 2, y - blackKing.getIconHeight() / 2);
                break;
            case "Queen":
                blackQueen.paintIcon(this, g,  x  - blackQueen.getIconWidth() / 2, y - blackQueen.getIconHeight() / 2);
                break;
            case "Pawn":
                blackPawn.paintIcon(this, g,  x  - blackPawn.getIconWidth() / 2, y - blackPawn.getIconHeight() / 2);
                break;
            case "Rook":
                blackRook.paintIcon(this, g,  x  - blackRook.getIconWidth() / 2, y - blackRook.getIconHeight() / 2);
                break;
            case "Knight":
                blackKnight.paintIcon(this, g,  x  - blackKnight.getIconWidth() / 2,
                        y - blackKnight.getIconHeight() / 2);
                break;
            case "Bishop":
                blackBishop.paintIcon(this, g,  x  - blackBishop.getIconWidth() / 2,
                        y - blackBishop.getIconHeight() / 2);
        }
    }

    //REQUIRES: x and y have to be inside this components bounds and all ImageIcons have to be properly initialized
    //EFFECTS: draws a white piece centered at x, y
    public void drawPieceWhite(Graphics g, Piece piece, int x, int y) {
        switch (piece.toString()) {
            case "King":
                whiteKing.paintIcon(this, g,  x  - whiteKing.getIconWidth() / 2, y - whiteKing.getIconHeight() / 2);
                break;
            case "Queen":
                whiteQueen.paintIcon(this, g,  x  - whiteQueen.getIconWidth() / 2, y - whiteQueen.getIconHeight() / 2);
                break;
            case "Pawn":
                whitePawn.paintIcon(this, g,  x  - whitePawn.getIconWidth() / 2, y - whitePawn.getIconHeight() / 2);
                break;
            case "Rook":
                whiteRook.paintIcon(this, g,  x  - whiteRook.getIconWidth() / 2, y - whiteRook.getIconHeight() / 2);
                break;
            case "Knight":
                whiteKnight.paintIcon(this, g,  x  - whiteKnight.getIconWidth() / 2,
                        y - whiteKnight.getIconHeight() / 2);
                break;
            case "Bishop":
                whiteBishop.paintIcon(this, g,  x  - whiteBishop.getIconWidth() / 2,
                        y - whiteBishop.getIconHeight() / 2);
        }
    }
}
