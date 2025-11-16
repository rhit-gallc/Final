import java.awt.*;
import javax.swing.*;

/**
 * Main display, handles rendering the level and update loop
 * 
 * @authors Tyler Bindel
 * @Reviewers Lizzy Jaynes
 */
public class Display extends JPanel implements Runnable {
	private Level level;
	private Thread gameThread;

	/**
	 * Builds main display, initializes frame, window, background, and keyboard
	 * listener.
	 */
	public Display() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.CYAN);
		level = new Level("Levels\\level1.txt");
		addKeyListener(new KeyboardListener(level.player, level)); // probably move to main at some point
		setFocusable(true);
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * Main game run loop
	 */
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

	/**
	 * Draws game
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		level.draw(g);
	}

	/**
	 * Main method, sets up game.
	 * 
	 * @param args
	 */
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