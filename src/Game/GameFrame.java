package Game;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Alex Radermacher
 */
public class GameFrame extends JFrame
{
    private Game game;
    
    public GameFrame()
    {
        // Add text to title bar 
        super("Brick Breaker");
        
        // Make sure the program exits when the close button is clicked
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Create an instance of the Game class and turn on double buffering
        //  to ensure smooth animation
        game = new Game(); 
        game.setDoubleBuffered(true);
        
        // Add the Breakout instance to this frame's content pane to display it
        this.getContentPane().add(game); 
        this.pack();
        
        // Start the game
        game.start();  
    }
    
    
    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new GameFrame().setVisible(true);
            }
        });
    }
}
