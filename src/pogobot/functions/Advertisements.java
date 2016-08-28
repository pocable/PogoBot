package pogobot.functions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import pogobot.lib.External;
import pogobot.lib.Reference;
import pogobot.main.MainListener;

public class Advertisements {
	Random r = new Random();
	ArrayList<String> ads = new ArrayList<String>();

	public Advertisements(ArrayList<String> ads){
		this.ads = ads;
	}
	
	public void checkAndCreateAdvertisements(){
		if(!External.getFileFromString("Advertisements.txt").exists()){
			try{
			PrintWriter writer = new PrintWriter("Advertisements.txt", "UTF-8");
			writer.println("Each line is its own advertisement!");
			writer.println("YAY!");
			writer.close();
			}catch(Exception e){
				
			}
		}
	}

	public Advertisements(){
		checkAndCreateAdvertisements();
		ads = External.getArrayFromFile(External.getFileFromString("Advertisements.txt"));
	}

	public void advertise(){
		MainListener.bot.sendIRC().message(Reference.LOCATION, ads.get(r.nextInt(ads.size())));
	}

}
