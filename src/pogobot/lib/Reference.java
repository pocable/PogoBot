package pogobot.lib;

import java.util.ArrayList;

public class Reference {
	
	public static final String VERSION = "0.06";
	public static final String SOAUTH = Config.getAccessToken();
	public static final String OAUTH = Config.getOAUTH();
	public static final String BOTNAME = Config.getBotName();
	public static final String TWITCHSRV = Config.getServer();
	public static final String ADMIN = Config.getAdmin();
	public static final String LOCATION = "#" + ADMIN;
	public static final int AMOUNTOFWARNING = Config.amountOfWarning();
	public static final boolean ADVERTISE = Config.isAdvertising();
	public static final int TIMEINBETWEEN = Config.getTimeInbetween();
	public static final ArrayList<String> ADMINS = new ArrayList<String>();
	
	public static final String UPDATESERVER = "http://api.pogo4545.ca/pogobot/";
	
	public static final Boolean SPEEDRUNNER = Config.isSpeedrunning();
	
}
