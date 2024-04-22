package spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * Primary class of the package that contains all the elements displayed in a panel,
 * as well as the event listeners and the paint component methods.
 * @author Andrea
 * @since  January 2015
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener {

	// declare variables
	private SpaceShip pShip;
	private AlienShip aShipX;
	private AlienShip[] aShip; 
	private Cannon pCannon, aCannon;
	private JButton[] buttons = {new JButton("Easy"), new JButton("Medium"), new JButton("Hard")};
	private JButton instrButton, creditButton, lbButton, backButton;
	private JTextArea instrText, lbText, creditText;
	private JScrollPane lbScrollPane;
	private JLabel label, pause, endGame;
	private Random rnd;
	private char difficulty;
	private boolean startGame, createShipX;
	private int numAliensLeft, score, countDown;
	private static final int NUM_ALIENS = 55;
	private static final ImageIcon BACKGROUND = new ImageIcon("./resources/background.jpg");
	
	
	/**
	 * Constructor method for the game panel. All buttons, labels, texts and figures are
	 * initialized within the panel.
	 */
	public GamePanel() {
		
		// settings for the panel
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		//setBackground(Color.BLACK);
		setLayout(null);
		
		// add a label for when the game is paused
		pause = new JLabel("PAUSE");
		pause.setFont(new Font("OCR A Extended", Font.BOLD, 80));
		pause.setForeground(Color.YELLOW);
		pause.setBounds(Main.FRAME_WIDTH / 2 - 130, Main.FRAME_HEIGHT / 2 - 25, 260, 50);
		pause.setVisible(false);
		add(pause);
		
		// add a label for when the game is over
		endGame = new JLabel();
		endGame.setFont(new Font("OCR A Extended", Font.BOLD, 60));
		endGame.setVisible(false);
		add(endGame);
		
		// add the menu label
		label = new JLabel("To start the game, choose the difficulty");
		label.setFont(new Font("OCR A Extended", Font.BOLD, 25));
		label.setForeground(Color.WHITE);
		label.setBounds(Main.FRAME_WIDTH / 2 - 320, 40, 640, 50);
		label.setVisible(true);
		add(label);
		
		// add the three buttons that allow to choose the difficulty
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(new Font("OCR A Extended", Font.BOLD, 40));
			buttons[i].setBackground(Color.GREEN);
			buttons[i].setForeground(Color.BLACK);
			buttons[i].setBounds(Main.FRAME_WIDTH / 2 - 120, 130 + 160 * i, 240, 120);
			buttons[i].setVisible(true);
			buttons[i].addActionListener(this);
			add(buttons[i]);
		}
		
		// add button to exit from instruction and leader board menus
		backButton = new JButton("Back");
		backButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		backButton.setBackground(Color.RED);
		backButton.setForeground(Color.BLACK);
		backButton.setBounds(Main.FRAME_WIDTH - 160 , 10, 150, 75);
		backButton.setVisible(false);
		backButton.addActionListener(this);
		add(backButton);
		
		// add the button to display the instructions
		instrButton = new JButton("Instructions");
		instrButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		instrButton.setBackground(Color.YELLOW);
		instrButton.setForeground(Color.BLACK);
		instrButton.setBounds(Main.FRAME_WIDTH / 2 - 340 , Main.FRAME_HEIGHT - 200, 200, 100);
		instrButton.setVisible(true);
		instrButton.addActionListener(this);
		add(instrButton);
		
		// add the text area for the instructions
		instrText = new JTextArea("left-right arrow keys = move\n\nspace bar = shoot\n\nalt gr key = pause/resume\n\nDon't let the aliens reach the Earth!");
		instrText.setFont(new Font("OCR A Extended", Font.BOLD, 50));
		instrText.setBackground(Color.BLACK);
		instrText.setForeground(Color.WHITE);
		instrText.setBounds(170, 250, 940, 460);
		instrText.setWrapStyleWord(true);
		instrText.setLineWrap(true);
		instrText.setEditable(false);
		instrText.setVisible(false);
		add(instrText);
		
		// add the button to display credits
		creditButton = new JButton("Credits");
		creditButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		creditButton.setBackground(Color.YELLOW);
		creditButton.setForeground(Color.BLACK);
		creditButton.setBounds(Main.FRAME_WIDTH / 2 - 100 , Main.FRAME_HEIGHT - 200, 200, 100);
		creditButton.setVisible(true);
		creditButton.addActionListener(this);
		add(creditButton);
		
		// add the text area for the credits
		creditText = new JTextArea();
		creditText.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		creditText.setBackground(Color.BLACK);
		creditText.setForeground(Color.WHITE);
		creditText.setBounds(100, 100, 980, 760);
		creditText.setWrapStyleWord(true);
		creditText.setLineWrap(true);
		creditText.setEditable(false);
		creditText.setText("\t\tCREDITS\n\n"
				         + " Original game authors:\n"
				         + "Space Invaders Development Team\nTomohiro Nishikado\nYoichi Wada\n"
				         + "Square Enix Holdings Co.\nTaito Corporation\n\n"
				         + " Images references:\ndecaldesignshop.com\nnerdist.com\npsnprofiles.com\n\n"
				         + " Developer:\nAndrea Bertazzoni\n\n"
				         + " Testers:\nEthan Lee\nYunsun Hwang");
		creditText.setVisible(false);
		add(creditText);
		
		// add the button to display the leader board
		lbButton = new JButton("Leaderboard");
		lbButton.setFont(new Font("OCR A Extended", Font.BOLD, 20));
		lbButton.setBackground(Color.YELLOW);
		lbButton.setForeground(Color.BLACK);
		lbButton.setBounds(Main.FRAME_WIDTH / 2 + 140 , Main.FRAME_HEIGHT - 200, 200, 100);
		lbButton.setVisible(true);
		lbButton.addActionListener(this);
		add(lbButton);
		
		// add the text area with the scroll pane for the leader board
		lbText = new JTextArea();
		lbText.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
		lbText.setBackground(Color.BLACK);
		lbText.setForeground(Color.WHITE);
		lbText.setBounds(100, 100, 980, 760);
		lbText.setEditable(false);
		lbText.setVisible(false);
		lbScrollPane = new JScrollPane();
		lbScrollPane.setBounds(lbText.getBounds());
		lbScrollPane.setVisible(false);
		add(lbText);
		add(lbScrollPane);
		
		// make sure the file "resources/Leaderboard.txt" exists in the directory;
		// if it does not, create one
		IO.createOutputFile("resources/Leaderboard.txt", true);
		IO.closeOutputFile();

		// read the leader board from the file "resources/Leaderboard.txt" in the directory,
		// and then add the lines to the leader board text area
		IO.openInputFile("resources/Leaderboard.txt");
		String line = IO.readLine();
		
		while(line != null){
			lbText.setText(lbText.getText() + "\n" + line);
			line = IO.readLine();
		}
		
		IO.closeInputFile();
		
		
		// initialize a player ship
		pShip = new SpaceShip();
		
		// initialize 55 alien ships
		aShip = new AlienShip[NUM_ALIENS];
		for(int i = 0; i < NUM_ALIENS; i++) {
			
			// define the position for each ship by these formula
			// and the image by their index
			aShip[i] = new AlienShip(50 + ((i % 11) * 80), 125 + ((i / 11) * 70), i);
		}
		
		// not time to initialize the bonus alien ship yet
		createShipX = false;
		
		// initialize cannons as not visible
		pCannon = new Cannon();
		aCannon = new Cannon();
		
		rnd = new Random();
		
		
		setVisible(true);
		
	} // end constructor
	
	
	
	//****************************************PAINT METHODS****************************************
	
	/**
	 * Private method that helps painting the player ship. 
	 * @param g - the Graphics object to protect
	 */
	private void paintPShip(Graphics g) {
		
		if(pShip.isIntact())
			g.drawImage(pShip.getImage(), pShip.getX(), pShip.getY(), this);
	} // end paintPShip
	
	/**
	 * Private method that helps painting the alien ships.
	 * @param g - the Graphics object to protect
	 */
	private void paintAShip(Graphics g) {
		
		for(int i = 0; i < NUM_ALIENS; i++) {
			
			if(aShip[i].isIntact())
				g.drawImage(aShip[i].getImage(), aShip[i].getX(), aShip[i].getY(), this);
		}
		
		if(createShipX)
			g.drawImage(aShipX.getImage(), aShipX.getX(), aShipX.getY(), this);
		
	} // end paintAShip
	
	/**
	 * Private method that helps painting the shooting cannons.
	 * @param g - The Graphics object to protect
	 */
	private void paintCannon(Graphics g) {
		
		if(pCannon.isIntact())
			g.drawImage(pCannon.getImage(), pCannon.getX(), pCannon.getY(), this);
		
		if(aCannon.isIntact())
			g.drawImage(aCannon.getImage(), aCannon.getX(), aCannon.getY(), this);
		
	} // end paintCannon

	/**
	 * Overriding method that calls all the other paint methods of the class in order to draw
	 * all the figures on the panel.
	 * @param g - the Graphics object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(BACKGROUND.getImage(), 0, 0, null);
		
		if(startGame) {
			
			// draw cannons
			paintCannon(g);
			
			// draw alien ships
			paintAShip(g);
			
			// draw player ship
			paintPShip(g);
			
			// draw score label
			g.setColor(Color.WHITE);
			g.setFont(new Font("OCR A Extended", Font.BOLD, 25));
			g.drawString("Score: " + Integer.toString(score), Main.FRAME_WIDTH - 220, 30);
			
			// draw limit line
			g.setColor(Color.RED);
			g.drawLine(0, Main.FRAME_HEIGHT - 150, Main.FRAME_WIDTH, Main.FRAME_HEIGHT - 150);
			
		}
	} // end paintComponent
	
	
	
	//**************************************END GAME METHODS***************************************
	
	/**
	 * This method is called when the player wins
	 */
	private void newGame() {
		
		// freeze every operating method based on startGame
		startGame = false;
		
		endGame.setForeground(Color.GREEN);
		endGame.setVisible(true);
		
		// start a count down from 5 to 0, displaying at every moment a new label
		if(countDown == 6) {
			// the first count of countDown is empty because, right now, the timer has a delay
			// too small to display anything, since the label would be displayed for only 1/2 second
		}
		// now the label is displayed
		if(countDown == 5) {
			endGame.setBounds(Main.FRAME_WIDTH / 2 - 157, Main.FRAME_HEIGHT / 2 - 30, 312, 60);
			endGame.setText("You Won!");
		}
		else if(countDown == 4) {
			endGame.setBounds(100, Main.FRAME_HEIGHT / 2 - 30, Main.FRAME_WIDTH, 60);
			endGame.setText("Get ready for next round...");
		}
		else if(countDown == 3 || countDown == 2 || countDown == 1) {
			endGame.setBounds(Main.FRAME_WIDTH / 2 - 20, Main.FRAME_HEIGHT / 2 - 30, 40, 60);
			endGame.setText(Integer.toString(countDown));
		}
		// when the count down is done
		else if(countDown == 0) {
			
			// settings for a new game //
			
			endGame.setVisible(false);
			
			// initialize a player ship
			pShip = new SpaceShip();
			
			// initialize 55 alien ships
			aShip = new AlienShip[NUM_ALIENS];
			for(int i = 0; i < NUM_ALIENS; i++) {
				
				// define the position for each ship by these formula
				aShip[i] = new AlienShip(50 + ((i % 11) * 80), 125 + ((i / 11) * 70), i);
			}
			// set their speed, but faster than before
			AlienShip.setVAlien(AlienShip.getVAlien() + 5);
			
			// if when the game ended there was a bonus ship flying, remove it
			if(createShipX) {
				aShipX.destroy();
				createShipX = false;
			}
			
			// initialize cannons as not visible
			pCannon = new Cannon();
			aCannon = new Cannon();
			
			// reset the variables as a new game
			numAliensLeft = NUM_ALIENS;
			timeShift = 0;
			countDown = 7;
			// increase player's score
			score += 200;
			
			// after many games are won, increase the rate of time at which the aliens move
			if(AlienShip.getVAlien() >= 40)
				aT.setDelay(400);
			// otherwise, set it as default
			else
				aT.setDelay(500);
			
			
			startGame = true;
		}
		
	} // end newGame
	
	
	/**
	 * This method is called when the player loses.
	 */
	private void gameOver() {
		
		// destroy both the cannons and the player
		aCannon.destroy();
		pShip.destroy();
		pCannon.destroy();
		
		// freeze the game
		pT.stop();
		aT.stop();
		cT.stop();
		
		// display the ending label, depending on the result of the game
		endGame.setForeground(Color.RED);
		endGame.setBounds(Main.FRAME_WIDTH / 2 - 215, Main.FRAME_HEIGHT / 2 - 30, 430, 60);
		endGame.setText("You Lost...");
		endGame.setVisible(true);
		
		repaint();
		
		
		/* Save the score with the player name in the leader board */
		
		// ask the user to enter his name
		String name = "";
		boolean invalid = false;
		
		try {
			
			// if nothing is entered
			if((name = JOptionPane.showInputDialog("Enter a valid name (between 1 and 10 characters):")) == null)
				invalid = true;
			
			if(invalid != true){
				
				// or if the input is longer than 10 characters
				if(name.length() > 10 || name.equals("")) {
					
					invalid = true;
				}
				// or if the input is all made of points or spaces
				else {
					
					invalid = true;
					
					for(int i = 0; i < name.length(); i++) {
						
						if(name.charAt(i) != '.' && name.charAt(i) != ' ') {
							// if the name is valid, the first non-point or space character found is
							// set as beginning of the name
							name = name.substring(i);
							invalid = false;
							break;
						}
					}
				}
				
			}
		}
		catch(Exception exc) {
		}
		
		// if the game is not valid, tell the user that the score was not saved
		if(invalid) {
			JOptionPane.showMessageDialog(null, "The score has not been saved.");
		}
		// if it is valid, save it in the leader board and rearrange the order of the stored scores
		else {
			
			// initialize variables for both the score and the type of leader board
			String newScore = difficulty + "\t" + name;
			
			// adjust the distance inside the string between the score and the name
			for(int i = 0; i < 13 - name.length(); i++) {
				newScore += ".";
			}
			
			newScore += Integer.toString(score);
			
			// store all the current scores with the new score in an array
			IO.openInputFile("resources/Leaderboard.txt");
			
			// determine the number of saved scores
			String line = IO.readLine();
			int numLines = 0;
			
			while(line != null){
				numLines++;
				line = IO.readLine();
			}
			
			IO.closeInputFile();
			
			// add the scores to the array
			String[] allScores  = new String[numLines + 1];
			
			IO.openInputFile("resources/Leaderboard.txt");
			
			for(int i = 0; i < numLines; i++)
				allScores[i] = IO.readLine();
				
			// add the new score
			allScores[numLines] = newScore;
			
			IO.closeInputFile();
			
			// bubble sort the scores in the array, from the greatest to the smallest
			boolean check = true;
			
			while(check) {
				
				check = false;
				
				for(int i = 0; i < allScores.length - 1; i++) {
					
					if(Integer.parseInt(allScores[i].substring(allScores[i].lastIndexOf('.') + 1)) < Integer.parseInt(allScores[i + 1].substring(allScores[i + 1].lastIndexOf('.') + 1))) {
						
						String temp = allScores[i];
						allScores[i] = allScores[i + 1];
						allScores[i + 1] = temp;
						
						check = true;
					}
				}
			}
			
			// keep only the top 100 scores of the same level
			int countE = 0, countM = 0, countH = 0;
			
			for(int i = 0; i < allScores.length; i++) {
				
				// after the best 100 scores of the same difficulty, all further scores of that same level
				// are set as empty slots
				if(allScores[i].charAt(0) == Main.EASY)
					countE++;
				else if(allScores[i].charAt(0) == Main.MEDIUM)
					countM++;
				else if(allScores[i].charAt(0) == Main.EASY)
					countH++;
				
				if(countE > 100 && allScores[i].charAt(0) == Main.EASY)
					allScores[i] = "";
				else if(countM > 100 && allScores[i].charAt(0) == Main.MEDIUM)
					allScores[i] = "";
				else if(countH > 100 && allScores[i].charAt(0) == Main.HARD)
					allScores[i] = "";
				
			}
			
			// enter the sorted list of score in the leader board
			IO.createOutputFile("resources/Leaderboard.txt");
			
			for(int i = 0; i < allScores.length; i++) {
				
				// all the empty slots are not printed
				if(!allScores[i].equals(""))
					IO.println(allScores[i]);
			}
			
			IO.closeOutputFile();
			
			// display a message if the score could be saved
			JOptionPane.showMessageDialog(null, "The score has successfully been saved.");
		}
		
	} // end gameOver

	
	
	//************************************OPENING MENU METHODS*************************************
	
	/**
	 * Private method invoked when one of the level buttons is pressed.
	 * Helps initializing the game.
	 * @param o - The button object that has been pressed
	 */
	private void buttonsPerformer(Object o) {
		
		// set difficulty parameters, remove all the menu components
		// (those that are not visible too, to avoid any problem), start the timers and the game
		
		if(o == buttons[0]) {
			difficulty = Main.EASY;
		}
		else if(o == buttons[1]) {
			difficulty = Main.MEDIUM;
		}
		else if(o == buttons[2]) {
			difficulty = Main.HARD;
		}
		
		SpaceShip.setVShip(difficulty);
		AlienShip.setVAlien(difficulty);
		
		for(int i = 0; i < buttons.length; i++) {
			
			buttons[i].setVisible(false);
			remove(buttons[i]);
		}
		
		label.setVisible(false);
		remove(label);
		
		instrButton.setVisible(false);
		remove(instrButton);
		
		creditButton.setVisible(false);
		remove(creditButton);
		
		lbButton.setVisible(false);
		remove(lbButton);
		
		remove(backButton);
		
		remove(instrText);
		
		remove(creditText);
		
		remove(lbText);
		
		remove(lbScrollPane);
		
		numAliensLeft = NUM_ALIENS;
		score = 0;
		countDown = 7;
		
		pT.start();
		aT.start();
		cT.start();
		
		startGame = true; // this allows every listener and the paint method to operate
	} // end buttonsPerformer
	
	/**
	 * Private method invoked when the instructions button is pressed.
	 * Helps displaying the instructions in the menu.
	 */
	private void instrPerformer() {
		
		for(int i = 0; i < buttons.length; i++) {
			
			buttons[i].setVisible(false);
		}
		
		label.setVisible(false);
		
		instrButton.setVisible(false);
		
		creditButton.setVisible(false);
		
		lbButton.setVisible(false);
		
		backButton.setVisible(true);
		
		instrText.setVisible(true);
	} // end instrPerformer
	
	/**
	 * 
	 */
	private void creditPerformer() {
		
		for(int i = 0; i < buttons.length; i++) {
			
			buttons[i].setVisible(false);
		}
		
		label.setVisible(false);
		
		instrButton.setVisible(false);
		
		creditButton.setVisible(false);
		
		lbButton.setVisible(false);
		
		backButton.setVisible(true);
		
		creditText.setVisible(true);
	} // end creditPerformer
	
	/**
	 * Private method invoked when the leader board button is pressed.
	 * Helps displaying the leader board in the menu.
	 */
	private void lbPerformer() {
		
		for(int i = 0; i < buttons.length; i++) {
			
			buttons[i].setVisible(false);
		}
		
		label.setVisible(false);
		
		instrButton.setVisible(false);
		
		creditButton.setVisible(false);
		
		lbButton.setVisible(false);
		
		backButton.setVisible(true);
		
		lbText.setVisible(true);
		
		lbScrollPane.setViewportView(lbText);
		lbScrollPane.setVisible(true);
	} // end lbPerformer
	
	/**
	 * Private method invoked when the back button is pressed.
	 * Helps going back to the main menu from a secondary menu.
	 */
	private void backPerformer() {
		
		for(int i = 0; i < buttons.length; i++) {
			
			buttons[i].setVisible(true);
		}
		
		label.setVisible(true);
		
		instrButton.setVisible(true);
		
		creditButton.setVisible(true);
		
		lbButton.setVisible(true);
		
		backButton.setVisible(false);
		
		instrText.setVisible(false);
		
		creditText.setVisible(false);
		
		lbText.setVisible(false);
		
		lbScrollPane.setVisible(false);
	} // end backPerformer
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object o = new Object();
		o = e.getSource();
		
		// if any of the buttons is pressed, call the respective method
		if(o == buttons[0] || o == buttons[1] || o == buttons[2]) {
			
			buttonsPerformer(o);
		}
		else if(o == instrButton) {
			
			instrPerformer();
		}
		else if(o == creditButton) {
			
			creditPerformer();
		}
		else if(o == lbButton) {
			
			lbPerformer();
		}
		else if(o == backButton) {
			
			backPerformer();
		}
		
		repaint(); // need this to refresh the panel after every action
		
	}// end actionPerformed
	
	
	
	//********************************TIMERS AND PERFORMER METHODS*********************************
	
	/* Declare and initialize all the additional actionListener objects with their
	 * own actionPerformed method and their own timer. This is done because each
	 * actionListener is related to either the player ship, or the alien ships,
	 * or the cannons, and also because each figure object is moving with different
	 * time rates and therefore needs a personal timer. */
	
	private ActionListener pShipPerformer = new ActionListener() { // actionListener for the 
                                                                   // player ship
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(startGame) {
				
				pShip.move();
				
				
				// the movement command for the extra alien ship is given here because this ship
				// moves with same same time rate as the player ship
				if(createShipX) {
					
					aShipX.moveH();
					
					if(!aShipX.isIntact())
						createShipX = false;
				}
			}
			
			repaint();
		}
	};
	
	private Timer pT = new Timer(5, pShipPerformer); // timer for the player ship
	
	
	// timeShift is necessary to properly move the alien ships
	private int timeShift = 0; // it is initially even
	
	private ActionListener aShipPerformer = new ActionListener() { // actionListener for the
		                                                           // alien ships
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(startGame) {
				
				/* This codes give the turn animation to the alien ships */
				
				// indexes for the farthest alien ship to the left and to the right
				int lEdge = -1, rEdge = -1;
				
				// this for loop keeps track of the left-most alien
				for(int i = 0; i < NUM_ALIENS; i++) {
					
					if(aShip[i].isIntact()) {
						
						// the first intact alien ship is set as left edge
						if(lEdge == -1)
							lEdge = i;
						
						// go through all the alien ships and every time sets the edge as the
						// one with the smallest x 
						if(aShip[i].getX() < aShip[lEdge].getX())
							lEdge = i;
					}
				}
				
				// this for loop keeps track of the right-most alien
				for(int i = 0; i < NUM_ALIENS; i++) {
					
					if(aShip[i].isIntact()) {
						
						// the first intact alien ship is set as right edge
						if(rEdge == -1)
							rEdge = i;
						
						// go through all the alien ships and every time sets the edge as the
						// one with the greatest x 
						if(aShip[i].getX() > aShip[rEdge].getX())
							rEdge = i;
					}
				}
				
				
				// if there is still an alien in the game
				if(lEdge != -1 || rEdge != -1) {
					
					// if the left-most alien ship reaches the left border
					if(aShip[lEdge].getX() < 50) {
						
						/* This all happens inside the left border */
						
						// at the first action input of the timer while the alien is in the border,
						// timeShift becomes odd; at the second action input of the timer, timeShift
						// becomes even
						timeShift++;
						
						// when timeShift is odd, move all the aliens downward
						if(timeShift % 2 != 0) {
							
							for(int i = 0; i < NUM_ALIENS; i++)
								aShip[i].moveV();
						}
						// when timeShift is even, change direction and move all the aliens out
						// of the border
						else {
							
							AlienShip.turn();
							for(int i = 0; i < NUM_ALIENS; i++)
								aShip[i].moveH();
						}
					}
					// if the right-most alien ship reaches the right border
					else if(aShip[rEdge].getX() + aShip[rEdge].getWidth() > 1230) {
						
						/* This all happens inside the right border */
						
						// at the first action input of the timer while the alien is in the border,
						// timeShift becomes odd; at the second action input of the timer, timeShift
						// becomes even
						timeShift++;
						
						// when timeShift is odd, move all the aliens downward
						if(timeShift % 2 != 0) {
							
							for(int i = 0; i < NUM_ALIENS; i++)
								aShip[i].moveV();
						}
						// when timeShift is even, change direction and move all the aliens out
						// of the border
						else {
							
							AlienShip.turn();
							for(int i = 0; i < NUM_ALIENS; i++)
								aShip[i].moveH();
						}
					}
					// if no alien is in any border
					else {
						
						for(int i = 0; i < NUM_ALIENS; i++)
							aShip[i].moveH();
					}
				}
				
				
				/* This codes determine the creation of a new bomb from any of lowest aliens */
				
				// if the there is no cannon shooting in the moment
				while(!aCannon.isIntact()) {
					
					// random index for the column of the shooting cannon
					int index = rnd.nextInt(11) + 1;
					
					// this loops goes from the lowest to the highest alien
					for(int i = 5; i > 0; i--) {
						
						// the lowest intact alien that is found shoots the cannon
						if(aShip[11 * i - index].isIntact()) {
							
							aCannon = new Cannon(aShip[11 * i - index].getX() + aShip[11 * i - index].getWidth() / 2 - 1, aShip[11 * i - index].getY() + aShip[11 * i - index].getHeight() - aCannon.getHeight());
							break;
						}
					}
					
					// if the column was all empty, the while loop will repeat the
					// operation with another random number
				}
				
				
				/* This codes determine variations in the game */
				
				// as time flows, the score decreases
				if(score > 0)
					score--;
				
				// every half second, there is 1% probability that a bonus alien ship is created,
				// if it is not created yet; the probability increases as the aliens speed increases
				if(!createShipX) {
					
					if(rnd.nextInt(100) == 0) {
						aShipX = new AlienShip(Main.FRAME_WIDTH, 75);
						createShipX = true;
					}
				}
				
				
				/* This codes determine when the game ends */
				
				// if any alien goes beyond the limit line
				for(int i = 0; i < NUM_ALIENS; i++) {
					
					if(aShip[i].getY() + aShip[i].getHeight() >= Main.FRAME_HEIGHT - 140) {
						
						// reduce the score depending on the number of aliens left
						for(int j = 0; j < numAliensLeft; j++) {
							if(score > 30) {
								score -= 30;
							}
							else {
								score = 0;
							}
						}
						
						// end the game
						gameOver();
						break;
					}
				}
			}
			
			// if all the aliens are destroyed
			if(numAliensLeft == 0) {
				
				// change the timer delay, so that the labels between the two games
				// are visible for a few moments
				aT.setDelay(2000);
				
				// the count down starts when the player wins, it allows to restart
				// a new game with some delay between each game
				countDown--;
				newGame();
			}
			
			repaint();
		}
	};
	
	private Timer aT = new Timer(500, aShipPerformer); // timer for the alien ships
	
	
	private ActionListener cannonPerformer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if(startGame) {
				
				// move the cannons as soon as they are shot
				if(pCannon.isIntact()) {
					pCannon.move(SpaceShip.class);
				}
				
				if(aCannon.isIntact()) {
					aCannon.move(AlienShip.class);
				}
				
				
				// if the player cannon enters the area occupied by any of the alien
				for(int i = 0; i < NUM_ALIENS; i++) {
					
					if(aShip[i].isIntact() && pCannon.isIntact() && pCannon.getX() >= aShip[i].getX() && pCannon.getX() <= aShip[i].getX() + aShip[i].getWidth() && pCannon.getY() >= aShip[i].getY() && pCannon.getY() <= aShip[i].getY() + aShip[i].getHeight()) {
						
						// destroy both the cannon and the alien
						aShip[i].destroy();
						pCannon.destroy();
						
						// reduce the number of aliens left, increase the score
						numAliensLeft--;
						score += 30;
						
						// when half of the aliens are gone, the remaining ones get faster
						if(numAliensLeft == 11) {
							aT.setDelay(aT.getDelay() / 2);
						}
						// if there is only one alien ship left, the last ship gets way faster
						else if(numAliensLeft == 1) {
							aT.setDelay(aT.getDelay() / 5);
						}
						
					}
				}
				
				
				// if the player cannon enters the area occupied by the bonus alien ship
				if(createShipX && aShipX.isIntact() && pCannon.isIntact() && pCannon.getX() >= aShipX.getX() && pCannon.getX() <= aShipX.getX() + aShipX.getWidth() && pCannon.getY() >= aShipX.getY() && pCannon.getY() <= aShipX.getY() + aShipX.getHeight()) {
					
					// destroy both the cannon and the alien
					aShipX.destroy();
					pCannon.destroy();
					
					// increase the score by an extra amount
					score += 300;
				}
				
				
				// if alien cannon enters the area occupied by the player
				if(aCannon.getX() >= pShip.getX() && aCannon.getX() <= pShip.getX() + pShip.getWidth() && aCannon.getY() + aCannon.getHeight() >= pShip.getY() && aCannon.getY() + aCannon.getHeight() <= pShip.getY() + pShip.getHeight()) {
					
					// reduce the score
					if(score > 99)
						score -= 100;
					else
						score = 0;
					
					// end the game
					gameOver();
				}
				
				
				//if the player cannon crashes into a alien cannon
				if(pCannon.isIntact() && aCannon.isIntact()) {
					
					if(pCannon.getX() == aCannon.getX() - 1 || pCannon.getX() == aCannon.getX() || pCannon.getX() == aCannon.getX() + 1) {
						
						if(pCannon.getY() < aCannon.getY() + aCannon.getHeight() && pCannon.getY() > aCannon.getY()) {
							
							// destroy both cannons
							pCannon.destroy();
							aCannon.destroy();
						}
					}
				}
				
			}
			
			repaint();
		}
	};
	
	private Timer cT = new Timer(2, cannonPerformer);
	
	
	
	//*************************************KEYLISTENER METHODS*************************************
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(startGame) {
			
			// pause key
			if(e.getKeyCode() == KeyEvent.VK_ALT) {
				
				// if the timers are running the game is paused, otherwise it is started
				if(pT.isRunning() || aT.isRunning() || cT.isRunning()) {
					
					pT.stop();
					aT.stop();
					cT.stop();
					
					pause.setVisible(true);
				}
				else {
					
					pT.start();
					aT.start();
					cT.start();
					
					pause.setVisible(false);
				}
			}
			// shoot key
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				
				if(!pCannon.isIntact())
					pCannon = new Cannon(pShip.getX() + pShip.getWidth() / 2 - 1, pShip.getY());
			}
			// movement keys
			else {
				
				pShip.keyPressed(e);
			}
		}
		
	} // end keyPressed
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(startGame) {
			
			pShip.keyReleased(e);
		}
		
	} // end keyReleased

	
	// this method is needed by the implementation of keyListener, although it is not used
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	} 

} // end class
