import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
/**
 * This is code that defines a level that can be loaded into the game
 * @author Tyler Bindel
 * @Reviewers
 */
public class Level {
    public Player player;
    public List<Platform> platforms;
    public List<Enemy> enemies;
    public List<Collectibles> blocks;

    public Level() {
        player = new Player(100, 100);

        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        blocks = new ArrayList<>();

        platforms.add(new Platform(0, 400, 600, 30));
        enemies.add(new Enemy(300, 330));
        blocks.add(new Collectibles(250, 360, 10));
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
        for (Platform p : platforms) p.draw(g);
        for (Enemy e : enemies) e.draw(g);
        for (Collectibles c : blocks) c.draw(g);
        player.draw(g);
    }
}
