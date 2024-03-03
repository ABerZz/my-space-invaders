package spaceinvaders;

import javax.swing.ImageIcon;

/**
 * This class describes a cannon object.
 * @author Andrea
 * @since  January 2016
 */
public class Cannon extends Figure {

	// declare variables
	private static final ImageIcon CANNON_ICON = new ImageIcon("../Space Invaders/resources/Cannon.png");
	private final int vY = 2;
	private int height;
	
	/**
	 * Constructor method for every new cannon that is currently not shooting.
	 */
	public Cannon() {
		
		super();
		
		height = CANNON_ICON.getIconHeight();
		
		destroy(); // so that the cannon is initialized but not visible
	}
	
	/**
	 * Constructor method for every new cannon that shoots.
	 * @param x - The x coordinate of the bullet
	 * @param y - The y coordinate of the bullet
	 */
	public Cannon(int x, int y) {
		
		super();
		
		height = CANNON_ICON.getIconHeight();
		setImage(CANNON_ICON.getImage());
		
		setX(x);
		setY(y);
		
	}
	
	/**
	 * Returns the height of the cannon's icon.
	 * @return The length of the bullet
	 */
	public int getHeight() {
		
		return height;
	}
	
	/**
	 * Moves the cannon bullet from a given ship within the frame.
	 * @param ship - The ship from which the cannon is shot
	 */
	public void move(Class<?> ship) {
		
		if(ship == SpaceShip.class) {
			setY(getY() - vY);
		}
		else if(ship == AlienShip.class) {
			setY(getY() + vY);
		}
		
		if(getY() + getHeight() >= Main.FRAME_HEIGHT) {
			destroy();
		}
		else if(getY() <= 0) {
			destroy();
		}
	}
	
} // end class
