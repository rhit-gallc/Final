
import java.awt.*;

/**
 * Heads-Up Display for the game, shows player stats like lives and score
 * @authors Colton Gall
 * @Reviewers
 */
public class Hud {

    private Player player;

    public Hud(Player player) {
        this.player = player;
    }

    public void draw(Graphics g) {
        // Semi-transparent background for HUD
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(10, 10, 160, 60);

        // HUD text
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));

        g.drawString("Score: " + player.getScore(), 20, 35);
        g.drawString("Lives: " + player.getLives(), 20, 60);
    }
}
