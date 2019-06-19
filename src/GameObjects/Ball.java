/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Interfaces.Moveable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Alec Terwilliger
 */
public class Ball extends GameObject implements Moveable{
    
    private int diameter;
    private int xVelocity;
    private int yVelocity;
    
    public Ball(int xPosition, int yPosition, int diameter, Color color)
    {
        super(xPosition , yPosition , color);
        this.diameter = diameter;
    }
    // creates the bounds of the ball
    @Override
    public Rectangle getBounds()
    {
        Rectangle ballHitBox = new Rectangle(xPosition,yPosition,diameter, diameter);
        return ballHitBox;
    }
    
    @Override
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(xPosition, yPosition, diameter, diameter);
    }

    @Override
    public void move()
    {
        xPosition += xVelocity;
        yPosition += yVelocity;
    }
    
    public int getXVelocity()
    {
        return xVelocity;
    }
    
    public int getYVelocity()
    {
        return yVelocity;
    }
    
    public int getDiameter()
    {
        return diameter;
    }
    
    public void setXVelocity(int newXVelocity)
    {
        xVelocity = newXVelocity;
    }
    
    public void setYVelocity(int newYVelocity)
    {
        yVelocity = newYVelocity;
    }
    
    
}
