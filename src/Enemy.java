/**
 * Enemy class controls anything we want to be an obstacle for the player to
 * overcome, handles enemy movement logic. Extends MovingEntity.
 * 
 * @authors Tyler Bindel, Lizzy Jaynes
 * @reviewers
 */
public class Enemy extends MovingEntity {

	/**
	 * Creates new enemy at specified position.
	 * 
	 * @param x          x-coordinate
	 * @param y          y-coordinate
	 * @param spritePath path to sprite image file
	 */
	public Enemy(int x, int y, String spritePath) {
		super(x, y, spritePath);
		movingRight = true;
		movingLeft = false;
		onGround = true;
	}

	/**
	 * Updates enemies movement based on its current position.
	 */
	@Override
	public void update() {
		if (this.x < 50) {
			movingLeft = false;
			movingRight = true;
		}
		if (this.x > Constants.WIDTH - 50) {
			movingLeft = true;
			movingRight = false;
		}
		moving();
	}
}