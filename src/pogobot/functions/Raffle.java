package pogobot.functions;

import java.util.ArrayList;
import java.util.Random;

import pogobot.api.Twitch_Viewers;
import pogobot.api.Viewer_API;
import pogobot.lib.Reference;

public class Raffle {
	private static Twitch_Viewers v;
	private ArrayList<String> entered;

	public Raffle(){
		v = Viewer_API.getViewers(Reference.ADMIN);
		entered = v.getEveryViewer();
	}

	public Raffle(boolean staff, boolean mods, boolean view) {
		v = Viewer_API.getViewers(Reference.ADMIN);
		entered = new ArrayList<String>();

		if(staff){
			if(!v.getStaff().isEmpty()){
				entered.addAll(v.getStaff());
			}
			if(!v.getAdmins().isEmpty()){
				entered.addAll(v.getAdmins());
			}
			if(!v.getGlobalMods().isEmpty()){
				entered.addAll(v.getGlobalMods());
			}
		}

		if(mods){
			if(!v.getMods().isEmpty()){
				entered.addAll(v.getMods());
			}
		}

		if(view){
			if(!v.getViewers().isEmpty()){
				entered.addAll(v.getViewers());
			}
		}

		if(staff == false){
			if(mods == false){
				if(view == false){
					entered.add("|Nothing was selected!|");
				}
			}
		}
		
		if(entered.contains(Reference.BOTNAME.toLowerCase())){
			entered.remove(Reference.BOTNAME.toLowerCase());
		}
	}

	public String getResult(){
		return select(entered);
	}

	public int getOdds(){
		try{
			return entered.size();
		}catch(NullPointerException e){
			return 0;
		}
	}

	private String select(ArrayList<String> s){
		try{
			if(!s.isEmpty()){
				int size = s.size();
				Random r = new Random();
				int chosen = r.nextInt(size);
				return s.get(chosen);
			}else{
				return "|Nothing has been found|";
			}
		}catch(NullPointerException e){
			return "|Nothing was found|";
		}
	}

}
