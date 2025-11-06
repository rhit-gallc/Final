
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {
	private Player player;
	private Level level;

	public KeyboardListener(Player player, Level level) {
		this.player = player;
		this.level = level;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT -> player.movingLeft = true;
		case KeyEvent.VK_RIGHT -> player.movingRight = true;
		case KeyEvent.VK_UP -> player.jumping = true;
		case KeyEvent.VK_SPACE -> level.checkReset();
		case KeyEvent.VK_DOWN -> level.collectItem();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT -> player.movingLeft = false;
		case KeyEvent.VK_RIGHT -> player.movingRight = false;
		case KeyEvent.VK_UP -> player.jumping = false;
		}

	}
}