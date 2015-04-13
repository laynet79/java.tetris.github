/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lthorup.tetris;

import java.awt.*;
import java.util.*;

/**
 *
 * @author layne
 */
public class Shape {

    protected static Color CUBE_COLOR = Color.MAGENTA;
    protected static Color BAR_COLOR = Color.RED;
    protected static Color LEFT_COLOR = Color.GRAY;
    protected static Color RIGHT_COLOR = Color.YELLOW;
    protected static Color ZIG_COLOR = Color.BLUE;
    protected static Color ZAG_COLOR = Color.GREEN;
    protected static Color TEE_COLOR = Color.CYAN;

    protected Point pos;
    protected ArrayList<Block> blocks = new ArrayList<Block>();

    public Shape(Point pos) {
        this.pos = pos;
    }

    public boolean rotate(Well well) {
        ArrayList<Block> rotatedBlocks = new ArrayList<Block>();
        for (Block b : blocks)
            rotatedBlocks.add( b.rotated());
        if (validMove(well, pos, rotatedBlocks)) {
            blocks = rotatedBlocks;
            return true;
        }
        return false;
    }

    public boolean move(Well well, int dx, int dy) {
        Point newPos = new Point(pos.x+dx,pos.y+dy);
        if (validMove(well, newPos, blocks)) {
            pos = newPos;
            return true;
        }
        return false;
    }

    public boolean validMove(Well well, Point origin, ArrayList<Block> blks) {
        for (Block b : blks) {
            if (well.occupied(b.absPos(origin)))
                return false;
        }
        return true;
    }

    public void placeInWell(Well well) {
        for (Block b : blocks) {
            Point p = b.absPos(pos);
            well.addBlock(b.posIndepBlock(), p);
        }
    }

    public void paint(Graphics g) {
        for (Block b : blocks)
            b.paint(pos, g);
    }

    public static Shape random(Point pos) {
        Shape s = null;
        int key = (int)(Math.random() * 21);
        switch (key) {
            case 0:
            case 1:
            case 2:
                s = new Cube(pos);
                break;
            case 3:
            case 4:
            case 5:
                s = new Bar(pos);
                break;
            case 6:
            case 7:
            case 8:
                s = new Left(pos);
                break;
            case 9:
            case 10:
            case 11:
                s = new Right(pos);
                break;
            case 12:
            case 13:
            case 14:
                s = new Zig(pos);
                break;
            case 15:
            case 16:
            case 17:
                s = new Zag(pos);
                break;
            case 18:
            case 19:
            case 20:
                s = new Tee(pos);
                break;
        }
        return s;
    }
}
