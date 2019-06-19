package Game;

import Controls.KeyboardController;
import GameObjects.Ball;
import GameObjects.Brick;
import GameObjects.Paddle;
import com.sun.prism.shader.AlphaTextureDifference_Color_AlphaTest_Loader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author (Alec Terwilliger)
 */
public class Game extends JPanel
{
    // Instance variables declared here
    private KeyboardController  controller; 
    private Brick[][] bricks;
    private Paddle paddle;
    private Ball ball;
    private Rectangle leftEdge;
    private Rectangle rightEdge;
    private Rectangle topEdge, deathEdge;
    private int lives;
    private int numberOfBlackBricks = 0;
    private Integer ballSpeed = new Integer(0);
    private Integer paddleWidth = new Integer(0);
    private int score = 0;
    
    // gets the players score
    
    public int getScore()
    {
        return score;
    }
    
   
    
    public void paint(Graphics g)
    {
        super.paint(g);
        // Draw GameObjects and anything else here
       
        paddle.draw(g);
        
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                bricks[i][j].draw(g);
            }
        }
        ball.draw(g);
        // draws top edge boundary
        g.setColor(Color.red);
        g.fillRect(topEdge.x, topEdge.y, topEdge.width, topEdge.height);
        
        //draws in the score on the game
        g.setColor(Color.WHITE);
        g.drawString("Score: " + this.getScore(), 500, 200);
        
    }
    
    public void updateGameState() {
        // Move GameObjects and check for collisions here
        // check for game win
        boolean gameIsPlaying = true;
       
        // if player turns all bricks to black they win
        if (numberOfBlackBricks >= 15) {
            gameIsPlaying = false;
        }
        if (!gameIsPlaying) {   
            // reset values and ask if they want to play again
            gameIsPlaying = true;    
            numberOfBlackBricks = 0;
            int selection = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "You Won!", JOptionPane.YES_NO_OPTION);
            if (selection == 0) 
            {
                score = 0;
                lives = 3;
                bricks = new Brick[3][5];
                ballSpeed = 0;
                paddleWidth = 0;
                for (int i = 0; i < 3; i++) 
                {
                    for (int j = 0; j < 5; j++) 
                    {
                        if (i == 0) 
                        {
                            bricks[i][j] = new Brick(30 + 110 * (j % 5), 30, 85, 25, Color.BLUE);
                        }
                        if (i == 1) 
                        {
                            bricks[i][j] = new Brick(30 + 110 * (j % 5), 65, 85, 25, Color.GREEN);
                        }
                        if (i == 2) 
                        {
                            bricks[i][j] = new Brick(30 + 110 * (j % 5), 100, 85, 25, Color.ORANGE);
                        }
                    }
                }
                while (!(ballSpeed == 5 || ballSpeed == 10 || ballSpeed == 15)) {            
                ballSpeed = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a speed.\n Easy = 5\n Medium = 10\n hard = 15"));
                }
                while (!(paddleWidth == 150 || paddleWidth == 95 || paddleWidth == 25)) {            
                    paddleWidth = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a paddle width.\n Easy = 150\n Medium = 95\n hard = 25"));
                }
                paddle = new Paddle(290, 725, paddleWidth, 15, 10, Color.red, controller);
                ball = new Ball(295, 600, 25, Color.MAGENTA);
                ball.setXVelocity(-2);
                ball.setYVelocity(-ballSpeed);
            }
            // otherwise close the applet
            else
            {
                System.exit(1);
            }
        }
        ball.move();
        // if the ball collides with something, move it away a little bit and reverse the velocity in the necessary direction
        if (ball.isColliding(paddle)) {
            ball.setYPosition(ball.getYPosition() - 5);
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
        if(ball.getBounds().intersects(topEdge))
        {
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
        if(ball.getBounds().intersects(rightEdge))
        {
            ball.setXPosition(ball.getXPosition() - 5);
            ball.setXVelocity(ball.getXVelocity() * -1);
        }
        if(ball.getBounds().intersects(leftEdge))
        {
            ball.setXPosition(ball.getXPosition() + 5);
            ball.setXVelocity(ball.getXVelocity() * -1);
        }
        // if the ball hits the bottom lose a life and reset ball and paddle
        if(ball.getBounds().intersects(deathEdge))
        {
            JOptionPane.showMessageDialog(null, "You lost a life");
            lives--;
            paddle = new Paddle(290, 725, paddleWidth, 15, 10, Color.red, controller);
            ball = new Ball(295, 600, 25, Color.MAGENTA);
            ball.setXVelocity(-2);
            ball.setYVelocity(-ballSpeed);
            if (lives == 0) 
            {
                JOptionPane.showMessageDialog(null, "You lose");
                ball.setXVelocity(0);
                ball.setYVelocity(0);
                // add ability to play again.
                int selection = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "You Lost!", JOptionPane.YES_NO_OPTION);
                if (selection == 0) 
                {
                    ballSpeed = 0;
                    paddleWidth = 0;
                    lives = 3;
                    bricks = new Brick[3][5];
                    for (int i = 0; i < 3; i++) 
                    {
                        for (int j = 0; j < 5; j++) 
                        {
                            if (i == 0) 
                            {
                                bricks[i][j] = new Brick(30 + 110 * (j % 5), 30, 85, 25, Color.BLUE);
                            }
                            if (i == 1) 
                            {
                                bricks[i][j] = new Brick(30 + 110 * (j % 5), 65, 85, 25, Color.GREEN);
                            }
                            if (i == 2) 
                            {
                                bricks[i][j] = new Brick(30 + 110 * (j % 5), 100, 85, 25, Color.ORANGE);
                            }
                        }
                    }
                    score = 0;
                    // let player choose their difficulty
                    while (!(ballSpeed == 5 || ballSpeed == 10 || ballSpeed == 15)) {            
                    ballSpeed = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a speed.\n Easy = 5\n Medium = 10\n hard = 15"));
                    }
                    while (!(paddleWidth == 150 || paddleWidth == 95 || paddleWidth == 25)) {            
                        paddleWidth = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a paddle width.\n Easy = 150\n Medium = 95\n hard = 25"));
                    }
                    paddle = new Paddle(290, 725, paddleWidth, 15, 10, Color.red, controller);
                    ball = new Ball(295, 600, 25, Color.MAGENTA);
                    ball.setXVelocity(-2);
                    ball.setYVelocity(-ballSpeed);
                }
                else
                {
                    System.exit(1);
                }
            }
        }
        // makes sure the paddle cannot move off screen
        if (!(paddle.getBounds().intersects(rightEdge) || paddle.getBounds().intersects(leftEdge))) 
        {
            paddle.move();
        } 
        else 
        {
            if (paddle.getBounds().intersects(rightEdge))
            {
                paddle.setXPosition(paddle.getXPosition() - 1);
            } 
            else 
            {
                paddle.setXPosition(paddle.getXPosition() + 1);
            }
        }
        // when the player hits a brick, demote the brick to the next lower color and add points
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if (ball.isColliding(bricks[i][j]) && (bricks[i][j].getColor() != Color.BLACK)) 
                {
                    if (ball.getYVelocity() > 0) 
                    {
                        ball.setYPosition(ball.getYPosition() - 1);
                    } 
                    else 
                    {
                        ball.setYPosition(ball.getYPosition() + 1);
                    }
                    ball.setYVelocity(ball.getYVelocity() * -1);
                   
                    if (bricks[i][j].getColor() == Color.BLUE) 
                    {
                        bricks[i][j].setColor(Color.GREEN);
                        score += 1000;
                    } 
                    else if (bricks[i][j].getColor() == Color.GREEN) 
                    {
                        bricks[i][j].setColor(Color.ORANGE);
                        score += 250;
                    } 
                    else if (bricks[i][j].getColor() == Color.ORANGE) 
                    {
                        bricks[i][j].setColor(Color.BLACK);
                        numberOfBlackBricks++;
                        score += 50;
                    }
                }
            }
        }
    }
    
    public final void setupGame() 
    {
        // Instantiate instance variables here
        
        bricks = new Brick[3][5];
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if (i == 0) 
                {
                    bricks[i][j] = new Brick(30 + 110 * (j % 5), 30, 85, 25, Color.BLUE);
                }
                if (i == 1) 
                {
                    bricks[i][j] = new Brick(30 + 110 * (j % 5), 65, 85, 25, Color.GREEN);
                }
                if (i == 2) 
                {
                    bricks[i][j] = new Brick(30 + 110 * (j % 5), 100, 85, 25, Color.ORANGE);
                }
            }
        }
        
        // checks user input for correct speed of ball and width of paddle
        while (!(ballSpeed == 5 || ballSpeed == 10 || ballSpeed == 15)) {
            ballSpeed = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a speed.\n Easy = 5\n Medium = 10\n hard = 15"));
            if ((ballSpeed == 5 || ballSpeed == 10 || ballSpeed == 15)) {
                
            }
            else
            ballSpeed = 10;
        }
        while (!(paddleWidth == 150 || paddleWidth == 95 || paddleWidth == 25)) {            
            paddleWidth = Integer.parseInt(JOptionPane.showInputDialog(this, "Choose a paddle width.\n Easy = 150\n Medium = 95\n hard = 25"));
        }
        
        // creates all the game objects and other necessary variables
        
        paddle = new Paddle(290, 725, paddleWidth, 15, 10, Color.red, controller);
        ball = new Ball(295, 600, 25, Color.MAGENTA);
        ball.setXVelocity(-2);
        ball.setYVelocity(-ballSpeed);
        leftEdge = new Rectangle(0, 0, 2, 800);
        rightEdge = new Rectangle(600, 0, 2, 800);
        topEdge = new Rectangle(0, 0, 600, 2);
        deathEdge = new Rectangle(0, 800, 600, 2);
        lives = 3;
    }
    
    // Constructor method should not be modified
    public Game()
    {
        // Set the size of the Panel to the correct size
        this.setPreferredSize(new Dimension(600, 800));
        
        // Set the background color of the Panel to black
        this.setBackground(Color.BLACK);
        
        // Instantiate a KeyboardController and listen for input with it
        controller = new KeyboardController(); 
        this.addKeyListener(controller);
        
        // Call the setupGame method to initialize instance variables
        this.setupGame();
        
        // Get focus in the window
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    
    // Start method should not be modified
    public void start()
    {
        
        // Set up a new Timer to repeat every 25 milliseconds (40 FPS)
        timer = new Timer(25, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // update the game state and redraw the screen.
                updateGameState();
                repaint();
            }
        });
        
        timer.setRepeats(true);
        timer.start();
    }
    
    Timer timer; 
}
