package pogobot.frames;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import pogobot.functions.UpdateCheck;
import pogobot.lib.Reference;
import pogobot.main.MainListener;

public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton stopbot = new JButton("Clear Chat");
	JButton pollbot = new JButton("View Poll");
	JButton advertbot = new JButton("Chat Ad");
	JButton ucpbot = new JButton("UCP Access");
	JButton raff = new JButton("Raffle");
	JButton ann = new JButton("Open Announcement Window");
	//JButton moo = new JButton("URGENT: CALL MOOBOT");
	public static JPanel main = new JPanel();

	public MainFrame(){
		super("PogoBot - Version: " + Reference.VERSION);
		main.add(stopbot);
		stopbot.addActionListener(this);
		main.add(pollbot);
		pollbot.addActionListener(this);
		main.add(advertbot);
		advertbot.addActionListener(this);
		main.add(ucpbot);
		ucpbot.addActionListener(this);
		raff.addActionListener(this);
		main.add(raff);
		ann.addActionListener(this);
		main.add(ann);
		if (UpdateCheck.needsUpdate()) {
			int reply = JOptionPane.showConfirmDialog(this, "New version avalible: " + UpdateCheck.newVer + "\nhttp://pogo4545.ca/\nWould you like to go to the website?", "Updater", JOptionPane.YES_NO_OPTION);
			if(reply == JOptionPane.YES_OPTION){
				try {
					URI u = new URI("http://pogo4545.ca/");
					Desktop.getDesktop().browse(u);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Cannot open webpage", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		//main.add(moo); TODO: This was temp. Will fully remove later
		//moo.addActionListener(this);
		this.setResizable(false);
		this.setSize(500, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(main);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(stopbot)){
			MainListener.bot.sendIRC().message(Reference.LOCATION, ".clear");
			System.out.println("Chat Cleared!");
		}else if(e.getSource().equals(pollbot)){
			new PollFrame();
		}else if(e.getSource().equals(advertbot)){
			if(Reference.ADVERTISE){
				MainListener.a.advertise();
			}else{
				JOptionPane.showMessageDialog(this, "Advertisements are not enabled!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource().equals(ucpbot)){
			new UCPFrame();
		//}else if(e.getSource().equals(moo)){
		//	MainListener.bot.sendIRC().message(Reference.LOCATION, "I have failed... - Bot shutting down");
		//	try {
		//		Desktop.getDesktop().browse(new URI("http://twitch.moobot.tv/pogo4545"));
		//	} catch (IOException e1) {
				// TODO Auto-generated catch block
		//		e1.printStackTrace();
		//	} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
		//		e1.printStackTrace();
		//	}
			//MainListener.disabled = true;
		}else if(e.getSource().equals(ann)){
			new AnnFrame();
		}else if(e.getSource().equals(raff)){
			new RaffleFrame();
		}
	}

}
