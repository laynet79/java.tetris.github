/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lthorup.tetris;

import java.awt.*;

/**
 *
 * @author layne
 */
public class Left extends Shape {
    public Left(Point pos) {
        super(pos);
        blocks.add(new Block(new Point(-1,-1), LEFT_COLOR));
        blocks.add(new Block(new Point(-1,0), LEFT_COLOR));
        blocks.add(new Block(new Point(-1,1), LEFT_COLOR));
        blocks.add(new Block(new Point(0,1), LEFT_COLOR));        
    }
}
