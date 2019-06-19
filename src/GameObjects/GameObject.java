/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Interfaces.Drawable;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author Alec Terwilliger
 */
public abstract class GameObject implements Drawable {
    
    protected int xPosition;
    protected int yPosition;
    protected Color color;
    
    /** For creating game Objects
     *
     * @param xPosition
     * @param yPosition
     * @param color
     */
    public GameObject(int xPosition, int yPosition , Color color)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }
    
    public abstract Rectangle getBounds();
    
    public int getXPosition()
    {
        return xPosition;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setXPosition(int newXPosition)
    {
        xPosition = newXPosition;
    }
    
    public void setYPosition(int newYPosition)
    {
        yPosition = newYPosition;
    }
            
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    public boolean isColliding(GameObject other)
    {
        Rectangle object1Bounds = getBounds();
        Rectangle object2Bounds = other.getBounds();
        return object1Bounds.intersects(object2Bounds);
    }
    
}
