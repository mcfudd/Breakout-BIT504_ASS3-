import java.awt.Graphics;

public class Brick extends Sprite
{
	private boolean broken = false;
	
	public Brick(int x, int y)
	{
		setX(x); // Set x using the parameter
		setY(y); // Set y using the parameter
		
		// Set the width and height of the brick using Settings.BRICK_WIDTH/HEIGHT
		setWidth(Settings.BRICK_WIDTH);
		setHeight(Settings.BRICK_HEIGHT);
	}

	
	public boolean isBroken()
	{
		return broken;	// Return brick state
	}
	
	
	public void setBroken(boolean b)
	{
		broken = b; // Set the broken variable using the parameter given
	}
	
	
	public void paint(Graphics g)
	{
		if(!broken)
		{
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
