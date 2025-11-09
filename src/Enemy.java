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
public class Enemy extends MovingEntity {
	private boolean movingLeft, movingRight;

	public Enemy(int x, int y, int width, int height, String spritePath) {
		super(x, y, width, height, spritePath);
		movingRight = true;
		movingLeft = false;
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
			if (this.x > Constants.WIDTH - 50) {
				movingLeft = true;
				movingRight = false;
			}
		}
		x += xVelocity;
	}
}