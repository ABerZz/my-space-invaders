package spaceinvaders;

import java.awt.Image;

/**
 * This class describes all the figure objects.
 * @author Andrea
 * @since  January 2016
 */
public class Figure {

	// declare variables
	private boolean intact;
	private int x, y;
	private Image im;
	
	/**
	 * Constructor method that sets the image of the new figure and sets the object intact.
	 * @param i - The image of the figure
	 */
	public Figure() {
		
		intact = true;
	}
	
	/**
	 * Returns the status of the figure.
	 * @return True if the figure is intact, false if not
	 */
	public boolean isIntact() {
		
		return intact;
	}
	
	/**
	 * Returns the horizontal position of the figure.
	 * @return The x coordinate of the figure
	 */
	public int getX() {
		
		return x;
	}
	
	/**
	 * Returns the vertical position of the figure.
	 * @return The y coordinate of the figure
	 */
	public int getY() {
		
		return y;
	}
	
	/**
	 * Returns the image of the figure.
	 * @return The image of the figure
	 */
	public Image getImage() {
		
		return im;
	}
	
	/**
	 * Sets the intact status of the figure to false.
	 */
	public void destroy() {
		
		intact = false;
	}
	
	/**
	 * Sets the horizontal position of the figure.
	 * @param n - The new x coordinate of the figure
	 */
	public void setX(int n) {
		
		x = n;
	}
	
	/**
	 * Sets the vertical position of the figure.
	 * @param n - The new y coordinate of the figure
	 */
	public void setY(int n) {
		
		y = n;
	}
	
	/**
	 * Sets the image of the figure
	 * @param i - The new image of the figure
	 */
	public void setImage(Image i) {
		
		im = i;
	}
	
} // end class
