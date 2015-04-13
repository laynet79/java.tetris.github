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
public class Bar extends Shape {
    public Bar(Point pos) {
        super(pos);
        blocks.add(new Block(new Point(0,-2), BAR_COLOR));
        blocks.add(new Block(new Point(0,-1), BAR_COLOR));
        blocks.add(new Block(new Point(0,0), BAR_COLOR));
        blocks.add(new Block(new Point(0,1), BAR_COLOR));
    }
}
