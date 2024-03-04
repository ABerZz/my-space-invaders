package spaceinvaders;

import javax.swing.ImageIcon;

/**
 * This class describes an alien ship object.
 * @author Andrea
 * @since  January 2016
 */
public class AlienShip extends Figure {

	// declare variables
	private final static ImageIcon ALIEN1_ICON = new ImageIcon("./resources/AlienShip1.jpg"),
			                       ALIEN2_ICON = new ImageIcon("./resources/AlienShip2.jpg"),
			                       ALIEN3_ICON = new ImageIcon("./resources/AlienShip3.png"),
	                               ALIENX_ICON = new ImageIcon("./resources/AlienShipX.png");
	private static int vAlien, vX;
	private int width, height;
	
	/**
	 * Constructor method that sets the position of the new alien ship and its image.
	 * Calls the parent class to create the ship.
	 * @param x - The x coordinate of the ship
	 * @param y - The y coordinate of the ship
	 * @param typeOfShip - Determines the image of the alien depending on a number from 0 to 54
	 */
	public AlienShip(int x, int y, int typeOfShip) {
		
		super();
		
		if(typeOfShip >= 0 && typeOfShip <= 10) {
			
			setImage(ALIEN1_ICON.getImage());
			
			width = ALIEN1_ICON.getIconWidth();
			height = ALIEN1_ICON.getIconHeight();
		}
		else if(typeOfShip >= 11 && typeOfShip <= 32){
			
			setImage(ALIEN2_ICON.getImage());
			
			width = ALIEN2_ICON.getIconWidth();
			height = ALIEN2_ICON.getIconHeight();
		}
		else if(typeOfShip >= 33 && typeOfShip <= 54) {
			
			setImage(ALIEN3_ICON.getImage());
			
			width = ALIEN3_ICON.getIconWidth();
			height = ALIEN3_ICON.getIconHeight();
		}
		
		setX(x);
		setY(y);
	}
	
	/**
	 * Constructor method exclusive of the bonus alien ship, sets the position of the new alien ship and its image.
	 * Calls the parent class to create the ship.
	 * @param x - The x coordinate of the ship
	 * @param y - The y coordinate of the ship
	 */
	public AlienShip(int x, int y) {
		
		super();
		
		setImage(ALIENX_ICON.getImage());
		
		width = ALIENX_ICON.getIconWidth();
		height = ALIENX_ICON.getIconHeight();
		
		setX(x);
		setY(y);
	}


	/**
	 * Returns the width of the image of this alien ship.
	 * @return The width of the ship
	 */
	public int getWidth() {
		
		return width;
	}
	
	/**
	 * Returns the height of the image of this alien ship.
	 * @return The height of the ship
	 */
	public int getHeight() {
		
		return height;
	}
	
	/**
	 * Returns the constant speed of all the standard alien ships
	 * @return The speed of the ships
	 */
	public static int getVAlien() {
		
		return vAlien;
	}
	
	/**
	 * Sets the constant speed of all the standard alien ships, depending on the difficulty, and their direction.
	 * @param v - The difficulty level
	 */
	public static void setVAlien(char v) {
		
		if(v == Main.EASY) {
			vAlien = 5;
		}
		else if(v == Main.MEDIUM) {
			vAlien = 10;
		}
		else if(v == Main.HARD) {
			vAlien = 20;
		}
		vX = vAlien;
	}
	
	/**
	 * Sets the speed of all the standard ships and their direction. Overloads setVAlien.
	 * @param v - The speed of the ships
	 */
	public static void setVAlien(int v) {
		
		vAlien = v;
		vX = v;
	}
	
	/**
	 * Changes the direction of all the alien ships
	 */
	public static void turn() {
		
		vX = -vX;
	}
	
	/**
	 * Moves the ship horizontally towards its direction.
	 */
	public void moveH() {
		
		if(isIntact()) {
			
			// the command is different whether it is called by the bonus ship
			if(getImage() == ALIENX_ICON.getImage()) {
				
				setX(getX() - vAlien / 5);
				
				// destroy the bonus ship when it goes out the frame
				if(getX() + getWidth() <= 0)
					destroy();
			}
			// or by a standard ship
			else {
				
				setX(getX() + vX);
			}
		}
	}
	
	/**
	 * Moves the ship vertically downward.
	 */
	public void moveV() {
		
		if(isIntact()) {
			
			setY(getY() + 25);
		}
	}
} // end class
