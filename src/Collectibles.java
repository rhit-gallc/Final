import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Facilitates creation of and management of collectible items in the game.
 * 
 * @authors Tyler Bindel
 * @Reviewers Lizzy Jaynes
 */
public class Collectibles extends MovingEntity {
	private int points;
	public boolean collected = false;

	public Collectibles(int x, int y, int width, int height, String spritePath) {
		super(x, y, width, height, spritePath);
		// TODO Auto-generated constructor stub
	}

	public int getPoints() {
		return points;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	@Override
	public void update() {
	}

	public boolean isCollected() {
		// TODO Auto-generated method stub
		return collected;
	}
	
	public void collect() {
		collected = true;
	}
}
