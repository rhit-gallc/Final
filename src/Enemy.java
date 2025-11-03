import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Enemy class controls anything we want to be an obstacle for the player to
 * overcome
 * 
 * @authors Tyler Bindel
 * @reviewers
 */
public class Enemy extends Entity {
	private BufferedImage sprite;
	private boolean movingLeft, movingRight;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y-20;
		this.width = 30;
		this.height = 30;
		movingRight = true;
		try {
			sprite = ImageIO.read(new File("Mort.png"));
		} catch (IOException e) {
			System.out.println("Enemy image not found!");
		}
	}

	@Override
	public void update() {
		if (movingLeft) {
			this.xVelocity = -5;
			if (this.x < 50) {
				movingLeft = false;
				movingRight = true;
			}
		} else if (movingRight) {
			this.xVelocity = 5;
			if (this.x > 550) {
				movingLeft = true;
				movingRight = false;
			}
		}
		x += xVelocity;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void draw(Graphics g) {
		if (sprite != null)
			g.drawImage(sprite, x, y, width, height, null);
		else {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
		}
	}
}
