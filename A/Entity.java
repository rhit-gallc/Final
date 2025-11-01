import java.awt.Rectangle;
/**
 * An abstract class that helps us create Player, Enemy, and Collectible
 * @authors Tyler Bindel
 * @reviewers
 */
public abstract class Entity {
	protected int x, y, width, height;
    protected int xVelocity, yVelocity;
    protected final int GRAVITY = 1;

    public abstract void update();
    public abstract Rectangle getBounds();

    public void applyGravity() {
        yVelocity += GRAVITY;
        y += yVelocity;
    }
}
