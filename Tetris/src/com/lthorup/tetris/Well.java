package com.lthorup.tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Well extends JPanel {
	
	static final long serialVersionUID = 1;
	
    public static int ROWS = 24;
    public static int COLS = 8;
    private ArrayList<Block[]> rows = new ArrayList<Block[]>();
    private Shape shape = null;
    private boolean initialized = false;

	/**
	 * Create the panel.
	 */
	public Well() {
	}

    public void newGame() {
        rows.clear();
        for (int r = 0; r < ROWS; r++) {
            rows.add(new Block[COLS]);
        }
        initialized = true;
    }

    public int update(Shape shape) {
        this.shape = shape;
        ArrayList<Block[]> fullRows = new ArrayList<Block[]>();
        for (Block[] row : rows) {
            int cnt = 0;
            for (int i = 0; i < COLS; i++) {
                if (row[i] != null)
                    cnt++;
            }
            if (cnt == COLS)
                fullRows.add(row);
        }
        int score = 0;
        for (Block[] row : fullRows) {
            rows.remove(row);
            rows.add(0, new Block[COLS]);
            score += COLS;
        }
        repaint();
        return score;
    }

    public void addBlock(Block b, Point pos) {
        System.out.printf("adding block (%d,%d)\n", pos.x, pos.y);
        rows.get(pos.y)[pos.x] = b;
    }

    public boolean occupied(Point pos) {
        if (pos.x < 0 || pos.x >= COLS || pos.y < 0 || pos.y >= ROWS)
            return true;
        return rows.get(pos.y)[pos.x] != null;
    }

    @Override
    public void paint(Graphics g) {
    	if (! initialized)
    		newGame();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (shape != null)
            shape.paint(g);
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++)
                if (rows.get(r)[c] != null)
                    rows.get(r)[c].paint(new Point(c,r), g);
        }
    }
}
