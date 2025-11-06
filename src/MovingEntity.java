import java.awt.Rectangle;
/**
 * An abstract class that helps us create Player, Enemy, and Collectible
 * @authors Tyler Bindel
 * @reviewers
 */
public abstract class MovingEntity extends GameObject {
	protected int xVelocity;
	protected int yVelocity;
	protected final int GRAVITY = 1;
	public MovingEntity(int x, int y, int width, int height, String spritePath) {
		super(x, y, width, height, spritePath);
	}
    public abstract void update();

    public void applyGravity() {
        yVelocity += GRAVITY;
        y += yVelocity;
    }
}
