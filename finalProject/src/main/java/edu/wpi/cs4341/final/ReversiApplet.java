/*
Applet Reversi

26-12-2006 version 0.1: initial release

*/

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.*;

public class ReversiApplet extends JApplet {
	static GPanel gpanel;
	ReversiBoard board;
	static JLabel score_black, score_white;

	public String[][] getParameterInfo() {
		String[][] info = {
		  // Parameter Name     Kind of Value   Description
			{"gametheme",		"String",		"game theme: Classic or Electric or Flat"},
			{"level",			"int",			"game level, in a range from 2 to 6"},
		};
		return info;
	}

	public String getAppletInfo() {
		return "Reversi Applet by javalc6";
	}


    public void init() {
		score_black=new JLabel("2"); // the game start with 2 black pieces
		score_black.setForeground(Color.blue);
		score_black.setFont(new Font("Dialog", Font.BOLD, 16));
		score_white=new JLabel("2"); // the game start with 2 white pieces
		score_white.setForeground(Color.red);
		score_white.setFont(new Font("Dialog", Font.BOLD, 16));
		board = new ReversiBoard();
		String levelString = getParameter("level");
		int level = 3;
		if (levelString != null)
			try {
				level = Integer.parseInt(levelString);
			} catch (NumberFormatException e) {
				//Use default width.
			}
		gpanel = new GPanel(board,score_black,score_white, getParameter("GameTheme"), level);

		gpanel.setMinimumSize(new Dimension(Reversi.Width,Reversi.Height));

		JPanel status = new JPanel();
		status.setLayout(new BorderLayout());
		status.add(score_black, BorderLayout.WEST);
		status.add(score_white, BorderLayout.EAST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gpanel, status);
		splitPane.setOneTouchExpandable(false);
		getContentPane().add(splitPane);

    }

	public void start() {
		gpanel.clear();
	}

}
