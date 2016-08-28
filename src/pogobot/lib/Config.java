package pogobot.lib;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pogobot.main.MainListener;

public class Config {
	

	public static ArrayList<String> config = new ArrayList<String>();

	public Config(){
		checkAndCreateConfig();
		checkAndCreateModList();
		readAndSetConfig(External.getFileFromString("Configuration.txt"));
		readAndSetMods(External.getFileFromString("Mods.txt"));
	}

	public void checkAndCreateConfig(){
		if(!External.getFileFromString("Configuration.txt").exists()){
			try{
				PrintWriter writer = new PrintWriter("Configuration.txt", "UTF-8");
				writer.println("oauth: null");
				writer.println("botname: null");
				writer.println("twitchsrv: irc.twitch.tv");
				writer.println("admin: pogo4545");
				writer.println("amountofwarning: 5");
				writer.println("advertise: false");
				writer.println("timeinbetween: 30");
				writer.println("speedrunner: false");
				writer.println("prefix: !");
				writer.println("access_token: xxxxxxxxxxxxxxxx");
				writer.close();
			}catch(Exception e){

			}
		}
	}

	public void checkAndCreateModList(){
		if(!External.getFileFromString("Mods.txt").exists()){
			try{
				PrintWriter writer = new PrintWriter("Mods.txt", "UTF-8");
				writer.println("pogo4545");
				writer.println("<3");
				writer.close();
			}catch(Exception e){

			}
		}
	}

	public void dumpConfig(){
		for(String s : config){
			System.out.println(s);
		}
	}

	public void readAndSetConfig(File a){
		config = External.getArrayFromFile(a);
		for(int i = 0; i < config.size(); i++){
			String item = config.get(i);
			int colon = item.indexOf(' ') + 1;
			item = item.substring(colon);
			config.set(i, item);
		}

	}
	
	public void readAndSetMods(File a){
		ArrayList<String> mods = External.getArrayFromFile(a);
		for(int i = 0; i < mods.size(); i++){
			Reference.ADMINS.add(mods.get(i));
		}

	}

	public static String getOAUTH(){
		return config.get(0);
	}

	public static String getBotName(){
		return config.get(1);
	}

	public static String getServer(){
		return config.get(2);
	}

	public static String getAdmin(){
		return config.get(3);
	}
	
	public static Boolean isSpeedrunning(){
		return Boolean.valueOf(config.get(7));
	}

	public static int amountOfWarning(){
		try{
			return Integer.parseInt(config.get(4));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "amountofwarning is not an integer!", "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
	}

	public static boolean isAdvertising(){
		return Boolean.valueOf(config.get(5));
	}

	public static int getTimeInbetween(){
		if(isAdvertising()){
			try{
				return Integer.parseInt(config.get(6));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "timeinbetween is not an integer!", "Error", JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		return -1;
	}
	
	public static String getPrefix(){
		return config.get(8);
	}

	public static void remakeAdminsFile() {
		if(External.getFileFromString("Mods.txt").exists()){
			try{
				PrintWriter writer = new PrintWriter("Mods.txt", "UTF-8");
				for(String a : Reference.ADMINS)
				writer.println(a);
				writer.close();
			}catch(Exception e){

			}
		}
		
	}
	
	public static String getAccessToken(){
		if(config.get(9).contains("#disabled#")){
			MainListener.usingAT = false;
		}
		return config.get(9);
	}

}
