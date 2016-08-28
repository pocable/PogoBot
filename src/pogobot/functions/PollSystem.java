package pogobot.functions;

import java.util.ArrayList;

import org.pircbotx.hooks.types.GenericMessageEvent;

import pogobot.lib.Reference;

public class PollSystem {
	public int amountOfItems;
	public String[] items;
	public boolean isActive = false;
	public ArrayList<String> voted = new ArrayList<String>();
	public ArrayList<Integer> votes = new ArrayList<Integer>();

	public void initializePoll(String[] items, @SuppressWarnings("rawtypes") GenericMessageEvent event){
		isActive = true;
		voted.clear();
		amountOfItems = items.length;
		this.items = items;
		event.getBot().sendIRC().message(Reference.LOCATION, ".subscribers");
		event.getBot().sendIRC().message(Reference.LOCATION, "-- New poll started --");
		for(int i = 0; i < items.length; i++){
			event.getBot().sendIRC().message(Reference.LOCATION, (i  + 1) + ". " + items[i]);
		}
		event.getBot().sendIRC().message(Reference.LOCATION, "-- Vote using !vote --");
		event.getBot().sendIRC().message(Reference.LOCATION, ".subscribersoff");
		for(int i = 0; i < items.length; i++){
			votes.add(0);
		}
	}

	public void registerVote(int vote){
		int math = votes.get(vote);
		math += 1;
		votes.set(vote, math);
		for(int v : votes){
			System.out.println(v);
		}
	}

	public void clearVoted(){
		voted.clear();
	}

	public void stopPoll(@SuppressWarnings("rawtypes") GenericMessageEvent event){
		int amount = votes.get(0);
		String name = items[0];
		boolean tie = false;
		for(int i = 0; i < votes.size(); i++){
			if(votes.get(i) > amount){
				amount = votes.get(i);
				name = items[i];
			}else if(votes.get(i) == amount){
				if(!(i == 0)){
					name += "\" and \"" + items[i];
					tie = true;
				}
			}
		}
		if(tie){
			event.getBot().sendIRC().message(Reference.LOCATION, "The poll has ended! It's a tie between \"" + name + "\" with " + amount + " vote(s) each!");
		}else{
			event.getBot().sendIRC().message(Reference.LOCATION, "The poll has ended! Winner is \"" + name + "\" with " + amount + " vote(s)!");
		}
		voted.clear();
		votes.clear();
		isActive = false;
	}

}
