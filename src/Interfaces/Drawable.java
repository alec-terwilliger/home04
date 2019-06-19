/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Graphics;

/**
 *
 * @author Alec Terwilliger
 */
public interface Drawable {
    
    /**
     * A draw method takes argument Graphics g
     * @param g
     */
    public abstract void draw(Graphics g);
    
}
