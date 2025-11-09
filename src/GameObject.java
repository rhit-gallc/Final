import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	public GameObject(int x, int y, int width, int height, String spritePath) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	 * 
	 * 
	 * @return new Rectangle object
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		if (sprite != null)
			g.drawImage(sprite, x, y, width, height, null);
		else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
}