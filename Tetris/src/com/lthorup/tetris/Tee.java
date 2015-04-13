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
public class Tee extends Shape {
    public Tee(Point pos) {
        super(pos);
        blocks.add(new Block(new Point(-1,-1), TEE_COLOR));
        blocks.add(new Block(new Point(0,-1), TEE_COLOR));
        blocks.add(new Block(new Point(1,-1), TEE_COLOR));
        blocks.add(new Block(new Point(0,0), TEE_COLOR));
    }
}
