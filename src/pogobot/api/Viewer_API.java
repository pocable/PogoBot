package pogobot.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Viewer_API {
	
	public static Gson gson = new Gson();
	
	public static Twitch_Viewers getViewers(String channelname){
		try{
			String json = API.readJsonFromUrl("https://tmi.twitch.tv/group/user/"+channelname+"/chatters");
			Twitch_Viewers v = new Twitch_Viewers();
			
			//JsonArray jb = gson.fromJson(json, JsonArray.class);
			//JsonObject jo = (JsonObject) jb.get(0);
			JsonObject jo = gson.fromJson(json, JsonObject.class);
			JsonObject chatters = jo.get("chatters").getAsJsonObject();
			v.load(chatters);
			return v;
		} catch (Exception error){
			error.printStackTrace();
		}


		return null;


	}

}
