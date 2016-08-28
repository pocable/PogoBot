package pogobot.frames;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import pogobot.task.AnnClearTask;

public class AnnFrame{
	
	JPanel c = new JPanel();
    static JTextArea textArea = new JTextArea("", 6, 20);
	public static JFrame frame = new JFrame("Announcement");
	public static Color gl;
	
	public AnnFrame(){
		gl = c.getBackground();
		frame.setSize(1000, 200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		c.add(textArea);
	    textArea.setFont(new Font("Franklin Gothic Heavy", Font.ITALIC, 20));
	    textArea.setForeground(Color.RED);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setOpaque(false);
	    textArea.setEditable(false);
		frame.setContentPane(c);
	}
	
	public static void announceAndKeep(String message){
		textArea.setText(message);
	}
	
	
	public static void announce(String message){
		textArea.setText(message);
		for(int r = gl.getRed(); r < Color.red.getRed(); r++){
			Color abs = new Color(r, gl.getBlue(), gl.getGreen());
			frame.getContentPane().setBackground(abs);
			frame.dispose();
		}
		Timer timer = new Timer();
		timer.schedule(new AnnClearTask(), 20000); //20 Seconds
	}

}
