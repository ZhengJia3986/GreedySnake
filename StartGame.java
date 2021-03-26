package SnakeEating;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class StartGame {
	// 游戏的主启动类
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.add(new GamePanel());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(10, 10, 918, 740);
		
		

	}

}
