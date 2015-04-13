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
public class Right extends Shape {
    public Right(Point pos) {
        super(pos);
        blocks.add(new Block(new Point(0,-1), RIGHT_COLOR));
        blocks.add(new Block(new Point(0,0), RIGHT_COLOR));
        blocks.add(new Block(new Point(0,1), RIGHT_COLOR));
        blocks.add(new Block(new Point(-1,1), RIGHT_COLOR));
    }
}
