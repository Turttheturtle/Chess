package ui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


import model.*;

//JPanel that displays the chessBoard
public class BoardPanel extends PieceDrawer {

    private static final Color VALID_SPACE_COLOR = new Color(176, 176, 239);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private JLayeredPane layeredPane;
    private ChessGame game;
    private Piece selectedPiece;
    private ImageIcon selectedPieceImage;
    private ArrayList<int[]> validPositions;
    private int mouseX;
    private int mouseY;
    private GameUI parent;


    //EFFECTS: constructs a BoardPanel and sets game to given ChessGame
    public BoardPanel(ChessGame game) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        layeredPane.add(new BoardBackground());
        setOpaque(false);
        this.add(layeredPane);
        this.game = game;
        validPositions = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: replaces game with given ChessGame
    public void replaceGame(ChessGame game) {
        this.game = game;
        selectedPiece = null;
        validPositions.clear();
    }

    //MODIFIES: this
    //EFFECTS: sets parent
    public void setParent(GameUI parent) {
        this.parent = parent;
    }

    //EFFECTS: draws the Chess Board
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawValidPositions(g);
        drawPieces(g);
        if (selectedPiece != null) {
            drawSelected(g);
        }
    }

    //EFFECTS: helper function for paint, draws all the pieces from the board contained in game field
    private void drawPieces(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece nextPiece = game.getBoard()[i][j];
                if (nextPiece != null) {
                    drawPiece(g, nextPiece, nextPiece.getPosX() * HEIGHT / 8 + HEIGHT / 16,
                            nextPiece.getPosY() * HEIGHT / 8 + HEIGHT / 16);
                }
            }
        }
    }

    //REQUIRES: mouseX and mouseY are within component's bounds
    //EFFECTS: draws the selected piece centered at mouseX, mouseY
    public void drawSelected(Graphics g) {
        drawPiece(g, selectedPiece, mouseX, mouseY);
    }

    //REQUIRES: validPositions only contains arrays in which their values are bounded by 0 an 7
    //EFFECTS: highlights the selected pieces available moves
    public void drawValidPositions(Graphics g) {
        int boxHeight = WIDTH / 8;
        g.setColor(VALID_SPACE_COLOR);
        for (int[] pos : validPositions) {
            g.fillRect(pos[1] * boxHeight, pos[0] * boxHeight, boxHeight, boxHeight);
        }
    }

    //MODIFIES：this
    //EFFECTS: selects piece at x,y
    public void selectPiece(int x, int y) {
        Piece tempPiece = game.getBoard()[(int)(y / (double)(WIDTH / 8))][(int)(x / (double)(WIDTH / 8))];
        if (tempPiece != null && (tempPiece.getIsWhite() == game.getWhiteToMove())) {
            selectedPiece = tempPiece;
            mouseX = x;
            mouseY = y;
            validPositions = (ArrayList<int[]>)selectedPiece.showValidMoves();
            repaint();
        }
    }

    //MODIFES: this
    //EFFECTS: if you have a piece selected and your mouse position is hovered over one of its valid move positions then
    //tells the game to move the piece otherwise unselects piece
    public void releasePiece() {
        for (int[] position : validPositions) {
            if ((int)(mouseY / (double)(WIDTH / 8)) == position[0]
                    && (int)(mouseX / (double)(WIDTH / 8)) == position[1]) {
                parent.handleMove(new int[] {selectedPiece.getPosY(),selectedPiece.getPosX(),position[0],
                        position[1]});
            }
        }
        selectedPiece = null;
        selectedPieceImage = null;
        validPositions.clear();
        repaint();
    }

    //MODFIES: this;
    //EFFECTS: sets mouseX and mouseY to given x and y then repaints the component
    public void updateXY(int x, int y) {
        mouseX = x;
        mouseY = y;
        repaint();
    }
}
