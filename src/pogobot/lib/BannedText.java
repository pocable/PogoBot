package pogobot.lib;

import java.util.ArrayList;

import org.pircbotx.hooks.types.GenericMessageEvent;

import com.google.common.base.CharMatcher;

import pogobot.functions.WarningSystem;
import pogobot.main.MainListener;

public class BannedText {

	public ArrayList<String> badWords = new ArrayList<String>();
	public ArrayList<Character> badChars = new ArrayList<Character>();

	@SuppressWarnings("unused")
	public void checkText(@SuppressWarnings("rawtypes") GenericMessageEvent event, WarningSystem w){
		String message = event.getMessage();
		char[] a = message.toCharArray();
		boolean warned = false;
		for(String s : badWords){
			if(message.toLowerCase().contains(s.toLowerCase())){
				w.warnPlayer(event.getUser().getNick(), Library.c.getData("warning_badwords"));
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".timeout " + event.getUser().getNick() + " 1");
				warned = true;
				break;
			}
		}
		
		if(event.getMessage().contains("[^\\p{L}^\\d]"))
			event.respond("BAD CHAR");

		for(char c : event.getMessage().toCharArray()){
			if(!CharMatcher.ASCII.matches(c)){
				if(warned)
					break;
				w.warnPlayer(event.getUser().getNick(), Library.c.getData("warning_badsymbol"));
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".timeout " + event.getUser().getNick() + " 1");
				break;
			}
		}

		int capital = 0;
		int symbol = 0;
		int small = 0;
		for(char c : a){
			int i = (int) c;
			if(i >= 65 && i <= 90 ){
				capital += 1;
			}else if(i >= 97 && i <= 122){
				small += 1;
			}else{
				symbol += 1;
			}
		}

		if(capital > (message.length()/2)){
			if(!warned){
				w.warnPlayer(event.getUser().getNick(), "It seems that you have too many capitals. Let me take some from you...");
				MainListener.bot.sendIRC().message(Reference.LOCATION, ".timeout " + event.getUser().getNick() + " 1");
			}
		}
	}


	/**
	 * Old system used when this bot was private.
	 */
	public BannedText(){
		//for(int i = 128; i <= 255; i++){
		//	char a = (char)i;
		//	badChars.add(a);
		//}
		//Don't look lower



		badWords.add("***");

		/*
		badWords.add("shit");
		badWords.add("fuck");
		badWords.add("ass");
		badWords.add("bitch");
		badWords.add("cunt");
		badWords.add("faggot");
		badWords.add("nigger");
		badWords.add("bastard");
		badWords.add("fucker");
		badWords.add("shit");
		badWords.add("cock");
		badWords.add("faggit");
		badWords.add("fag");
		badWords.add("fudgepacker");
		badWords.add("slut");
		badWords.add("penis");
		badWords.add("***");
		 */
	}
}
