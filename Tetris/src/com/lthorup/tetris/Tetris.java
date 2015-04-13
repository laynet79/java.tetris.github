package com.lthorup.tetris;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Tetris extends JFrame {
	
	static final long serialVersionUID = 1;

	private JPanel contentPane;
	private JTextField scoreText;
	private JTextField statusText;

    private Well well;
    private boolean gameOver;
    private int ticksPerMove;
    private int tickCnt;
    private boolean dropping;
    private Shape shape = null;
    private KeyBoard keys;
    private boolean newGameReq;
    private int score;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tetris frame = new Tetris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tetris() {
		setResizable(false);
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		well = new Well();
		well.setFocusable(false);
		well.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		well.setBounds(17, 16, 120, 360);
		contentPane.add(well);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFocusable(false);
		lblScore.setBounds(174, 22, 61, 16);
		contentPane.add(lblScore);
		
		scoreText = new JTextField();
		scoreText.setFocusable(false);
		scoreText.setEditable(false);
		scoreText.setBounds(220, 16, 95, 28);
		contentPane.add(scoreText);
		scoreText.setColumns(10);
		
		statusText = new JTextField();
		statusText.setFocusable(false);
		statusText.setEditable(false);
		statusText.setBounds(220, 50, 95, 28);
		contentPane.add(statusText);
		statusText.setColumns(10);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFocusable(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        newGameReq = true;
		    }
		});
		btnNewGame.setBounds(198, 111, 117, 29);
		contentPane.add(btnNewGame);
		
        keys = new KeyBoard();
        addKeyListener(keys);
        newGameReq = true;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() { public void run() { update(); } }, 0, 20);
	}

    private void newGame() {
        gameOver = true;
        ticksPerMove = 50;
        tickCnt = 0;
        dropping = false;
        shape = null;
        score = 0;
        well.newGame();
        gameOver = false;
        newGameReq = false;
        statusText.setText("Playing");
    }

    private void update() {
        if (newGameReq)
            newGame();

        if (gameOver)
            return;

        if (shape == null)
        {
            shape = Shape.random(new Point(Well.COLS/2, 2));
            if (! shape.move(well, 0, 0)) {
                gameOver = true;
                statusText.setText("Game Over");
            }
        }

        keys.update();
        if (keys.keyJustPressed(KeyEvent.VK_UP))
            shape.rotate(well);
        if (keys.keyJustPressed(KeyEvent.VK_DOWN))
            dropping = true;
        if (keys.keyJustPressed(KeyEvent.VK_LEFT))
            shape.move(well, -1, 0);
        if (keys.keyJustPressed(KeyEvent.VK_RIGHT))
            shape.move(well, 1, 0);

        tickCnt++;
        if (dropping || tickCnt >= ticksPerMove) {
            tickCnt = 0;
            if (! shape.move(well, 0, 1)) {
                shape.placeInWell(well);
                shape = null;
                dropping = false;
            }
        }
        score += well.update(shape);
        scoreText.setText(String.valueOf(score));
        adjustRate();
    }

    private void adjustRate() {
        if (score > 400)
            ticksPerMove = 3;
        else if (score > 300)
            ticksPerMove = 6;
        else if (score > 200)
            ticksPerMove = 12;
        else if (score > 100)
            ticksPerMove = 25;
    }
}
