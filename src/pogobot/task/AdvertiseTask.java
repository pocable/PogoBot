package pogobot.task;

import java.util.TimerTask;

import pogobot.functions.Advertisements;

public class AdvertiseTask extends TimerTask{
	Advertisements a;
	
	public AdvertiseTask(Advertisements a){
		this.a = a;
	}

	@Override
	public void run() {
		a.advertise();
	}

}
