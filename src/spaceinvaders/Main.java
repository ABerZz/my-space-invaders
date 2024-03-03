package spaceinvaders;

import javax.swing.JFrame;

/**
 * Main class that runs the game. A JFrame is included within the main method to display
 * the game in a proper window.
 * @author Andrea
 * @since  January 2016
 */
public class Main {

	// declare public variables
	public static final int FRAME_WIDTH = 1280, FRAME_HEIGHT = 960;
	public static final char EASY = 'E', MEDIUM = 'M', HARD = 'H';
	
	// declare private variables
	private static final String TITLE = "Space Invaders";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create a new window for the game
		JFrame frame = new JFrame(TITLE);
		
		// settings for the window
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add the game panel on which the animations can be displayed
		frame.add(new GamePanel());
		
		frame.setVisible(true);
	}
	
} // end class
