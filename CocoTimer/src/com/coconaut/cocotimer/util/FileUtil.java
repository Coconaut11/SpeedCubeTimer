package com.coconaut.cocotimer.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileUtil {
	
	public static void writeFile(String str, String path) {
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(str);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(String path) {
		try {
			FileReader fr = new FileReader(path);
			BufferedReader reader = new BufferedReader(fr);
			String str = reader.readLine();
			fr.close();
			reader.close();
			return str;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Files required not found!", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Unable to read the files!", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		return null;
	}
	
}