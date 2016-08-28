package pogobot.task;

import java.util.TimerTask;

import pogobot.frames.AnnFrame;

public class AnnClearTask extends TimerTask{

	@Override
	public void run() {
		AnnFrame.announce("");
		AnnFrame.frame.getContentPane().setBackground(AnnFrame.gl);
	}

}
