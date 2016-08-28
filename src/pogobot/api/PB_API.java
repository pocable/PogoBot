package pogobot.api;

import pogobot.lib.Reference;
import pogobot.main.MainListener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PB_API {
	
	public static Gson gson = new Gson();


	public static PersonalBest getPersonalBest(String user, String g, String categor){
		try{
			String json = API.readJsonFromUrl("http://www.speedrun.com/api_records.php?user="+user);

			PersonalBest stream = new PersonalBest();
			//JsonArray jb = gson.fromJson(json, JsonArray.class);
			//JsonObject jo = (JsonObject) jb.get(0);
			JsonObject jo = gson.fromJson(json, JsonObject.class);
			JsonObject game = jo.get(g).getAsJsonObject();
			JsonObject category = game.get(categor).getAsJsonObject();
			stream.load(category, g);
			return stream;
		} catch (Exception error){
			MainListener.bot.sendIRC().message("#" + Reference.ADMIN, user + " does not have a pb in " + g + " {" + categor + "}");
		}
		return null;


	}


	public static String getCategoryFromTitle(String title) {
		return title.substring(title.indexOf('[') + 1, title.indexOf(']'));
	}

}
