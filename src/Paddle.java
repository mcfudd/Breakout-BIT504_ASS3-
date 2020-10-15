import java.awt.Graphics;

public class Paddle extends Sprite
{
	private int xVelocity; // declare paddle velocity
	
	
	// set paddle dimensions and calls start location
	public Paddle()
	{
		setWidth(Settings.PADDLE_WIDTH); // Set width to Settings.PADDLE_WIDTH
		setHeight(Settings.PADDLE_HEIGHT); // Set width to Settings.PADDLE_HEIGHT
		resetPosition(); // Call resetPosition
	}
	
	
	// Set initial position x and y
	public void resetPosition()
	{
		setX(Settings.INITIAL_PADDLE_X);
		setY(Settings.INITIAL_PADDLE_Y);
	}
	
	
	// method controlling paddle behaviour and movement
	public void update()
	{
		// update paddle position based on velocity each time method is called
		x += xVelocity; 

		// Prevent the paddle from moving outside of the screen
		if (x <= 0)
			setX(0);
		if (x >= Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH)
			setX(Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH);
	}
	
	
	// draw the paddle graphic in the JFrame when called	
	public void paint(Graphics g)
	{
		g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
	}
	
	
	// paddle velocity setter
	public void setXVelocity(int vel) {	xVelocity = vel; }
}
