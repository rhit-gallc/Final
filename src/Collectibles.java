/**
 * Facilitates creation of and management of collectible items in the game.
 * 
 * @authors Tyler Bindel
 * @Reviewers Lizzy Jaynes
 */
public class Collectibles extends GameObject {
	private int points;
	public boolean collected = false;

	public Collectibles(int x, int y, String spritePath) {
		super(x, y, spritePath);
	}

	public int getPoints() {
		return points;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public boolean isCollected() {
		return collected;
	}

	public void collect() {
		collected = true;
	}
}
