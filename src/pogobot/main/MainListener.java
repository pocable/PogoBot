package pogobot.main;

import java.util.Timer;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import pogobot.api.Twitch_API;
import pogobot.api.Twitch_Stream;
import pogobot.frames.AnnFrame;
import pogobot.frames.MainFrame;
import pogobot.frames.PollFrame;
import pogobot.functions.Advertisements;
import pogobot.functions.CustomCMD;
import pogobot.functions.PollSystem;
import pogobot.functions.SSettings;
import pogobot.functions.TimeHandler;
import pogobot.functions.WarningSystem;
import pogobot.lib.BannedText;
import pogobot.lib.Config;
import pogobot.lib.Library;
import pogobot.lib.Reference;
import pogobot.task.AdvertiseTask;

@SuppressWarnings("rawtypes")
public class MainListener extends ListenerAdapter{

	public static PircBotX bot;
	PollSystem p = new PollSystem();
	static WarningSystem w;
	static BannedText bt;
	public static Advertisements a = new Advertisements();
	public static Config c = new Config();
	public static CustomCMD cmd = new CustomCMD();
	public boolean commandsEnabled = true;
	public static boolean disabled = false;
	public static Twitch_Stream stream = Twitch_API.getStream(Reference.ADMIN);
	public static boolean banText = true;
	public static String category = "Any%";
	public static Boolean overloadWr = false;
	public static String prefix = Config.getPrefix();
	public static boolean usingAT = false;

	/*
	 * Prepare for dick ripping if you can read this and see my code. Its horrendous. The config's
	 * could of all extended one basic class, but nooooo, i'm too cool for that.
	 */

	@Override
	public void onGenericMessage(GenericMessageEvent event) {
		if(!disabled){
			String sender = event.getUser().getNick();
			stream = Twitch_API.getStream(Reference.ADMIN);
			int args = event.getMessage().split(" ").length;
			if(commandsEnabled){

				if (event.getMessage().startsWith(prefix + "btchk"))
					event.respond("PogoBot v" + Reference.VERSION + ". Made by pogo4545!");

				if(event.getMessage().startsWith(prefix + "pb")){
					if(args == 1){
						Library.updateCategory(stream);
						System.out.println(stream.getName() + "+" + stream.getMeta_game() + "+" + category);
						event.respond(Library.custPersonalBest(stream.getName(), stream.getMeta_game(), category));
						//event.respond(Library.custPersonalBest("pogo4545", "The Legend of Zelda: A Link to the Past", "Master Sword"));
					}
				}

				cmd.checkCommand(event);
				if(banText){
					if(!Reference.ADMINS.contains(event.getUser().getNick()) || !Reference.ADMIN.equals(sender)){
						if(!event.getUser().getNick().equals("jtv")){
							bt.checkText(event, w);
						}
					}
				}

				if(Reference.SPEEDRUNNER){
					if(event.getMessage().startsWith(prefix + "wr")){
						event.respond(Library.custWorldRecord(stream));
					}
				}

				if(event.getMessage().startsWith(prefix + "uptime")){
					event.respond(stream.getName() + " has been streaming for " + TimeHandler.getUptime() + "!");
				}

				if(event.getMessage().startsWith(prefix + "version"))
					event.respond("Current bot version: " + Reference.VERSION + "!");

				if (event.getMessage().startsWith(prefix + "ismod"))
					event.respond(Reference.ADMINS.contains(sender) + "!");

				if(p.isActive){
					if(!p.voted.contains(event.getUser().getNick())){
						for(int i = 0; i < p.amountOfItems; i++){
							if(event.getMessage().toLowerCase().startsWith(prefix + "vote " + (i + 1))){ //(i + 1)
								p.registerVote((i));
								event.respond("Vote registered for \"" + (p.items[i]) + "\"!");
								p.voted.add(event.getUser().getNick());
							}
						}
						if(!p.votes.isEmpty()){
							PollFrame.registerPollResults(p.votes, p.items);
						}
					}
				}

			}

			if(event.getUser().getNick().equals(Reference.ADMIN)){
				if (event.getMessage().startsWith(prefix + "clear")){
					event.getBot().sendIRC().message(Reference.LOCATION, ".clear");
				}else if (event.getMessage().startsWith(prefix + "poll")){
					String message = event.getMessage().replaceAll(prefix + "poll", "");
					String[] a = message.split(",");
					p.initializePoll(a, event);
					PollFrame.amount = p.amountOfItems;
				}else if(event.getMessage().startsWith(prefix + "endpoll")){
					p.stopPoll(event);
				}else if(event.getMessage().startsWith(prefix + "morevotes")){
					p.clearVoted();
					event.getBot().sendIRC().message(Reference.LOCATION, "You can now vote again!");
				}else if(event.getMessage().startsWith(prefix + "warn")){
					String message = event.getMessage().replaceAll(prefix + "warn", "");
					String[] a = message.split("\\s+");
					String user = a[1];
					message = message.replace(user, "");
					String reason = message;
					System.out.println(user);
					System.out.println(reason);
					user = user.replaceAll("\\s+", "");
					w.warnPlayer(user, reason);
				}else if(event.getMessage().startsWith(prefix + "commandsoff")){
					commandsEnabled = false;
					event.getBot().sendIRC().message(Reference.LOCATION, "Commands Disabled!");
				}else if(event.getMessage().startsWith(prefix + "commandson")){
					event.getBot().sendIRC().message(Reference.LOCATION, "Commands Enabled!");
					commandsEnabled = false;
				}else if(event.getMessage().startsWith(prefix + "togglecommands")){
					if(commandsEnabled){
						event.getBot().sendIRC().message(Reference.LOCATION, "Commands Disabled!");
						commandsEnabled = false;
					}else{
						event.getBot().sendIRC().message(Reference.LOCATION, "Commands Enabled!");
						commandsEnabled = true;
					}
				}else if(event.getMessage().startsWith(prefix + "announce")){
					String msg = event.getMessage().replace(prefix + "announce ", "");
					AnnFrame.announceAndKeep(msg);
				}else if(event.getMessage().startsWith(prefix + "mod")){
					String ply = event.getMessage().replaceAll(prefix + "mod ", "");
					if(Reference.ADMINS.contains(ply)){
						Reference.ADMINS.remove(ply);
						event.respond(ply + " removed from admins!");
					}else{
						Reference.ADMINS.add(ply);
						event.respond(ply + " added to admins!");
					}
				}else if(event.getMessage().startsWith(prefix + "tbt")){
					if(banText){
						banText = false;
						event.respond("Text moderation disabled!");
					}else{
						banText = true;
						event.respond("Text moderation enabled!");
					}
				}else if(event.getMessage().startsWith(prefix + "setcat")){
					overloadWr = true;
					String message = event.getMessage().replaceAll(prefix + "setcat ", "");
					category = message;
					event.respond("Category set to: \"" + category + "\"");
				}else if(event.getMessage().startsWith(prefix + "title")){
					if(usingAT){
						String msg = event.getMessage().replace(prefix + "title", "");
						SSettings.changeTitle(msg);
						event.respond("Title changed to:" + msg);
					}
				}else if(event.getMessage().startsWith(prefix + "setgame")){
					if(usingAT){
						String msg = event.getMessage().replace(prefix + "setgame", "");
						SSettings.changeGame(msg);
						event.respond("Game changed to:" + msg);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws Exception {
		System.out.println("LAUNCH START!");
		new MainFrame();
		//Configure what we want our bot to do
		Configuration configuration = new Configuration.Builder()
		.setServerPassword(Reference.OAUTH)
		.setName(Reference.BOTNAME)
		.setLogin(Reference.BOTNAME)
		.setServerHostname(Reference.TWITCHSRV)
		.addAutoJoinChannel(Reference.LOCATION)
		.addListener(new MainListener()) 
		.buildConfiguration();

		//Create our bot with the configuration
		bot = new PircBotX(configuration);
		//Connect to the server
		w = new WarningSystem(bot);
		bt = new BannedText();
		TimeHandler.setCurrentTime();
		if(Reference.ADVERTISE){
			Timer timer = new Timer();
			//1000 converts it to seconds, 60 makes it minutes.
			timer.scheduleAtFixedRate(new AdvertiseTask(a), 10, (((Reference.AMOUNTOFWARNING) * 1000) * 60));
		}

		bot.startBot();
	}


	public static void convertText(){

	}

}
