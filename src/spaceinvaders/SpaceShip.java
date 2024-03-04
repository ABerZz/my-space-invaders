package spaceinvaders;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * This class describes a space ship object.
 * @author Andrea
 * @since  January 2016
 */
public class SpaceShip extends Figure{

	// declare variables
	private final static ImageIcon SHIP_ICON = new ImageIcon("./resources/SpaceShip.png");
	private static int vShip;
	private int width, height, vX;
	
	/**
	 * Constructor method that sets the position of the player ship.
	 * Calls the parent class to set the image.
	 * @param x - The x coordinate of the ship
	 * @param y - The y coordinate of the ship
	 */
	public SpaceShip() {
		
		super();
		
		width = SHIP_ICON.getIconWidth();
		height = SHIP_ICON.getIconHeight();
		setImage(SHIP_ICON.getImage());
		
		setX((Main.FRAME_WIDTH / 2) - (width / 2));
		setY(Main.FRAME_HEIGHT - 100);
	}
	
	/**
	 * Returns the width of the image of this ship.
	 * @return The width of the ship
	 */
	public int getWidth() {
		
		return width;
	}
	
	public int getHeight() {
		
		return height;
	}
	
	/**
	 * Sets the speed of the space ship, depending on the difficulty.
	 * @param - The difficulty level
	 */
	public static void setVShip(char v) {
		
		if(v == Main.EASY) {
			vShip = 4;
		}
		else if(v == Main.MEDIUM) {
			vShip = 2;
		}
		else if(v == Main.HARD) {
			vShip = 1;
		}
	}
	
	/**
	 * Moves the ship horizontally within the borders.
	 */
	public void move() {
		
		setX(getX() + vX);
		
		// stops at left border
		if(getX() < 30) {
			
			setX(30);
			vX = 0;
		}
		// stops at right border
		else if(getX() + width > 1250) {
			
			setX(Main.FRAME_WIDTH - 30 - width);
			vX = 0;
		}
	}
	
	/**
	 * Changes the direction of the space ship depending on which key is pressed.
	 * @param e - The key that has been pressed
	 */
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vX = vShip;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			vX = -vShip;
		}
	}
	
	/**
	 * Stops the space ship when the directional keys are released.
	 * @param e - The key that has been released
	 */
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vX = 0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			vX = 0;
		}
	}
} // end class
