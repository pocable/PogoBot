package pogobot.lib;

import pogobot.api.PB_API;
import pogobot.api.PersonalBest;
import pogobot.api.Record_API;
import pogobot.api.Twitch_Stream;
import pogobot.api.WorldRecords;
import pogobot.functions.Customization;
import pogobot.functions.TimeHandler;
import pogobot.main.MainListener;

public class Library {

	public static WorldRecords wr;
	public static PersonalBest pb;
	public static Customization c = new Customization();

	public static void updateCategory(Twitch_Stream stream){
		if(!MainListener.overloadWr){
			if(stream.getTitle().contains("[") && stream.getTitle().substring(stream.getTitle().indexOf('[')).contains("]")){
				MainListener.category = Record_API.getCategoryFromTitle(stream.getTitle());
				System.out.println(MainListener.category + " has been set!");
			}
		}
	}

	public static String custWorldRecord(Twitch_Stream stream){
		String message = c.getData("world_record");
		try{
			if(!MainListener.overloadWr){
				if(stream.getTitle().contains("[") && stream.getTitle().substring(stream.getTitle().indexOf('[')).contains("]")){
					MainListener.category = Record_API.getCategoryFromTitle(stream.getTitle());
					System.out.println(MainListener.category + " has been set!");
				}
			}
			wr = Record_API.getRecords(stream.getMeta_game(), MainListener.category);
			//event.respond(category + " world record is currently held by " + wr.getPlayer() + " with a time of " + wr.getNiceTime() + "!");
			//message = ("World Record for " + stream.getMeta_game() + " {" + MainListener.category + "} is held by " + wr.getPlayer() + " with a time of " + wr.getNiceTime() + "!");
			if(message.contains("%wrplayer%")){ message = message.replaceAll("%wrplayer%", wr.getPlayer()); }
			if(message.contains("%wrtime%")){ message = message.replaceAll("%wrtime%", wr.getNiceTime()); }
		}catch(NullPointerException e){
			message = ("The category that was entered is not valid! - Category: \"" + MainListener.category + "\"!");
		}
		return custText(message);
	}

	public static String custPersonalBest(String user, String game, String category){
		String message = c.getData("personal_best");
		try{
			PersonalBest pb = PB_API.getPersonalBest(user, game, category);
			if(message.contains("%pbplayer%")){ message = message.replaceAll("%pbplayer%", pb.getPlayer()); }
			if(message.contains("%pbgame%")){ message = message.replaceAll("%pbgame%", pb.getGame()); }
			if(message.contains("%pbtime%")){ message = message.replaceAll("%pbtime%", pb.getNiceTime()); }
			
		}catch(NullPointerException e){
			e.printStackTrace();
			//message = ("The category that was entered is not valid! - Category: \"" + MainListener.category + "\"!");
		}
		return custText(message);
	}


	public static String custText(String message){
		Twitch_Stream stream = MainListener.stream;
		if(message.contains("%game%")){ message = message.replaceAll("%game%", stream.getMeta_game()); }
		if(message.contains("%streamer%")){ message = message.replaceAll("%streamer%", stream.getName()); }
		if(message.contains("%brl%")){ message = message.replaceAll("%brl%", "{"); }
		if(message.contains("%brr%")){ message = message.replaceAll("%brr%", "}"); }
		if(message.contains("%nothing%")){ message = message.replaceAll("%nothing%", "([null])"); }
		if(message.contains("%title%")){ message = message.replaceAll("%title%", stream.getTitle()); }
		if(message.contains("%followers%")){ message = message.replaceAll("%followers%", stream.getFollowers() + ""); }
		if(message.contains("%views%")){ message = message.replaceAll("%views%", stream.getViews_count() + ""); }
		if(message.contains("%uptime%")){ message = message.replaceAll("%uptime%", TimeHandler.getUptime()); }
		if(Reference.SPEEDRUNNER){ if(message.contains("%category%")){ message = message.replaceAll("%category%", MainListener.category); }}
		return message;

	}

}
