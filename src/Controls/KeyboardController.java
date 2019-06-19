package Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Alex Radermacher
 */
public class KeyboardController implements KeyListener
{
    private boolean[] keyStatus = new boolean[256];
    
    @Override
    public void keyPressed(KeyEvent e) 
    {
       keyStatus[e.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        keyStatus[e.getKeyCode()] = false; 
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // do nothing
    }
    
    /**
     * A method that can be used to determine if a key is being pressed down at 
     * the current time. 
     * @param keyCode an int value representing the key to check. You can either
     * use the corresponding numeric value or use static constants of the KeyEvent
     * class (e.g. KeyEvent.VK_LEFT which represents the left arrow key.) as
     * arguments. 
     * @return Whether or not the key corresponding to the particular keyCode is
     * currently being pressed down. 
     */
    public boolean getKeyStatus(int keyCode)
    {
        return keyStatus[keyCode]; 
    }
    
    /**
     * A method that can be used to clear the current key press status of all keys.
     * This is occasionally necessary if some other component or part of an application
     * is intercepting keyboard input, which causes this listener to not receive
     * keyReleased events which makes it appear as though a key is still being 
     * held down when it is not. 
     */
    public void clearKeyStatus()
    {
        keyStatus = new boolean[256]; 
    }
    
}
