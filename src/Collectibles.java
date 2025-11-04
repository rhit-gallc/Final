import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * Facilitates creation of and management of collectible items in the game.
 * 
 * @authors Tyler Bindel
 * @Reviewers Lizzy Jaynes
 */
public class Collectibles extends Entity {
    private int points;
    private boolean collected;
    private BufferedImage sprite;

    public Collectibles(int x, int y, int points) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.width = 30;
        this.height = 30;
        try {
            sprite = ImageIO.read(new File("SpriteImages\\Banana.png"));
        } catch (IOException e) {
            System.out.println("Collectible image not found!");
        }
    }

    public int getPoints() { return points; }

    public void setCollected(boolean collected) { this.collected = collected; }

    @Override
    public void update() {}

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (!collected) {
            if (sprite != null)
                g.drawImage(sprite, x, y, width, height, null);
            else {
                g.setColor(Color.YELLOW);
                g.fillOval(x, y, width, height);
            }
        }
    }
}

