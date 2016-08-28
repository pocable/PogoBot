package pogobot.functions;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import pogobot.frames.MainFrame;
import pogobot.lib.Reference;
import pogobot.main.MainListener;

public class SSettings {

	public static void changeTitle(String title){
		try{
			String url = "https://api.twitch.tv/kraken/channels/" + Reference.ADMIN ;
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestProperty("Accept", "application/vnd.twitchtv.v2+json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//Authorization: OAuth <access_token>
			conn.setRequestProperty("Authorization", "OAuth " + Reference.SOAUTH);
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);

			title = title.replaceAll(" ", "+");
			String data = "channel[status]=" + title;
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data);
			out.flush();

		//for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
				//System.out.println(header.getKey() + "=" + header.getValue());
		//	}

		}catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(MainFrame.main, "Could not change stream title (" + e + ")", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public static void changeGame(String title){
		try{
			String url = "https://api.twitch.tv/kraken/channels/" + Reference.ADMIN ;
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestProperty("Accept", "application/vnd.twitchtv.v2+json");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//Authorization: OAuth <access_token>
			conn.setRequestProperty("Authorization", "OAuth " + Reference.SOAUTH);
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);

			title = title.replaceAll(" ", "+");
			String data = "channel[status]=" + (MainListener.stream.getTitle().replaceAll(" ", "+")) + "&channel[game]=" + title;
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			out.write(data);
			out.flush();

			for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
				System.out.println(header.getKey() + "=" + header.getValue());
			}

		}catch(Exception e){
			System.out.println(e);
			JOptionPane.showMessageDialog(MainFrame.main, "Could not change stream title (" + e + ")", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

}
