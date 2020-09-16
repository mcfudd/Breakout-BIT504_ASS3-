import java.awt.Color;

import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;
	
	public Breakout() {
		// TODO: Set the size of the screen (use Settings.WINDOW_WIDTH/HEIGHT)
		// TODO: Set the title
		// TODO: Set the background colour to white
		// TODO: Set resizable to false
		// TODO: Set visible to true
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BreakoutPanel(this);
        add(panel);
	}

	public static void main(String[] args) {
		new Breakout();	
	}

}
