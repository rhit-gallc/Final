
import java.awt.*;
import javax.swing.*;
/**
 * The class that puts the game on the screen
 * @authors Tyler Bindel
 * @Reviewers
 */
public class Display extends JPanel implements Runnable {
    private Level level;
    private Thread gameThread;

    public Display() {
        setPreferredSize(new Dimension(600, 450));
        setBackground(Color.CYAN);
        level = new Level("Levels\\level1.txt");
        addKeyListener(level.player);
//        addKeyListener(level);  // this is making the platform disappear when you restart after hitting the space bar
        setFocusable(true);
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (true) {
            level.update();
            repaint();
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        level.draw(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("King Julien Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new Display());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}