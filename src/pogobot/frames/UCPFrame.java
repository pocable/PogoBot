package pogobot.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pogobot.lib.Config;
import pogobot.lib.Reference;
import pogobot.main.MainListener;

public class UCPFrame implements ActionListener{

	public static JFrame ucp = new JFrame("User Control Panel");
	public static JPanel p = new JPanel();
	JLabel info = new JLabel("  *****USER                                                AMOUNT*****    ", JLabel.CENTER);
	JTextField user = new JTextField();
	JTextField amount = new JTextField();
	JButton ban = new JButton("Ban User");
	JButton uban = new JButton("Unban User");
	JButton timeout = new JButton("Timeout User");
	JButton tm = new JButton("Toggle Bot Mod");
	JLabel m = new JLabel("Type in the name above and click a button!", JLabel.CENTER);

	public UCPFrame(){
		p.removeAll();
		ucp.setLocationRelativeTo(MainFrame.main);
		ucp.setSize(400, 400);
		ucp.setResizable(false);
		ucp.setVisible(true);
		p.add(info);

		user.setColumns(15);
		user.setEnabled(true);
		amount.setColumns(15);
		amount.setEnabled(true);
		ban.addActionListener(this);
		uban.addActionListener(this);
		timeout.addActionListener(this);
		tm.addActionListener(this);
		p.add(user);
		p.add(amount);
		p.add(ban);
		p.add(uban);
		p.add(timeout);
		p.add(tm);
		m.setLocation(400 / 2, 400 - 30);
		p.add(m);
		ucp.setContentPane(p);
	}

	public void actionPerformed(ActionEvent e) {
		String a = user.getText();
		if(isInteger(amount.getText())){
			if(e.getSource().equals(ban)){
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".ban " + a);
				m.setText("\"" + a + "\" has been banned!");
			}else if(e.getSource().equals(uban)){
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".unban " + a);
				m.setText("\"" + a + "\" has been unbanned!");
			}else if(e.getSource().equals(timeout)){
				int c = Integer.parseInt(amount.getText());
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".timeout " + a + " " + c);
			}else if(e.getSource().equals(tm)){
				if(!Reference.ADMINS.contains(a)){
					Reference.ADMINS.add(a);
					MainListener.bot.sendIRC().message(Reference.LOCATION, "Added " + a + " to mods!");
				}else{
					Reference.ADMINS.remove(a);
					MainListener.bot.sendIRC().message(Reference.LOCATION, "Removed " + a + " from mods!");
				}
				Config.remakeAdminsFile();
			}
		}
	}

	@SuppressWarnings("unused")
	public static boolean isInteger(String s){
		try{
			if(!s.isEmpty()){
				int i = Integer.parseInt(s);
			}
			return true;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(ucp, "\"" + s + "\" is not an integer!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

}
