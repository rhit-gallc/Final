import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.color.*;
import java.awt.font.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * This is code that defines a level that can be loaded into the game
 * 
 * @author Tyler Bindel, Colton Gall, Ritu Bharamaraddi
 * @Reviewers
 */
public class Level {
	int width = 800;
	int height = 600;
	final int sizeVal = 40;
	public Player player;
	public List<Platform> platforms = new ArrayList<>();
	public List<Enemy> enemies = new ArrayList<>();
	public List<Collectibles> blocks = new ArrayList<>();
	private Hud hud;
	private boolean gameOver = false;
	String fileName;

	public Level(String fileName) {
		this.fileName = fileName;
		loadFromFile();
		hud = new Hud(player);
	}

	public void update() {
		if (gameOver)
			return;
		player.update();

		player.checkPlatformCollision(platforms);

		for (Enemy e : enemies) {
			e.update();
			if (player.getBounds().intersects(e.getBounds())) {
				player.loseLife();
				if (player.getLives() <= 0) {
					gameOver = true;
					System.out.println("Game Over!");
				}
				break;
			}
		}

		for (Collectibles c : blocks) {
			c.update();
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
			c.draw(g);
		}
		player.draw(g);
		hud.draw(g);
//600, 450
		if (gameOver) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER", width / 3, 215); // formerly 175, 215 for width 600 and height 450
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Press SPACE to play again", width / 3, 325); // formerly 175, 325 for width 600 and height 450
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
					platforms.add(new Platform(x, y, 40, 40, "SpriteImages\\Platform.png"));
					break;
				case 'e':
					enemies.add(new Enemy(x, y, 40, 40, line));
					break;
				case 'b':
					blocks.add(new Collectibles(x, y, 40, 40, "SpriteImages\\Banana.png"));
					break;
				case 'p':
					if (player != null) {
						player.resetPlayer(x, y);
					} else {
						player = new Player(x, y, 40, 40, "SpriteImages\\KingJulien.png");
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
		for (Platform p : platforms) {
			player.checkPlatformCollision(platforms);
		}
	}

	public void checkReset() {
		if (gameOver) {
			resetLevel();
		}
	}
	/*
	 * @Override public void keyPressed(KeyEvent e) { if (gameOver && e.getKeyCode()
	 * == KeyEvent.VK_SPACE) { resetLevel(); } else { player.keyPressed(e); } }
	 * 
	 * @Override public void keyReleased(KeyEvent e) { player.keyReleased(e); }
	 * 
	 * @Override public void keyTyped(KeyEvent e) { }
	 */
}
