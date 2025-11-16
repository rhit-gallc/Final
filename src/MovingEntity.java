/**
 * An abstract class for any game object that can move (players, enemies, etc).
 * Handles logic for movement, and gravity.
 * 
 * Extends GameObject.
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

	/**
	 * Creates new moving entity at given position with specified sprite.
	 * 
	 * @param x          initial x-coordinate
	 * @param y          initial y-coordinate
	 * @param spritePath path to sprite image file
	 */
	public MovingEntity(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	/**
	 * Abstract method for updating entities
	 */
	public abstract void update();

	/**
	 * Updates entities movement based on flags specifying direction.
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

	/**
	 * Applies gravity to entity, influencing its yVelocity.
	 */
	public void applyGravity() {
		yVelocity += GRAVITY;
	}
}