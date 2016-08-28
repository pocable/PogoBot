package pogobot.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Twitch_API {
	public static Gson gson = new Gson();


	public static Twitch_Stream getStream(String channelname){
		try{
			String json = API.readJsonFromUrl("https://api.twitch.tv/kraken/channels/"+channelname);
			Twitch_Stream stream = new Twitch_Stream();
			if(json.equalsIgnoreCase("[]")){
				stream.setOnline(false);
				System.out.println("Offline!");
				return stream;
			}
			//JsonArray jb = gson.fromJson(json, JsonArray.class);
			//JsonObject jo = (JsonObject) jb.get(0);
			JsonObject jo = gson.fromJson(json, JsonObject.class);
			stream.setOnline(true);
			stream.load(jo);
			return stream;
		} catch (Exception error){
			error.printStackTrace();
		}


		return null;


	}
}