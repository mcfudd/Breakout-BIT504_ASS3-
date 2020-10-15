import java.awt.Graphics;

public class Brick extends Sprite
{
	private boolean broken = false; // set initial brick state
	
	
	// set individual brick position and size
	public Brick(int x, int y)
	{
		setX(x); // Set x using the parameter
		setY(y); // Set y using the parameter
		
		// Set the width and height of the brick using Settings.BRICK_WIDTH/HEIGHT
		setWidth(Settings.BRICK_WIDTH);
		setHeight(Settings.BRICK_HEIGHT);
	}

	
	// check to see if a brick state is broken
	public boolean isBroken() { return broken; }// getter
	
	
	// change state of brick to broken when called
	public void setBroken(boolean b) { broken = b; } // setter
	
	
	// paint the brick graphic in the JFrame when called
	public void paint(Graphics g)
	{
		if(!broken) // controls which bricks are visible broken/unbroken
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
	}
}
