import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * Enemy class controls anything we want to be an obstacle for the player to overcome
 * @authors Tyler Bindel
 * @reviewers
 */
public class Enemy extends Entity {
    private BufferedImage sprite;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 70;
        try {
            sprite = ImageIO.read(new File("Mort.png"));
        } catch (IOException e) {
            System.out.println("Enemy image not found!");
        }
    }

    @Override
    public void update() {}

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        if (sprite != null)
            g.drawImage(sprite, x, y, width, height, null);
        else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }
}
