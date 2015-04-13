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
public class Block {

    public static int BLOCK_SIZE = 15;

    private Color color;
    private Point pos;

    public Block(Point pos, Color color) {
        this.color = color;
        this.pos = pos;
    }

    public Point absPos(Point origin) {
        return new Point(pos.x+origin.x, pos.y+origin.y);
    }

    public Block posIndepBlock() {
        return new Block(new Point(0,0),color);
    }

    public Block rotated() {
        return new Block(new Point(pos.y, -pos.x), color);
    }

    public void paint(Point shapeOrigin, Graphics g) {
        int x = (shapeOrigin.x + pos.x) * BLOCK_SIZE;
        int y = (shapeOrigin.y + pos.y) * BLOCK_SIZE;
        g.setColor(color);
        g.fillRect(x+1, y+1, BLOCK_SIZE-2, BLOCK_SIZE-2);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x+1, y+1, BLOCK_SIZE-2, BLOCK_SIZE-2);
    }

}
