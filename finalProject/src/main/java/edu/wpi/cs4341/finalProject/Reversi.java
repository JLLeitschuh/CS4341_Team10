package edu.wpi.cs4341.finalProject;
/*
Reversi

Reversi (Othello) is a game based on a grid with eight rows and eight columns, played between you and the computer, by adding pieces with two sides: black and white.
At the beginning of the game there are 4 pieces in the grid, the player with the black pieces is the first one to place his piece on the board.
Each player must place a piece in a position that there exists at least one straight (horizontal, vertical, or diagonal) line between the new piece and another piece of the same color, with one or more contiguous opposite pieces between them.

Usage:  java Reversi

10-12-2006 version 0.1: initial release
26-12-2006 version 0.15: added support for applet
01-11-2007 version 0.16: minor improvement in level handling

Requirement: Java 1.5 or later

future features:
- undo
- save/load board on file, logging of moves
- autoplay
- sound

This software is released under the GNU GENERAL PUBLIC LICENSE, see attached file gpl.txt
*/

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.*;

class GPanel extends JPanel implements MouseListener {

    ReversiBoard board;
    int gameLevel;
    ImageIcon button_black, button_white;
    JLabel score_black, score_white;
    String gameTheme;
    Move hint = null;
    boolean inputEnabled, active;

    public GPanel(ReversiBoard board, JLabel score_black, JLabel score_white, String theme, int level) {
        super();
        this.board = board;
        this.score_black = score_black;
        this.score_white = score_white;
        gameLevel = level;
        setTheme(theme);
        addMouseListener(this);
        inputEnabled = true;
        active = true;
    }

    public void setTheme(String gameTheme) {
        hint = null;
        this.gameTheme = gameTheme;
        if (gameTheme.equals("Classic")) {
            button_black = new ImageIcon(Reversi.class.getResource("/button_black.jpg"));
            button_white = new ImageIcon(Reversi.class.getResource("/button_white.jpg"));
            setBackground(Color.green);
        } else if (gameTheme.equals("Electric")) {
            button_black = new ImageIcon(Reversi.class.getResource("/button_blu.jpg"));
            button_white = new ImageIcon(Reversi.class.getResource("/button_red.jpg"));
            setBackground(Color.white);
        } else {
            gameTheme = "Flat"; // default theme "Flat"
            setBackground(Color.green);
        }
        repaint();
    }

    public void setLevel(int level) {
        if ((level > 1) && (level < 7)) gameLevel = level;
    }

    public void drawPanel(Graphics g) {
//	    int currentWidth = getWidth();
//		int currentHeight = getHeight();
        for (int i = 1; i < 8; i++) {
            g.drawLine(i * Reversi.Square_L, 0, i * Reversi.Square_L, Reversi.Height);
        }
        g.drawLine(Reversi.Width, 0, Reversi.Width, Reversi.Height);
        for (int i = 1; i < 8; i++) {
            g.drawLine(0, i * Reversi.Square_L, Reversi.Width, i * Reversi.Square_L);
        }
        g.drawLine(0, Reversi.Height, Reversi.Width, Reversi.Height);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                switch (board.get(i, j)) {
                    case white:
                        if (gameTheme.equals("Flat")) {
                            g.setColor(Color.white);
                            g.fillOval(1 + i * Reversi.Square_L, 1 + j * Reversi.Square_L, Reversi.Square_L - 1, Reversi.Square_L - 1);
                        } else
                            g.drawImage(button_white.getImage(), 1 + i * Reversi.Square_L, 1 + j * Reversi.Square_L, this);
                        break;
                    case black:
                        if (gameTheme.equals("Flat")) {
                            g.setColor(Color.black);
                            g.fillOval(1 + i * Reversi.Square_L, 1 + j * Reversi.Square_L, Reversi.Square_L - 1, Reversi.Square_L - 1);
                        } else
                            g.drawImage(button_black.getImage(), 1 + i * Reversi.Square_L, 1 + j * Reversi.Square_L, this);
                        break;
                }
        if (hint != null) {
            g.setColor(Color.darkGray);
            g.drawOval(hint.i * Reversi.Square_L + 3, hint.j * Reversi.Square_L + 3, Reversi.Square_L - 6, Reversi.Square_L - 6);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPanel(g);
    }

    public Dimension getPreferredSize() {
        return new Dimension(Reversi.Width, Reversi.Height);
    }

    public void showWinner() {
        inputEnabled = false;
        active = false;
        if (board.counter[0] > board.counter[1]) {
            //JOptionPane.showMessageDialog(this, "You win!", "Reversi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("You win! : " + JOptionPane.INFORMATION_MESSAGE);
        }

        else if (board.counter[0] < board.counter[1]) {
            //JOptionPane.showMessageDialog(this, "I win!", "Reversi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("I win! : " + JOptionPane.INFORMATION_MESSAGE);
        }

        else {
            //JOptionPane.showMessageDialog(this, "Drawn!", "Reversi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Draw! : " + JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setHint(Move hint) {
        this.hint = hint;
    }

    public void clear() {
        board.clear();
        score_black.setText(Integer.toString(board.getCounter(TKind.black)));
        score_white.setText(Integer.toString(board.getCounter(TKind.white)));
        inputEnabled = true;
        active = true;
    }

    public void computerMove() {
        if (board.gameEnd()) {
            showWinner();
            return;
        }
        Move move = new Move();
        if (board.findMove(TKind.white, gameLevel, move)) {
            board.move(move, TKind.white);
            score_black.setText(Integer.toString(board.getCounter(TKind.black)));
            score_white.setText(Integer.toString(board.getCounter(TKind.white)));
            repaint();
            if (board.gameEnd()) showWinner();
            else if (!board.userCanMove(TKind.black)) {
                //JOptionPane.showMessageDialog(this, "You pass...", "Reversi", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("You Pass...! : " + JOptionPane.INFORMATION_MESSAGE);
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        computerMove();
                    }
                });
            }
        } else if (board.userCanMove(TKind.black)) {
            //JOptionPane.showMessageDialog(this, "I pass...", "Reversi", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("I Pass...! : " + JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            showWinner();
        }
    }

    public void mouseClicked(MouseEvent e) {
// generato quando il mouse viene premuto e subito rilasciato (click)

        if (inputEnabled) {
            hint = null;
            int i = e.getX() / Reversi.Square_L;
            int j = e.getY() / Reversi.Square_L;
            if ((i < 8) && (j < 8) && (board.get(i, j) == TKind.nil) && (board.move(new Move(i, j), TKind.black) != 0)) {
                score_black.setText(Integer.toString(board.getCounter(TKind.black)));
                score_white.setText(Integer.toString(board.getCounter(TKind.white)));
                repaint();
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        Cursor savedCursor = getCursor();
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        computerMove();
                        setCursor(savedCursor);
                    }
                });
            } else {
                //JOptionPane.showMessageDialog(this, "Illegal move", "Reversi", JOptionPane.ERROR_MESSAGE);
                System.out.println("Illegal move: " + JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void mouseEntered(MouseEvent e) {
// generato quando il mouse entra nella finestra
    }


    public void mouseExited(MouseEvent e) {
// generato quando il mouse esce dalla finestra
    }


    public void mousePressed(MouseEvent e) {
// generato nell'istante in cui il mouse viene premuto
    }


    public void mouseReleased(MouseEvent e) {
// generato quando il mouse viene rilasciato, anche a seguito di click
    }


};

public class Reversi extends JFrame implements ActionListener {

    JEditorPane editorPane;

    static final String WindowTitle = "Reversi";
    static final String ABOUTMSG = WindowTitle + "\n\n26-12-2006\njavalc6";

    static GPanel gpanel;
    static JMenuItem hint;
    static boolean helpActive = false;

    static final int Square_L = 33; // length in pixel of a square in the grid
    static final int Width = 8 * Square_L; // Width of the game board
    static final int Height = 8 * Square_L; // Width of the game board

    ReversiBoard board;
    static JLabel score_black, score_white;
    JMenu level, theme;

    public Reversi() {
        super(WindowTitle);

        score_black = new JLabel("2"); // the game start with 2 black pieces
        score_black.setForeground(Color.blue);
        score_black.setFont(new Font("Dialog", Font.BOLD, 16));
        score_white = new JLabel("2"); // the game start with 2 white pieces
        score_white.setForeground(Color.red);
        score_white.setFont(new Font("Dialog", Font.BOLD, 16));
        board = new ReversiBoard();
        gpanel = new GPanel(board, score_black, score_white, "Electric", 3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupMenuBar();
        gpanel.setMinimumSize(new Dimension(Reversi.Width, Reversi.Height));

        JPanel status = new JPanel();
        status.setLayout(new BorderLayout());
        status.add(score_black, BorderLayout.WEST);
        status.add(score_white, BorderLayout.EAST);
//		status.setMinimumSize(new Dimension(100, 30));
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gpanel, status);
        splitPane.setOneTouchExpandable(false);
        getContentPane().add(splitPane);

        pack();
        setVisible(true);
        setResizable(false);
    }


    // voci del menu di primo livello
// File Edit Help
//
    void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(buildGameMenu());
        menuBar.add(buildHelpMenu());
        setJMenuBar(menuBar);
    }


    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        String action = source.getText();
        if (action.equals("Classic")) gpanel.setTheme(action);
        else if (action.equals("Electric")) gpanel.setTheme(action);
        else if (action.equals("Flat")) gpanel.setTheme(action);
    }

    protected JMenu buildGameMenu() {
        JMenu game = new JMenu("Game");
        JMenuItem newWin = new JMenuItem("New");
        level = new JMenu("Level");
        theme = new JMenu("Theme");
        JMenuItem undo = new JMenuItem("Undo");
        hint = new JMenuItem("Hint");
        undo.setEnabled(false);
        JMenuItem quit = new JMenuItem("Quit");

// build level sub-menu
        ActionListener newLevel = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JMenuItem source = (JMenuItem) (e.getSource());
                gpanel.setLevel(Integer.parseInt(source.getText()));
            }
        };
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("2");
        group.add(rbMenuItem);
        level.add(rbMenuItem).addActionListener(newLevel);
        rbMenuItem = new JRadioButtonMenuItem("3", true);
        group.add(rbMenuItem);
        level.add(rbMenuItem).addActionListener(newLevel);
        rbMenuItem = new JRadioButtonMenuItem("4");
        group.add(rbMenuItem);
        level.add(rbMenuItem).addActionListener(newLevel);
        rbMenuItem = new JRadioButtonMenuItem("5");
        group.add(rbMenuItem);
        level.add(rbMenuItem).addActionListener(newLevel);
        rbMenuItem = new JRadioButtonMenuItem("6");
        group.add(rbMenuItem);
        level.add(rbMenuItem).addActionListener(newLevel);

// build theme sub-menu
        group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("Classic");
        group.add(rbMenuItem);
        theme.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        rbMenuItem = new JRadioButtonMenuItem("Electric", true);
        group.add(rbMenuItem);
        theme.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        rbMenuItem = new JRadioButtonMenuItem("Flat");
        group.add(rbMenuItem);
        theme.add(rbMenuItem);
        rbMenuItem.addActionListener(this);

// Begin "New"
        newWin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gpanel.clear();
                hint.setEnabled(true);
                repaint();
            }
        });
// End "New"

// Begin "Quit"
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
// End "Quit"


// Begin "Hint"
        hint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (gpanel.active) {
                    Move move = new Move();
                    if (board.findMove(TKind.black, gpanel.gameLevel, move))
                        gpanel.setHint(move);
                    repaint();
    /*					if (board.move(move,TKind.black) != 0) {
							score_black.setText(Integer.toString(board.getCounter(TKind.black)));
							score_white.setText(Integer.toString(board.getCounter(TKind.white)));
							repaint();
							javax.swing.SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									Cursor savedCursor = getCursor();
									setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
									gpanel.computerMove();
									setCursor(savedCursor);
								}
							});
						}
	*/
                } else hint.setEnabled(false);
            }
        });
// End "Hint"


        game.add(newWin);
        game.addSeparator();
        game.add(undo);
        game.add(hint);
        game.addSeparator();
        game.add(level);
        game.add(theme);
        game.addSeparator();
        game.add(quit);
        return game;
    }


    protected JMenu buildHelpMenu() {
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About " + WindowTitle + "...");
        JMenuItem openHelp = new JMenuItem("Help Topics...");

// Begin "Help"
        openHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createEditorPane();
            }
        });
// End "Help"

// Begin "About"
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon(Reversi.class.getResource("reversi.jpg"));
                JOptionPane.showMessageDialog(null, ABOUTMSG, "About " + WindowTitle, JOptionPane.PLAIN_MESSAGE, icon);
            }
        });
// End "About"

        help.add(openHelp);
        help.add(about);

        return help;
    }

    protected void createEditorPane() {
        if (helpActive) return;
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument) editorPane.getDocument()).processHTMLFrameHyperlinkEvent(
                                (HTMLFrameHyperlinkEvent) e);
                    } else {
                        try {
                            editorPane.setPage(e.getURL());
                        } catch (java.io.IOException ioe) {
                            System.out.println("IOE: " + ioe);
                        }
                    }
                }
            }
        });
        java.net.URL helpURL = Reversi.class.getResource("HelpFile.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
                new HelpWindow(editorPane);
            } catch (java.io.IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: HelpFile.html");
        }

        return;
    }


    public class HelpWindow extends JFrame {

        public HelpWindow(JEditorPane editorPane) {
            super("Help Window");
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Reversi.helpActive = false;
                    setVisible(false);
                }
            });

            JScrollPane editorScrollPane = new JScrollPane(editorPane);
            editorScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            getContentPane().add(editorScrollPane);
            setSize(600, 400);
            setVisible(true);
            helpActive = true;
        }
    }

    public HyperlinkListener createHyperLinkListener1() {
        return new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    if (e instanceof HTMLFrameHyperlinkEvent) {
                        ((HTMLDocument) editorPane.getDocument()).processHTMLFrameHyperlinkEvent(
                                (HTMLFrameHyperlinkEvent) e);
                    } else {
                        try {
                            editorPane.setPage(e.getURL());
                        } catch (java.io.IOException ioe) {
                            System.out.println("IOE: " + ioe);
                        }
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
        }
        Reversi app = new Reversi();
    }

}
