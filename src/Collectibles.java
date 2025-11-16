/**
 * Facilitates creation of and management of collectible items in the game.
 * Handles logic of if object has been collected and points it has.
 * 
 * @authors Tyler Bindel
 * @Reviewers Lizzy Jaynes
 */
public class Collectibles extends GameObject {
	private int points;
	public boolean collected = false;

	/**
	 * Creates new collectible at specified position
	 * 
	 * @param x          x-coordinate of the collectible
	 * @param y          y-corrdinate of the collectible
	 * @param spritePath path to sprite image file
	 */
	public Collectibles(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	/**
	 * Returns number of points collectible is worth
	 * 
	 * @return the point value of the collectible
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Records collectible status as collected
	 * 
	 * @param collected true if collectible is collected; otherwise false
	 */
	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	/**
	 * Checks if collectible has been collected.
	 * 
	 * @return true if collected; otherwise false
	 */
	public boolean isCollected() {
		return collected;
	}

	/**
	 * Sets collected to true
	 */
	public void collect() {
		collected = true;
	}
}
