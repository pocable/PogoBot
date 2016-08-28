package pogobot.lib;

import java.util.ArrayList;

public class Converter {
	
	public static ArrayList<String> convertArray(String[] array){
		ArrayList<String> a = new ArrayList<String>();
		for(String c : array){
			a.add(c);
		}
		return a;
	}

}
