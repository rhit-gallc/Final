import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is code that defines a level that can be loaded into the game *
 * 
 * @author Tyler Bindel, Colton Gall, Ritu Bharamaraddi
 * @Reviewers
 */
public class Level {
	protected Player player;
	protected List<Platform> platforms = new ArrayList<>();
	protected List<Enemy> enemies = new ArrayList<>();
	protected List<Collectibles> blocks = new ArrayList<>();
	private Hud hud;
	private boolean gameOver = false;
	private boolean gameWon = false;
	String fileName;

	public Level(String fileName) {
		this.fileName = fileName;
		loadFromFile();
		hud = new Hud(player);
	}

	public void update() {
		if (gameOver || gameWon)
			return;
		player.update();

		for (Platform p : platforms) {
			player.platformCollision(p);

			if (player.onGround) {
				break;
			}
		}

		for (Enemy e : enemies) {
			e.update();
			if (player.getBounds().intersects(e.getBounds())) {
				if (!player.isInvincible()) {
					player.loseLife();
					player.activateInvincibility();
				}
				if (player.getLives() <= 0) {
					gameOver = true;
					System.out.println("Game Over!");
				}
				break;
			}
		}

		boolean allCollected = true;

		for (Collectibles c : blocks) {
			if (!c.isCollected()) {
				allCollected = false;
			}
		}

		if (allCollected) {
			gameWon = true;
			System.out.println("You won!");
		}
	}

	public void draw(Graphics g) {
		for (Platform p : platforms) {
			p.draw(g);
		}
		for (Enemy e : enemies) {
			e.draw(g);
		}
		for (Collectibles c : blocks) {
			if (c.collected)
				continue;
			c.draw(g);
		}
		player.draw(g);
		hud.draw(g);

		if (gameOver) {
			gameLost(g);
		}
		if (gameWon) {
			gameWon(g);
		}
	}

	public void loadFromFile() {
		enemies.clear();
		blocks.clear();
		Scanner scanner = null;
		File f = null;
		try {
			f = new File(fileName);
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		// frame size is 600, 450
		int lineNum = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNum++;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				int x = i * 40;
				int y = lineNum * 40;

				switch (c) {
				case '_':
					platforms.add(new Platform(x, y, "SpriteImages\\Platform.png"));
					break;
				case 'e':
					enemies.add(new Enemy(x, y, "SpriteImages\\Mort.png"));
					break;
				case 'b':
					blocks.add(new Collectibles(x, y, "SpriteImages\\Banana.png"));
					break;
				case 'p':
					if (player != null) {
						player.resetPlayer(x, y);
					} else {
						player = new Player(x, y, "SpriteImages\\KingJulien.png");
					}
					break;
				default:
					break;
				}
			}
		}
		scanner.close();
	}

	public void resetLevel() {
		enemies.clear();
		blocks.clear();
		loadFromFile();
		hud = new Hud(player);
		gameOver = false;
		gameWon = false;

		for (Platform p : platforms) {
			player.platformCollision(p);
		}
	}

	public void checkReset() {
		if (gameOver) {
			resetLevel();
		}
	}

	public void collectItem() {
		for (Collectibles c : blocks) {
			if (!c.collected && player.getBounds().intersects(c.getBounds())) {
				c.setCollected(true);
				player.addScore(1);
				break;
			}
		}
	}

	public void gameWon(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("YOU WIN!", Constants.WIDTH / 3, 215);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press SPACE to play again", Constants.WIDTH / 3, 325);
	}

	public void gameLost(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("GAME OVER", Constants.WIDTH / 3, 215); // formerly 175, 215 for width 600 and height 450
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press SPACE to play again", Constants.WIDTH / 3, 325); // formerly 175, 325 for width 600 and
																				// height 450
	}

}
