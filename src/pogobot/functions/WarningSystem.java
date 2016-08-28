package pogobot.functions;

import java.util.HashMap;

import org.pircbotx.PircBotX;

import pogobot.lib.Reference;

public class WarningSystem {

	public HashMap<String, Integer> warns = new HashMap<String, Integer>();
	PircBotX bot;
	public WarningSystem(PircBotX b){
		bot = b;
	}

	public void warnPlayer(String name, String reason){
		if(warns.get(name) == null){
			warns.put(name, 0);
		}
		String name2 = name;
		int amountOfWarnings = warns.get(name);
		warns.put(name, (amountOfWarnings += 1));
		if(reason.contains("([null])")){
			if(warns.get(name) >= Reference.AMOUNTOFWARNING){
				bot.sendIRC().message(Reference.LOCATION, ".ban " + name2);
			}
		}else{
			bot.sendIRC().message(Reference.LOCATION, "{" + name2 + "} " + reason + " (Warning: " + amountOfWarnings + "/" + Reference.AMOUNTOFWARNING + ")");
			if(warns.get(name) >= Reference.AMOUNTOFWARNING){
				bot.sendIRC().message(Reference.LOCATION, "{" + name2 + "} " + " You have been banned for going over the warning limit.");
				bot.sendIRC().message(Reference.LOCATION, ".ban " + name2);
			}
		}
	}

}
