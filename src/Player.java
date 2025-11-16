import java.awt.Rectangle;
import java.util.List;

/**
 * This is the Player class which helps to control the character you play.
 * Handles player/platform collision, invincibility, and manages player stats.
 * 
 * Extends MovingEntity.
 * 
 * @authors Tyler Bindel, Colton Gall, Ritu Bharamaraddi, Lizzy Jaynes
 * @Reviewers
 */
public class Player extends MovingEntity {
	private int lives = 3;
	private int score = 0;
	private boolean invincible = false;
	private long invincibleStartTime;
	private static final int INVINCIBLE_DURATION = 3000; // 5 seconds in milliseconds

	/**
	 * Creates new player at specified position.
	 * 
	 * @param x          x-coordinate
	 * @param y          y-coordinate
	 * @param spritePath path to sprite image file
	 */
	public Player(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	/**
	 * Handles collisions between player and platform. Fixes player position based
	 * on direction of collision.
	 * 
	 * @param p the platform to check player collision against
	 */
	public void platformCollision(Platform p) {
		Rectangle player = getBounds();
		Rectangle platform = p.getBounds();
		onGround = false;

		// if player is intersecting platform
		if (player.intersects(platform)) {
			// landing on top of platform
			if (yVelocity > 0) {
				y = platform.y - height;
				onGround = true;
				yVelocity = 0;
			}
			// hitting under side of platform
			else if (yVelocity < 0) {
				y = platform.y + platform.height;
				yVelocity = 0;
			}

			// horizontal collisions
			if (!onGround) {
				// hit left side
				if (xVelocity > 0) {
					x = platform.x - width;
					xVelocity = 0;
				}
				// hit right side
				else if (xVelocity < 0) {
					x = platform.x + platform.width;
					xVelocity = 0;
				}
			}
		}
	}

	/**
	 * Resets player position, velocity, movement flags, lives and score.
	 * 
	 * @param startX the x-coordinate to reset to
	 * @param startY the y-coordinate to reset to
	 */
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

	/**
	 * Subtracts one from player life count.
	 */
	public void loseLife() {
		lives--;
		System.out.println("Player lost a life, lives left: " + lives);
	}

	/**
	 * Adds to the players score.
	 * 
	 * @param amount points to add to score
	 */
	public void addScore(int amount) {
		score += amount;
	}

	/**
	 * Returns current player score
	 * 
	 * @return players current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns number of players lives
	 * 
	 * @return returns number of lives player still has
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Returns players invincibilty status
	 * 
	 * @return returns true if player is invincible, false otherwise
	 */
	public boolean isInvincible() {
		if (invincible && System.currentTimeMillis() - invincibleStartTime >= INVINCIBLE_DURATION) {
			invincible = false;
		}
		return invincible;
	}

	/**
	 * Activates invincibilty
	 */
	public void activateInvincibility() {
		invincible = true;
		invincibleStartTime = System.currentTimeMillis();
	}

	/**
	 * Update method
	 */
	@Override
	public void update() {
		this.moving();

	}
}