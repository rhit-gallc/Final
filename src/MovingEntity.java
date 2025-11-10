/**
 * An abstract class that helps us create Player, Enemy, and Collectible
 * 
 * @authors Tyler Bindel, Lizzy Jaynes
 * @reviewers
 */
public abstract class MovingEntity extends GameObject {
	protected int xVelocity;
	protected int yVelocity;
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean jumping;
	protected boolean onGround;
	protected final int GRAVITY = 1;

	public MovingEntity(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	public abstract void update();

	/**
	 * method to update movement of entities based off direction they are moving
	 */
	public void moving() {
		// horizontal movement logic
		if (movingLeft) {
			xVelocity = -5;
		} else if (movingRight) {
			xVelocity = 5;
		} else {
			xVelocity = 0;
		}

		// vertical movement logic
		if (jumping && onGround) {
			yVelocity = -15;
			onGround = false;
		}
		if (!onGround) {
			applyGravity();
		}
		x += xVelocity;
		y += yVelocity;
	}

	public void applyGravity() {
		yVelocity += GRAVITY;
	}
}