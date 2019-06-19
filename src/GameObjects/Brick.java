/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Alec Terwilliger
 */
public class Brick extends GameObject {
    
    private int width;
    private int height;
    
    public Brick(int xPosition, int yPosition, int width, int height, Color color)
    {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;
    }

    // creates the bounds of the brick
    @Override
    public Rectangle getBounds() 
    {
        Rectangle r = new Rectangle(xPosition, yPosition, width, height);
        return r;
    }

    @Override
    public void draw(Graphics g) 
    {
        g.setColor(color);
        g.fillRect(xPosition, yPosition, width, height);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;                
    }
    
    public void setWidth(int newWidth)
    {
        width = newWidth;
    }
    
    public void setHeight(int newHeight)
    {
        height = newHeight;
    }
    
}
