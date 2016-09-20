
package main;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
public class Main{

	public static double BOX_WIDTH = 1000, BOX_HEIGHT = 500, UPDATE_RATE = 90, SHURIKEN_SPEED = .012, ROTATION_SPEED = 10;
	public static int UP = 1, MIDDLE = 2, DOWN = 3, DEFAULT_INSIDE_HP_WIDTH = 300;
	protected static JFrame frame = new JFrame("Platformer");

	public static void main(String[] args) 
	{

		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				frame.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());


				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				frame.setResizable(false);
				frame.setUndecorated(false);
				try {
					frame.setContentPane(new PrintScreen());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(true);
				frame.pack();

/*				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						Point p = new Point(0, 0);
						SwingUtilities.convertPointToScreen(p, frame.getContentPane());
						Point l = frame.getLocation();
						l.x -= p.x;
						l.y -= p.y;
						frame.setLocation(l);

					}
				});*/

			}
		});

	}


}
