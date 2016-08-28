package pogobot.frames;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PollFrame{

	static JPanel k = new JPanel();
	public static int amount = 0;
	public static JFrame x = new JFrame("Poll");

	public PollFrame(){
		x.setResizable(false);
		x.setSize(500, 200);
		x.setVisible(true);
		x.setLocationRelativeTo(MainFrame.main);
		x.setContentPane(k);
	}

	public static void registerPollResults(ArrayList<Integer> votes, String[] items){
		k.removeAll();
		for(int i = 0; i < votes.size(); i++){
			String oldText;
			if(i == votes.size() - 1){
				oldText = items[i] + ": " + votes.get(i);
			}else{
				oldText = items[i] + ": " + votes.get(i) + " | ";
			}
			JLabel repl = new JLabel(oldText);
			k.add(repl);
			x.revalidate();
		}

	}

}
