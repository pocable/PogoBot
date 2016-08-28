package pogobot.api;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Twitch_Viewers {
	
	ArrayList<String> mods = new ArrayList<String>();
	ArrayList<String> staff = new ArrayList<String>();
	ArrayList<String> admins = new ArrayList<String>();
	ArrayList<String> globalmods = new ArrayList<String>();
	ArrayList<String> viewers = new ArrayList<String>();
	
	public void load(JsonObject job)
	{
		JsonArray k = job.get("moderators").getAsJsonArray();
		for(int i = 0; i < k.size(); i++){
			mods.add(k.get(i).getAsString());
		}
		
		JsonArray s = job.get("staff").getAsJsonArray();
		for(int i = 0; i < s.size(); i++){
			staff.add(s.get(i).getAsString());
		}
		
		JsonArray a = job.get("admins").getAsJsonArray();
		for(int i = 0; i < a.size(); i++){
			admins.add(a.get(i).getAsString());
		}
		
		JsonArray gm = job.get("global_mods").getAsJsonArray();
		for(int i = 0; i < gm.size(); i++){
			globalmods.add(gm.get(i).getAsString());
		}
		
		JsonArray v = job.get("viewers").getAsJsonArray();
		for(int i = 0; i < v.size(); i++){
			viewers.add(k.get(i).getAsString());
		}
	}
	
	public ArrayList<String> getMods(){
		return mods;
	}
	
	public ArrayList<String> getStaff(){
		return staff;
	}
	
	public ArrayList<String> getAdmins(){
		return admins;
	}
	
	public ArrayList<String> getGlobalMods(){
		return globalmods;
	}
	
	public ArrayList<String> getViewers(){
		return viewers;
	}
	
	public ArrayList<String> getEveryViewer(){
		ArrayList<String> all = new ArrayList<String>();
		all.addAll(mods);
		all.addAll(staff);
		all.addAll(admins);
		all.addAll(globalmods);
		all.addAll(viewers);
		return all;
	}

}
