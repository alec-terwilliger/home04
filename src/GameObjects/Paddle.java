/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Controls.KeyboardController;
import Interfaces.Moveable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Alec Terwilliger
 */
public class Paddle extends GameObject implements Moveable {
    
    private int width,height,speed;
    private KeyboardController controller;
    
    public Paddle(int xPosition, int yPosition, int width, int height, int speed, Color color, KeyboardController controller)
    {
        super(xPosition, yPosition, color);
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.controller = controller;
    }

    @Override
    public Rectangle getBounds() {
        Rectangle r = new Rectangle(xPosition,yPosition,width, height);
        return r;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xPosition, yPosition, width, height);
    }

    @Override
    public void move() {
        if(controller.getKeyStatus(KeyEvent.VK_RIGHT))
        {
            xPosition += speed;
        }
        if(controller.getKeyStatus(KeyEvent.VK_LEFT))
        {
            xPosition -= speed;
        }
        
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void setWidth(int newWidth)
    {
        width = newWidth;
    }
    
    public void setHeight(int newHeight)
    {
        height = newHeight;
    }
    
    public void setSpeed(int newSpeed)
    {
        speed = newSpeed;
    }
}
