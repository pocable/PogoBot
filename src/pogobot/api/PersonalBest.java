package pogobot.api;

import java.math.BigDecimal;

import com.google.gson.JsonObject;

public class PersonalBest {
	int position = 0;
	int time = 0;
	String player;
	String niceTime;
	String video;
	String g;
	
	public void load(JsonObject job, String game)
	{
		System.out.println(job);
		position = job.get("place").getAsInt();
		time = job.get("time").getAsInt();
		player = job.get("player").getAsString();
		video = job.get("video").getAsString();
		g = game;
		
		BigDecimal a = new BigDecimal(time);
		int[] time = splitToComponentTimes(a);
		niceTime = time[0] + ":" + time[1] + "." + time[2];
		
	}
	
	
	public static int[] splitToComponentTimes(BigDecimal biggy)
	{
	    long longVal = biggy.longValue();
	    int hours = (int) longVal / 3600;
	    int remainder = (int) longVal - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;

	    int[] ints = {hours , mins , secs};
	    return ints;
	}
	
	public String getNiceTime(){
		return niceTime;
	}
	
	public String getGame(){
		return g;
	}
	
	public String getVideo(){
		return video;
	}
	
	public int getTime(){
		return time;
	}
	
	public String getPlayer(){
		return player;
	}
	
	public int getPosition(){
		return position;
	}

}
