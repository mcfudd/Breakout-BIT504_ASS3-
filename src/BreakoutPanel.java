import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class BreakoutPanel extends JPanel implements ActionListener, KeyListener
{
	
	static final long serialVersionUID = 2L;

	private boolean gameRunning = false; //  declares and initialises starting game state
	private int livesLeft = 3; // life counter
	private String screenMessage = "Press Enter to Start"; // screen messages variable for win/loss
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	
	public BreakoutPanel(Breakout game)
	{
		addKeyListener(this); // start the key listener
		setFocusable(true); // brings the JFrame to focus for keyboard input
		
		// initialise and start the application timer
		Timer timer = new Timer(5, this);
		timer.start();
		
		ball = new Ball(); // Create a new ball object and assign it to the appropriate variable
		paddle = new Paddle(); // Create a new paddle object and assign it to the appropriate variable
		
		bricks = new Brick[Settings.TOTAL_BRICKS]; // Create a new bricks array (Use Settings.TOTAL_BRICKS)
		createBricks(); // Call the createBricks() method
	}
	
	// populates the bricks array and assigns spatial information for each
	private void createBricks()
	{
		int counter = 0;
		int x_space = 0;
		int y_space = 0;
		for(int x = 0; x < 4; x++)
		{
			for(int y = 0; y < 5; y++)
			{
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++;
				y_space++;
			}
			x_space++;
			y_space = 0;
		}
	}
	
	
	// Loop through and paint the bricks
	private void paintBricks(Graphics g)
	{
		for(Brick currentBrick:bricks)
		{
			currentBrick.paint(g);
		}
	}
	
	
	// main game loop
	private void update()
	{
		if(gameRunning)
		{
			ball.update(); // Update the ball
			paddle.update(); // Update the paddle
			collisions(); // check for collisions
			repaint(); // repaint all sprites
		}
	}
	
	
	// displays the game over message and halts update()
	private void gameOver()
	{
		screenMessage = "GAMEOVER"; // Set screen message
		stopGame();
	}
	
	
	// displays the game win message and halts update()
	private void gameWon()
	{
		screenMessage = "WIN"; // Set screen message
		stopGame();
	}
	
	// halts game update by changing game state variable gameRunning
	private void stopGame()
	{
		gameRunning = false;
	}
	
	
	// ball collision management method which also checks for game win/lose and breaks bricks
	private void collisions()
	{
		// Check for loss
		if(ball.y > 450)
		{
			// Game over
			livesLeft--;
			if(livesLeft <= 0)
			{
				gameOver();
				return;
			}
			else
			{
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
		for(int i = 0; i < bricks.length; i++)
		{
			// Check if there are any bricks left
			if(!bricks[i].isBroken())
			{
				// Brick was found, close loop
				bricksLeft = true;
				break;
			}
		}
		
		if(!bricksLeft)
		{
			gameWon();
			return;
		}
		
		// Check ball/paddle collisions and inverts Y velocity of ball
		if(ball.getRectangle().intersects(paddle.getRectangle()))
		{
			// Simplified touching of paddle
			// Proper game would change angle of ball depending on where it hit the paddle
			ball.setYVelocity(-1);
		}
		
		// loop through all bricks
		for(int i = 0; i < bricks.length; i++)
		{
			// check for current brick/ball collision
			if (ball.getRectangle().intersects(bricks[i].getRectangle()))
			{
				
// ------------------------ ORIGINAL COLLISION MANGAMENT CODE --------------------------------------------------------------------
// Ball was occasionally reversing both velocities when hitting mid brick making it rebound at 180d instead of 90d.  
//				
//				int ballLeft = (int) ball.getRectangle().getMinX();
//	            int ballHeight = (int) ball.getRectangle().getHeight();
//	            int ballWidth = (int) ball.getRectangle().getWidth();
//	            int ballTop = (int) ball.getRectangle().getMinY();
//
//	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
//	            Point pointLeft = new Point(ballLeft - 1, ballTop);
//	            Point pointTop = new Point(ballLeft, ballTop - 1);
//	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
//				
// --------------------------------------------------------------------------------------------------------------------------------	            
	            
	            // Calculate centre of ball which is used below to determine ball's relative position to the brick
	            Point ballCentre = new Point(ball.getX() + (ball.getWidth() / 2), ball.getY() + (ball.getHeight() / 2));
	            
	            if (!bricks[i].isBroken())
	            {
	            	// Collision management statements to determine which directions to reverse when ball hits bricks
	            	if (ballCentre.x < bricks[i].getRectangle().getMinX())
	            		ball.setXVelocity(-1);
	            	if (ballCentre.x > bricks[i].getRectangle().getMaxX())
	            		ball.setXVelocity(1);
	            	if (ballCentre.y < bricks[i].getRectangle().getMinY())
	            		ball.setYVelocity(-1);
	            	if (ballCentre.y > bricks[i].getRectangle().getMaxY())
	            		ball.setYVelocity(1);

	            	
// ------------------------ ORIGINAL COLLISION MANGAMENT CODE --------------------------------------------------------------------
// Ball was occasionally reversing both velocities when hitting mid brick making it rebound at 180d instead of 90d.          	
//	            	
//	                if (bricks[i].getRectangle().contains(pointRight)) {
//	                    ball.setXVelocity(-1);
//	                } else if (bricks[i].getRectangle().contains(pointLeft)) {
//	                    ball.setXVelocity(1);
//	                }
//
//	                if (bricks[i].getRectangle().contains(pointTop)) {
//	                    ball.setYVelocity(1);
//	                } else if (bricks[i].getRectangle().contains(pointBottom)) {
//	                    ball.setYVelocity(-1);
//	                }
//	            	
// --------------------------------------------------------------------------------------------------------------------------------	                
	                	            	
	                bricks[i].setBroken(true); // breaks the brick involved in collision
	            }
			}
		}
	}
	
	// method for printing messages to the JFrame
	public void writeMessage(Graphics g, String message)
	{
    	g.setFont(new Font("Arial", Font.BOLD, 18));
    	int messageWidth = g.getFontMetrics().stringWidth(message);
    	g.drawString(message, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
	}
	
	

	@Override
    public void paintComponent(Graphics g) // paints graphics to JFrame 
	{
        super.paintComponent(g);

        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        
        // Draw lives left
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("LIVES: " + livesLeft, 20, 20);
        
        // Draw screen message
        if(screenMessage != null)
        	writeMessage(g, screenMessage);
	}

	
	@Override
	public void keyPressed(KeyEvent event) // manage key presses
	{
		// moves paddle depending on whether the player is pressing left or right
		if (event.getKeyCode() == KeyEvent.VK_LEFT)
			paddle.setXVelocity(-Settings.PADDLE_SPEED);
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
			paddle.setXVelocity(Settings.PADDLE_SPEED);
	}

	
	@Override
	public void keyReleased(KeyEvent event) // manage key releases
	{
		// stops paddle after the player has released the keys
		if (event.getKeyCode() == KeyEvent.VK_LEFT || event.getKeyCode() == KeyEvent.VK_RIGHT)
			paddle.setXVelocity(0);
		
		// delay game start until 'enter' key is pressed
		if (event.getKeyCode() == KeyEvent.VK_ENTER)
		{
			gameRunning = true; // sets the game state to active
			screenMessage = ""; // clears the delay message
		}
	}

	
	@Override
	public void keyTyped(KeyEvent arg0) // unused, no unicode input required
	{
		
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) // listens for timer and calls update()
	{
		update();
	}

}
