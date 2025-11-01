import java.awt.*;
/**
 * Represents something an entity may stand on during the game
 * @authors Tyler Bindel
 * @reviewers
 */
public class Platform extends Entity {

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {}

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
    }
}
