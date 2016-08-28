package pogobot.api;

import java.math.BigDecimal;

import com.google.gson.JsonObject;

public class WorldRecords {
	
	String ms;
	int time;
	String niceTime;
	
	public void load(JsonObject job, String category)
	{
		JsonObject o = job.get(category).getAsJsonObject();
		ms = o.get("player").toString();
		time = o.get("time").getAsInt();
		
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
	
	public String getPlayer(){
		return ms;
	}
	
	public int getTime(){
		return time;
	}
	
	public String getNiceTime(){
		return niceTime;
	}

}
