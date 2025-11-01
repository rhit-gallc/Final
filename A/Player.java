import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * This is the Player class which helps to control the character you play as and deals 
 * @authors Tyler Bindel
 * @Reviewers
 */

public class Player extends Entity implements KeyListener {
    private int lives = 3;
    private int score = 0;
    private boolean movingLeft, movingRight, jumping, onGround;
    private BufferedImage sprite;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 70;
        try {
            sprite = ImageIO.read(new File("KingJulien.png"));
        } catch (IOException e) {
            System.out.println("Player image not found!");
        }
    }

    @Override
    public void update() {
        if (movingLeft) xVelocity = -5;
        else if (movingRight) xVelocity = 5;
        else xVelocity = 0;

        if (jumping && onGround) {
            yVelocity = -15;
            onGround = false;
        }

        x += xVelocity;
        applyGravity();
    }

    public void checkPlatformCollision(Platform p) {
        if (getBounds().intersects(p.getBounds())) {
            Rectangle r = getBounds();
            Rectangle pr = p.getBounds();
            if (r.y + r.height <= pr.y + 15) { // top collision
                y = pr.y - height;
                yVelocity = 0;
                onGround = true;
            }
        } else {
            onGround = false;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> movingLeft = true;
            case KeyEvent.VK_RIGHT -> movingRight = true;
            case KeyEvent.VK_UP -> jumping = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> movingLeft = false;
            case KeyEvent.VK_RIGHT -> movingRight = false;
            case KeyEvent.VK_UP -> jumping = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void draw(Graphics g) {
        if (sprite != null)
            g.drawImage(sprite, x, y, width, height, null);
        else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
        }
    }
}
