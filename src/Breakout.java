import javax.swing.JFrame;

public class Breakout extends JFrame
{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;
	
	public Breakout()
	{
		setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT); // Set the size of the screen (use Settings.WINDOW_WIDTH/HEIGHT)
		setTitle(Settings.WINDOW_NAME); // Set the title
		setBackground(Settings.WINDOW_COLOUR); // Set the background colour to white
		setResizable(false);	// Set resizable to false
		setVisible(true); // Set visible to true
		setDefaultCloseOperation(EXIT_ON_CLOSE); // set behaviours for close button control
		panel = new BreakoutPanel(this); //  create the new panel object
        add(panel); // add the panel object
	}

	public static void main(String[] args)
	{
		new Breakout();	
	}

}
