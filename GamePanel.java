package SnakeEating;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

	// 定义小蛇的数据结构
	public int length;
	public int[] snakeX = new int[600];
	public int[] snakeY = new int[500];
	public String fx;// 小蛇头方向
	
	int foodX,foodY;
	Random random = new Random();
	
	boolean isFail = false;
	
	int score;

	// 游戏当前状态
	public boolean isStart = false;// 默认不开始

	Timer timer = new Timer(100, this);

	public GamePanel() {
		init();
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}

	public void init() {
		// TODO Auto-generated method stub
		length = 3;
		snakeX[0] = 100;
		snakeY[0] = 100;
		snakeX[1] = 75;
		snakeY[1] = 100;
		snakeX[2] = 50;
		snakeY[2] = 100;
		fx = "R";
		
		foodX = 25 + 25 * random.nextInt(34);
		foodY = 75 + 25 * random.nextInt(24);
		
		score = 0;
	}

	// 绘制面板,游戏中所有的东西都是靠这个 Graphics g 来画
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setBackground(Color.blue);
		// 绘制静态面板
		Data.header.paintIcon(this, g, 25, 11);

		g.fillRect(25, 75, 850, 600);
		
		g.setColor(Color.black);
		g.setFont(new Font("TektonPro-BoldObl", Font.ITALIC, 40));
		g.drawString("LENGTH:"+length+"   SCORE:"+score, 500, 50);

		
		Data.food.paintIcon(this, g, foodX, foodY);
		
		switch (fx) {
		case "R":
			Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
		case "L":
			Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
		case "U":
			Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
		case "D":
			Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
		}

		for (int i = 1; i < length; i++) {
			Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
		}
		
		

		if (isStart == false) {
			g.setColor(Color.green);
			g.setFont(new Font("微软雅黑", Font.BOLD, 40));
			g.drawString("按下空格开始游戏", 300, 300);
			g.setColor(Color.black);
		}
		
		if (isFail) {
			g.setColor(Color.red);
			g.setFont(new Font("StencilStd", Font.BOLD, 50));
			g.drawString("YOUR SNAKE IS DEAD !!!", 70, 300);
			g.drawString("Press space to restart.", 70, 400);
			g.setColor(Color.black);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();// 获取当前按下的是哪个键

		if (keyCode == KeyEvent.VK_SPACE) {// 空格键
			if(isFail) {
				isFail = false;
				init();
			}else {
				isStart = !isStart;
				repaint();
			}
		}

		if (keyCode == KeyEvent.VK_UP) {
			fx = "U";
		} else if (keyCode == KeyEvent.VK_DOWN) {
			fx = "D";
		} else if (keyCode == KeyEvent.VK_LEFT) {
			fx = "L";
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			fx = "R";
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isStart && isFail == false) {
			
			if(snakeX[0] == foodX && snakeY[0] == foodY) {
				length++;
				score++;
				foodX = 25 + 25 * random.nextInt(34);
				foodY = 75 + 25 * random.nextInt(24);
			}
			
			
			for (int i = length - 1; i > 0; i--) {
				snakeX[i] = snakeX[i - 1];
				snakeY[i] = snakeY[i - 1];
			}
			if (fx.equals("R")) {
				snakeX[0] = snakeX[0] + 25;
				if (snakeX[0] > 850) {
					snakeX[0] = 25;
				}
			} else if (fx.equals("L")) {
				snakeX[0] = snakeX[0] - 25;
				if (snakeX[0] < 25) {
					snakeX[0] = 850;
				}
			} else if (fx.equals("U")) {
				snakeY[0] = snakeY[0] - 25;
				if (snakeY[0] < 75) {
					snakeY[0] = 650;
				}
			} else if (fx.equals("D")) {
				snakeY[0] = snakeY[0] + 25;
				if (snakeY[0] > 650) {
					snakeY[0] = 75;
				}
			}
			
			for(int i = 1; i<length;i++) {
				if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0])
					isFail = true;
			}

			repaint();
		}
		timer.start();//定时器开启
		

	}
}
