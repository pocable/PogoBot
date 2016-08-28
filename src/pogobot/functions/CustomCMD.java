package pogobot.functions;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.pircbotx.hooks.types.GenericMessageEvent;

import pogobot.lib.External;
import pogobot.lib.Library;
import pogobot.main.MainListener;

public class CustomCMD {

	public ArrayList<String> commands = new ArrayList<String>();
	public static ArrayList<String> responces = new ArrayList<String>();

	public CustomCMD(){
		checkAndCreateCommands();
		if(External.getFileFromString("Commands.txt").length() == 0){
			System.out.println("Commands Disabled!");
		}else{
			readAndSetCommands(External.getFileFromString("Commands.txt"));
		}
	}

	public void checkAndCreateCommands(){
		if(!External.getFileFromString("Commands.txt").exists()){
			try{
				PrintWriter writer = new PrintWriter("Commands.txt", "UTF-8");
				writer.println("cmd: This is a default command!");
				writer.close();
			}catch(Exception e){

			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void checkCommand(GenericMessageEvent event){
		for(int i = 0; i < commands.size(); i++){
			if(event.getMessage().startsWith(MainListener.prefix + commands.get(i))){
				event.respond(Library.custText(responces.get(i)));
			}
		}
	}

	public void dumpResponces(){
		for(String s : responces){
			System.out.println(s);
		}
	}

	public void readAndSetCommands(File a){
		responces = External.getArrayFromFile(a);
		for(int i = 0; i < responces.size(); i++){
			String item = responces.get(i);
			int colon = item.indexOf(' ') + 1;
			String item2 = item.substring(colon);
			responces.set(i, item2);
			commands.add(i, item.replaceAll(": " + item2, ""));
		}

	}

}
