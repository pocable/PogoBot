package pogobot.frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pogobot.functions.Raffle;

public class RaffleFrame implements ActionListener{

	public static JFrame x = new JFrame("Raffle");
	public static JFrame ro = new JFrame("Raffle Options");
	static JPanel option = new JPanel();
	static JPanel main = new JPanel(new GridLayout(0,1));
	JButton k = new JButton("Raffle!");
	JLabel l = new JLabel("", SwingConstants.CENTER);

	JCheckBox tstaff = new JCheckBox("Twitch Staff");
	JCheckBox mods = new JCheckBox("Mods");
	JCheckBox viewers = new JCheckBox("Viewers");

	public RaffleFrame(){
		option.removeAll();
		main.removeAll();
		ro.setResizable(false);
		ro.setSize(500, 200);
		ro.setVisible(true);
		ro.setLocationRelativeTo(MainFrame.main);
		x.setResizable(false);
		x.setSize(500, 200);
		x.setVisible(true);
		x.setLocationRelativeTo(ro);

		//Raffle Options
		tstaff.addActionListener(this);
		mods.addActionListener(this);
		viewers.addActionListener(this);
		option.add(tstaff);
		option.add(mods);
		option.add(viewers);
		ro.setContentPane(option);

		//Raffle Window
		k.addActionListener(this);
		main.add(k);
		main.add(l);
		x.setContentPane(main);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(k)){
			Raffle r = new Raffle(tstaff.isSelected(), mods.isSelected(), viewers.isSelected());
			String win = r.getResult();
			if(win.contains("|")){
				win = win.replaceAll("|", " ");
				l.setText("Error: " + win);
			}else{
				l.setText("Congrats " + win + " for winning! Odds: 1/" + r.getOdds());
			}
		}

	}

}
