package pogobot.functions;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import pogobot.api.WorldRecords;

public class TimeHandler {
	
	static int[] time;
	static Date d;
	
	public static void setCurrentTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		d = date;
		String timea = dateFormat.format(date);
		String[] beforeconver;
		beforeconver = timea.split(":");
		int h = Integer.parseInt(beforeconver[0]);
		int m = Integer.parseInt(beforeconver[1]);
		int s = Integer.parseInt(beforeconver[2]);
		int[] ctime = {h, m, s};
		time = ctime;
		
	}
	
	public static int[] getCTimeArray(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String timea = dateFormat.format(date);
		String[] beforeconver;
		beforeconver = timea.split(":");
		int h = Integer.parseInt(beforeconver[0]);
		int m = Integer.parseInt(beforeconver[1]);
		int s = Integer.parseInt(beforeconver[2]);
		int[] ctime = {h, m, s};
		return ctime;
	}
	
	@SuppressWarnings("deprecation")
	public static int[] getTimeDifference(){
		int finaltime[];
		int h = time[0];
		int m = time[1];
		int s = time[2];
		int ttime = (((h * 60) + m) * 60) + s;
		int[] otime = getCTimeArray();
		int h1 = otime[0];
		int m1 = otime[1];
		int s1 = otime[2];
		int ottime = (((h1 * 60) + m1) * 60) + s1;
		Date d = new Date();
		if(d.getDate() != d.getDate()){
			int math = (86400 - ttime) + ottime;
			BigDecimal a = new BigDecimal(math);
			finaltime = WorldRecords.splitToComponentTimes(a);
		}else{
			int diff = ottime - ttime ;
			BigDecimal a = new BigDecimal(diff);
			finaltime = WorldRecords.splitToComponentTimes(a);
		}
		return finaltime;
	}

	public static String getUptime() {
		int td[] = getTimeDifference();
		return td[0] + ":" + td[1] + ":" + td[2];
	}

}
