/**
 * Enemy class controls anything we want to be an obstacle for the player to
 * overcome
 * 
 * @authors Tyler Bindel, Lizzy Jaynes
 * @reviewers
 */
public class Enemy extends MovingEntity {

	public Enemy(int x, int y, String spritePath) {
		super(x, y, spritePath);
		movingRight = true;
		movingLeft = false;
		onGround = true;
	}

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