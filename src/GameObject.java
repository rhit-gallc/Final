import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Abstract base for all objects in the game. Handles variables for location,
 * width, height, and sprite image. Also holds handles creation of sprite image
 * and its drawing.
 * 
 * Subclasses should be specific types of game objects
 * 
 * @authors Lizzy Jaynes
 * @reviewers
 */
public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	protected String spritePath;

	/**
	 * Creates a new game object at the specified position, with the given sprite.
	 * 
	 * @param x          initial x-coordinate
	 * @param y          initial y-coordinate
	 * @param spritePath path to sprite image file
	 */
	public GameObject(int x, int y, String spritePath) {
		this.x = x;
		this.y = y;
		this.width = Constants.SCALE;
		this.height = Constants.SCALE;
		this.spritePath = spritePath;

		loadSprite(spritePath);
	}

	/**
	 * Loads sprite image from file path. If the file cannot be read, the exception
	 * is handled and an alert is printed to the console.
	 * 
	 * @param spritePath path to sprite image file
	 */
	public void loadSprite(String spritePath) {
		try {
			sprite = ImageIO.read(new File(spritePath));
		} catch (IOException e) {
			System.out.println("Image not found!");
		}
	}

	/**
	 * Returns rectangular bounding box of the object
	 * 
	 * @return new Rectangle object
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Draws game object using image sprite. If no sprite is available a blue
	 * rectangle is drawn.
	 * 
	 * @param g Graphics object
	 */
	public void draw(Graphics g) {
		if (sprite != null)
			g.drawImage(sprite, x, y, width, height, null);
		else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
}