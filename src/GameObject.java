import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is an abstract class that is the outline for all created game objects
 * and their shared methods and variables.
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
	 * Constructor for abstract class GameObject, super class to all game pieces
	 * 
	 * @param x          x-coordinate
	 * @param y          y-coordinate
	 * @param width      object width
	 * @param height     object height
	 * @param spritePath path to sprite file
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
	 * Loads sprite image from png file. <br>
	 * This method takes the path to a png file, reads the image and assigns it as
	 * the objects sprite. If no image is found this method also handles that
	 * exception.
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
	 * Gets the bounds of the object as a new rectangle object.
	 * 
	 * @return new Rectangle object
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Draws game piece using image sprite, if one is not available, draw as a box.
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