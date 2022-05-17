package ui;

import javax.swing.*;
import java.awt.*;

//JPanel that when painted, displays the background of a chess board
public class BoardBackground extends JPanel {
    private static final Color BLACK_SPACE_COLOR = new Color(118, 150, 86);
    private static final Color WHITE_SPACE_COLOR = new Color(238, 238, 210);
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    //EFFECTS: constructs a BoardBackground
    public BoardBackground() {
        setOpaque(true);
        setBackground(BLACK_SPACE_COLOR);
        setBounds(0,0, WIDTH, HEIGHT);
    }

    //EFFECTS: paints the BoardBackground and displays a chess board background
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareHeight = WIDTH / 8;
        g.setColor(WHITE_SPACE_COLOR);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i % 2) % 2 == 0) {
                    g.fillRect(j * squareHeight, i * squareHeight, squareHeight, squareHeight);
                }
            }
        }
    }
}
