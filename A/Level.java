import java.awt.Graphics;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is code that defines a level that can be loaded into the game
 * 
 * @author Tyler Bindel
 * @Reviewers
 */
public class Level {
	public Player player;
	public List<Platform> platforms;
	public List<Enemy> enemies;
	public List<Collectibles> blocks;

	public Level(String fileName) {
		platforms = new ArrayList<>();
		enemies = new ArrayList<>();
		blocks = new ArrayList<>();

		loadFromFile(fileName);
		/*
		 * player = new Player(100, 100);
		 * 
		 * platforms.add(new Platform(0, 400, 600, 30)); enemies.add(new Enemy(300,
		 * 330)); blocks.add(new Collectibles(250, 360, 10));
		 */
	}

	public void update() {
		player.update();

		for (Platform p : platforms) {
			player.checkPlatformCollision(p);
		}

		for (Enemy e : enemies) {
			e.update();
		}
		for (Collectibles c : blocks) {
			c.update();
		}
	}

	public void draw(Graphics g) {
		for (Platform p : platforms)
			p.draw(g);
		for (Enemy e : enemies)
			e.draw(g);
		for (Collectibles c : blocks)
			c.draw(g);
		player.draw(g);
	}

	public void loadFromFile(String filename) {
		Scanner scanner = new Scanner(filename);

		// frame size = 600, 450
		int lineNum = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNum += 1;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				int x = i * 30;
				int y = lineNum * 30;

				switch (c) {
				case '_':
					platforms.add(new Platform(x, y, 30, 30));
				case 'e':
					enemies.add(new Enemy(x, y));
				case 'b':
					blocks.add(new Collectibles(x, y, 10));
				case 'p':
					player = new Player(x, y);
				default:
				}

			}
		}
		scanner.close();
	}
}
