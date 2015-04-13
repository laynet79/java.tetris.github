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
public class Zig extends Shape {
    public Zig(Point pos) {
        super(pos);
        blocks.add(new Block(new Point(0,-1), ZIG_COLOR));
        blocks.add(new Block(new Point(-1,-1), ZIG_COLOR));
        blocks.add(new Block(new Point(-1,0), ZIG_COLOR));
        blocks.add(new Block(new Point(-2,0), ZIG_COLOR));
    }
}
