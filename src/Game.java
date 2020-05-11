import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Game {
	
	private Board b;
	private JFrame frame;
	private boolean won;
	
	public Game() {
		frame = new JFrame();
		frame.setSize(700, 700);
		b = new Board(4, 4, frame.getWidth(), frame.getHeight());
		b.setVisible(true);
		frame.add(b);
		frame.addKeyListener(b);
		frame.setVisible(true);
		won = false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		while (!g.won) {
			if (g.b.isSolved()) {
				System.out.println("Winner");
				g.won = true;
			}
		}
		g.frame.dispatchEvent(new WindowEvent(g.frame, WindowEvent.WINDOW_CLOSING));
		System.exit(0);
	}
}
