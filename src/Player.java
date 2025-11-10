import java.awt.Rectangle;
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
	private boolean invincible = false;
	private long invincibleStartTime;
	private static final int INVINCIBLE_DURATION = 3000; // 5 seconds in milliseconds

	public Player(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	public void platformCollision(Platform p) {
		Rectangle player = getBounds();
		Rectangle platform = p.getBounds();
		onGround = false;
		if (player.intersects(platform)) {
			if (yVelocity > 0) {
				y = platform.y - height;
				onGround = true;
				yVelocity = 0;
			} else if (yVelocity < 0) {
				y = platform.y + platform.height;
				yVelocity = 0;
			}

			if (!onGround) {
				if (xVelocity > 0) {
					x = platform.x - width;
					xVelocity = 0;
				} else if (xVelocity < 0) {
					x = platform.x + platform.width;
					xVelocity = 0;
				}
			}
		}
	}

//	@Override
//
//	public void checkPlatformCollision(List<Platform> platforms) {
//		onGround = false;
//		for (Platform p : platforms) {
//			Rectangle r = getBounds();
//			Rectangle pr = p.getBounds();
//			if (r.intersects(pr)) {
//				if (r.y + r.height <= pr.y + 15) {
//					y = pr.y - height;
//					yVelocity = 0;
//					onGround = true;
//					break;
//				}
//			}
//		}
//	}

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

	@Override
	public void update() {
		this.moving();

	}
}