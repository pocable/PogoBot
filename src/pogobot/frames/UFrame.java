package pogobot.frames;

import java.awt.Dimension;

import javax.swing.JFrame;

public class UFrame {
	
	JFrame frame;
	
	public UFrame(String name, Dimension d){
		frame = new JFrame(name);
		frame.setSize(d);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
