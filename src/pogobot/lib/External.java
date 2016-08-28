package pogobot.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class External {
	
	public static File getFileFromString(String s){
		File f = new File(s);
		return f;
		
	}
	
	public static String getDefaultFilePath(){
		String path = new File("").getAbsolutePath() + "\\";
		try {
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			return decodedPath;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "null";
	}

	public static ArrayList<String> getArrayFromFile(File f){
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(getDefaultFilePath() + f));
			String line;
			try {
				while((line = in.readLine()) != null){
					data.add(line);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, f.getName(), "IO Exception Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, f.getName() + " does not exist!", "File Not Found", JOptionPane.ERROR_MESSAGE);
		}		
		return data;
	}

}
