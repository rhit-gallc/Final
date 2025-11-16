/**
 * Represents something an entity may stand on during the game. Extends
 * GameObject class
 * 
 * @authors Tyler Bindel
 * @reviewers Lizzy Jaynes
 */
public class Platform extends GameObject {
	/**
	 * Creates new platform at specified position.
	 * 
	 * @param x          x-coordinate
	 * @param y          y-coordinate
	 * @param spritePath path to sprite image file
	 */
	public Platform(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}
}