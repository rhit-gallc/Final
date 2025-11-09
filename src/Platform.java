import java.awt.*;
/**
 * Represents something an entity may stand on during the game
 * 
 * @authors Tyler Bindel
 * @reviewers
 */
public class Platform extends GameObject {
	public Platform(int x, int y, int width, int height, String spritePath) {
		super(x, y, width, height, spritePath);
	}
}