package ui;

import model.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//Jpanel that displays the side panel of the application
public class SidePanel extends PieceDrawer {
    private final int pieceSpacingX = 60;
    private final int pieceOffsetX = 60;
    private final int pieceOffsetY = 60;
    private final int pieceSpacingY = 75;
    private final int imageWidth = 45;
    private final int imageHeight = 45;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;
    private final Color textColor = Color.cyan;
    private final GameUI parent;

    //EFFECTS: constructs the side panel with its parent set to given GameUi
    public SidePanel(GameUI parent) {
        super();
        this.parent = parent;
        setLayout(null);
        setBackground(GameUI.BACKGROUND);
        setPreferredSize(new Dimension(300, 800));
        JLabel whitesPieces = new JLabel("white's pieces");
        whitesPieces.setBounds(100,410, 100, 15);
        whitesPieces.setForeground(textColor);
        add(whitesPieces);
        JLabel blacksPieces = new JLabel("black's pieces");
        blacksPieces.setBounds(100,10, 100, 15);
        blacksPieces.setForeground(textColor);
        add(blacksPieces);
        addActionListener();
    }

    //MODIFIES: this
    //EFFECTS: adds the mouse listener to this
    public void addActionListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectPiece(e.getX(),e.getY());
                repaint();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: if the given x and y are at a position where a white taken piece is displayed at then selects the piece
    public void selectPiece(int x, int y) {
        int row = 0;
        java.util.List<Piece> takenPieces = parent.game.getWhitePlayer().getTakenPieces();
        for (int i = 0; i < takenPieces.size(); i++) {
            int piecePosX = (i % 4) * pieceSpacingX + pieceOffsetX - (imageWidth / 2);
            int piecePosY = row * pieceSpacingY + pieceOffsetY + 400 - (imageHeight / 2);
            if ((x > piecePosX) && (x < (piecePosX + imageWidth))
                    && (y > piecePosY) && (y < (piecePosY + imageHeight))) {
                selectedPiece = takenPieces.get(i);
                selectedPieceX = piecePosX;
                selectedPieceY = (int)(piecePosY + (1.5 * imageHeight));
            }
            if (((i + 1) % 4) == 0) {
                row++;
            }
        }
        selectPieceBlack(x,y);
    }

    //MODIFIES: this
    //EFFECTS: if the given x and y are at a position where a black taken piece is displayed at then selects the piece
    private void selectPieceBlack(int x, int y) {
        int row = 0;
        java.util.List<Piece> takenPieces = parent.game.getBlackPlayer().getTakenPieces();
        for (int i = 0; i < takenPieces.size(); i++) {
            int piecePosX = (i % 4) * pieceSpacingX + pieceOffsetX - (imageWidth / 2);
            int piecePosY = row * pieceSpacingY + pieceOffsetY - (imageHeight / 2);
            if ((x > piecePosX) && (x < (piecePosX + imageWidth))
                    && (y > piecePosY) && (y < (piecePosY + imageHeight))) {
                selectedPiece = takenPieces.get(i);
                selectedPieceX = piecePosX;
                selectedPieceY = (int)(piecePosY + (1.5 * imageHeight));
            }
            if (((i + 1) % 4) == 0) {
                row++;
            }
        }
    }

    //EFFECTS: overrides paintComponent and draws the takenPieces as well as displays information if needed
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWhiteTakenPieces(g);
        paintBlackTakenPieces(g);
        paintInfoOfSelectedPiece(g);
    }

    //EFFECTS: paints white's taken pieces
    public void paintWhiteTakenPieces(Graphics g) {
        int row = 0;
        java.util.List<Piece> takenPieces = parent.game.getWhitePlayer().getTakenPieces();
        for (int i = 0; i < takenPieces.size(); i++) {
            drawPiece(g, takenPieces.get(i), (i % 4) * pieceSpacingX + pieceOffsetX,
                    row * pieceSpacingY + pieceOffsetY + 400);
            if (((i + 1) % 4) == 0) {
                row++;
            }
        }
    }

    //EFFECTS: paints black's taken pieces
    public void paintBlackTakenPieces(Graphics g) {
        int row = 0;
        List<Piece> takenPieces = parent.game.getBlackPlayer().getTakenPieces();
        for (int i = 0; i < takenPieces.size(); i++) {
            drawPiece(g, takenPieces.get(i), (i % 4) * pieceSpacingX + pieceOffsetX,
                    row * pieceSpacingY + pieceOffsetY);
            if (((i + 1) % 4) == 0) {
                row++;
            }
        }
    }

    //EFFECTS: paints the info of selected piece
    public void paintInfoOfSelectedPiece(Graphics g) {
        if (selectedPiece != null) {
            g.setColor(textColor);
            g.drawString("X : " + selectedPiece.getPosX() + " Y : "
                    + selectedPiece.getPosY(), selectedPieceX, selectedPieceY);
        }
    }
}