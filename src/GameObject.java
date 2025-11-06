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

	public GameObject(int x, int y, int width, int height, String spritePath) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.spritePath = spritePath;

		loadSprite(spritePath);
	}

	public void loadSprite(String spritePath) {
		try {
			sprite = ImageIO.read(new File(spritePath));
		} catch (IOException e) {
			System.out.println("Image not found!");
		}
	}

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
