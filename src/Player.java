import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is the Player class which helps to control the character you play as and
 * deals
 * 
 * @authors Tyler Bindel, Colton Gall, Ritu Bharamaraddi
 * @Reviewers
 */
public class Player extends MovingEntity {
	private int lives = 3;
	private int score = 0;
	// private boolean movingLeft, movingRight, jumping, onGround;
	private boolean onGround;
	private boolean invincible = false;
	private long invincibleStartTime;
	private static final int INVINCIBLE_DURATION = 3000; // 5 seconds in milliseconds


	public Player(int x, int y, int width, int height, String spritePath) {
		super(x, y, width, height, spritePath);
	}

	@Override
	public void update() {
		if (movingLeft)
			xVelocity = -5;
		else if (movingRight)
			xVelocity = 5;
		else
			xVelocity = 0;

		if (jumping && onGround) {
			yVelocity = -15;
			onGround = false;
		}

		x += xVelocity;

		applyGravity();
	}

	public void checkPlatformCollision(List<Platform> platforms) {
		onGround = false;
		for (Platform p : platforms) {
			Rectangle r = getBounds();
			Rectangle pr = p.getBounds();
			if (r.intersects(pr)) {
				if (r.y + r.height <= pr.y + 15) {
					y = pr.y - height;
					yVelocity = 0;
					onGround = true;
					break;
				}
			}
		}
	}
	public void resetPlayer(int startX, int startY) {
		this.x = startX;
		this.y = startY;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.movingLeft = false;
		this.movingRight = false;
		this.jumping = false;
		this.onGround = false;
		this.lives = 3;
		this.score = 0;
	}

	public void loseLife() {
		lives--;
		System.out.println("Player lost a life, lives left: " + lives);
	}
	
	public void addScore(int amount) {
	    score += amount;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}
	
	public boolean isInvincible() {
	    if (invincible && System.currentTimeMillis() - invincibleStartTime >= INVINCIBLE_DURATION) {
	        invincible = false;
	    }
	    return invincible;
	}

	public void activateInvincibility() {
	    invincible = true;
	    invincibleStartTime = System.currentTimeMillis();
	}
}