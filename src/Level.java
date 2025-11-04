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

/**
 * This is code that defines a level that can be loaded into the game
 * 
 * @author Tyler Bindel, Colton Gall
 * @Reviewers
 */
public class Level {
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
		platforms.add(new Platform(0, 400, 600, 30));
		/*
		 * player = new Player(100, 100); platforms.add(new Platform(0, 400, 600, 30));
		 * enemies.add(new Enemy(300, 330)); blocks.add(new Collectibles(250, 360, 10));
		 */
	}

	public void update() {
		if (gameOver) return;
		player.update();
		
		for (Platform p : platforms) {
			player.checkPlatformCollision(p);
		}

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
		
		if (gameOver) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 600, 450);
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("GAME OVER", 180, 225);
		}
	}
	

	public void loadFromFile() {
		Scanner scanner = null;
		File f = null;
		try {
			f = new File(fileName);
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
		// frame size = 600, 450
		int lineNum = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNum++;
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				int x = i * 30;
				int y = lineNum * 30;

				switch (c) {
				case '_':
					platforms.add(new Platform(x, y, 30, 30));
					break;
				case 'e':
					enemies.add(new Enemy(x, y));
					break;
				case 'b':
					blocks.add(new Collectibles(x, y, 10));
					break;
				case 'p':
					player = new Player(x, y);
					break;
				default:
					break;
				}
			}
		}
		scanner.close();

	}
}
