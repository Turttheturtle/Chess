package ui;

import exceptions.CheckmateException;
import exceptions.IllegalMoveException;
import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;

//JFrame that displays the Chess Game application
public class GameUI extends JFrame {
    private static final String FILE_PATH = "./data/data.json";
    protected static final Color BACKGROUND = new Color(43,43,43);
    protected ChessGame game;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    //EFFECTS: constructs the GameUI
    public GameUI() {
        super("Chess");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\n");
                System.out.println("Events logged since application start:");
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event + "\n");
                }
                System.exit(0);
            }
        });
        jsonReader = new JsonReader(FILE_PATH);
        jsonWriter = new JsonWriter(FILE_PATH);
        startUp();
        //setUpGame("white vs black");
    }

    //MODIFIES: this
    //EFFECTS: displays the starting menu
    private void startUp() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));
        this.add(layeredPane);
        //addBlackVSBotButton(layeredPane);
        addLoadButton(layeredPane);
        addWhiteVSBotButton(layeredPane);
        addWhiteVSBlackButton(layeredPane);
        layeredPane.add(new BoardBackground());
        this.pack();
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds the white vs black button to given JLayeredPane
    private void addWhiteVSBlackButton(JLayeredPane layeredPane) {
        JButton whiteVsBlack = new JButton("White Vs Black");
        whiteVsBlack.setBounds(400,400,200,100);
        whiteVsBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(layeredPane);
                revalidate();
                setUpGame(new ChessGame("white vs black"));
            }
        });
        layeredPane.add(whiteVsBlack);
    }

    //MODIFIES: this
    //EFFECTS: adds the white vs bot button to given JLayeredPane
    private void addWhiteVSBotButton(JLayeredPane layeredPane) {
        JButton whiteVsBot = new JButton("White vs Bot");
        whiteVsBot.setBounds(400,500,200,100);
        whiteVsBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(layeredPane);
                revalidate();
                setUpGame(new ChessGame("white vs bot"));
            }
        });
        layeredPane.add(whiteVsBot);
    }

    //MODIFIES: this
    //EFFECTS: adds the black vs bot button to given JLayeredPane
    private void addBlackVSBotButton(JLayeredPane layeredPane) {
        JButton blackVsBot = new JButton("Black vs Bot");
        blackVsBot.setBounds(400,600,200,100);
        blackVsBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(layeredPane);
                revalidate();
                setUpGame(new ChessGame("black vs bot"));
            }
        });
        layeredPane.add(blackVsBot);
    }

    //MODIFIES: this
    //EFFECTS: adds the load button to the given JLayeredPane
    private void addLoadButton(JLayeredPane layeredPane) {
        JButton blackVsBot = new JButton("Load from save");
        blackVsBot.setBounds(400,300,200,100);
        blackVsBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(layeredPane);
                revalidate();
                try {
                    setUpGame(jsonReader.read());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        layeredPane.add(blackVsBot);
    }

    //MODIFIES: this
    //EFFECTS: starts the main game menu with given ChessGame
    private void setUpGame(ChessGame inputGame) {
        game = inputGame;
        BoardPanel bp = new BoardPanel(game);
        bp.setOpaque(true);
        bp.setBackground(BACKGROUND);
        this.add(bp,BorderLayout.WEST);
        bp.setParent(this);
        setUpMouseListenerForBoardPanel(bp);
        this.add(new SidePanel(this), BorderLayout.CENTER);
        this.add(new MenuPanel(), BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds mouse listener for the game board
    private void setUpMouseListenerForBoardPanel(BoardPanel bp) {
        bp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                bp.selectPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bp.releasePiece();
            }
        });
        bp.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                bp.updateXY(e.getX(), e.getY());
            }
        });
    }

    //EFFECTS: saves the state of the game
    public void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads game
    public void loadGame() {
        try {
            game = jsonReader.read();
            for (int i = 0; i < rootPane.getContentPane().getComponentCount(); i++) {
                if (rootPane.getContentPane().getComponent(i) instanceof BoardPanel) {
                    ((BoardPanel) rootPane.getContentPane().getComponent(i)).replaceGame(game);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        repaint();
    }

    //MODIFIES: this
    //EFFECTS: moves the piece according to given input
    public void handleMove(int[] move) {
        try {
            game.handleTurn(move[0], move[1], move[2], move[3]);
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        } catch (CheckmateException e) {
            endGame();
        }
        repaint();
    }

    //todo implement into ui
    //EFFECTS: ends the game
    public void endGame() {
        if (game.getWhiteToMove()) {
            System.out.println("Checkmate Black wins");
        } else {
            System.out.println("Checkmate White wins");
        }
    }



    //JPanel that models the menu of the application
    private class MenuPanel extends JPanel {
        //EFFECTS: constructs the MenuPanel
        public MenuPanel() {
            super();
            setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
            setPreferredSize(new Dimension(1100, 25));
            setBackground(new Color(60,63,65));
            addSaveButton();
            addLoadButton();
        }

        //MODIFIES: this
        //EFFECTS: adds the save button to this
        private void addSaveButton() {
            JButton save = new JButton("Save");
            save.setForeground(Color.white);
            save.setBackground(new Color(60,63,65));
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveGame();
                }
            });
            save.setBorderPainted(false);
            save.setFocusPainted(false);
            this.add(save);
        }

        //MODIFIES: this
        //EFFECTS: adds the load button to this
        private void addLoadButton() {
            JButton load = new JButton("Load");
            load.setForeground(Color.white);
            load.setBackground(new Color(60,63,65));
            load.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadGame();
                }
            });
            load.setBorderPainted(false);
            load.setFocusPainted(false);
            this.add(load);
        }
    }
}
