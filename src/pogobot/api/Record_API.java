package pogobot.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Record_API {
	
	public static Gson gson = new Gson();


	public static WorldRecords getRecords(String g, String categor){
		try{
			System.out.println(g);
			String game = g.replaceAll(" ", "%20");
			System.out.println(game);
			String json = API.readJsonFromUrl("http://www.speedrun.com/api_records.php?game="+game);

			WorldRecords stream = new WorldRecords();
			//JsonArray jb = gson.fromJson(json, JsonArray.class);
			//JsonObject jo = (JsonObject) jb.get(0);
			JsonObject jo = gson.fromJson(json, JsonObject.class);
			JsonObject category = jo.get(g).getAsJsonObject();
			stream.load(category, categor);
			return stream;
		} catch (Exception error){
			error.printStackTrace();
		}


		return null;


	}


	public static String getCategoryFromTitle(String title) {
		return title.substring(title.indexOf('[') + 1, title.indexOf(']'));
	}

}
