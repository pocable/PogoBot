package pogobot.functions;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import pogobot.lib.External;

public class Customization {
	
	public static HashMap<String, String> config = new HashMap<String, String>();

	public Customization(){
		checkAndCreateC();
		readAndSetC(External.getFileFromString("CText.txt"));
	}

	public void checkAndCreateC(){
		if(!External.getFileFromString("CText.txt").exists()){
			try{
				PrintWriter writer = new PrintWriter("CText.txt", "UTF-8");
				writer.println("warning_badwords: Please avoid using bad words!");
				writer.println("warning_badsymbol: Please avoid using symbols!");
				writer.println("world_record: World Record for %game% %brl%%category%%brr% is held by %player% with a time of %time%!");
				writer.println("personal_best: Personal best for %pbplayer% in %pbgame% %brl%%category%%brr% is %pbtime%");
				writer.close();
			}catch(Exception e){

			}
		}
	}
	
	
	public String getData(String key){
		return config.get(key);
	}
	
	public void readAndSetC(File a){
		ArrayList<String> c = External.getArrayFromFile(a);
		for(int i = 0; i < c.size(); i++){
			String item = c.get(i);
			int colon = item.indexOf(' ') + 1;
			item = item.substring(colon);
			String beforesub = c.get(i).replaceAll(": " + item, "");
			config.put(beforesub, item);
		}

	}
	
}
