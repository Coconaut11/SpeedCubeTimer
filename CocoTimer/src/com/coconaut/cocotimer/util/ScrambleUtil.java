package com.coconaut.cocotimer.util;

import java.util.Random;

public class ScrambleUtil {
	
	private static Random r = new Random(System.currentTimeMillis());
	
	private static String[] moves = {
		"Uy",
		"Dy",
		"Fz",
		"Rx",
		"Lx",
		"Bz",
	};
	
	public static String[] directions = {
		" ",
		"' ",
		"2 "
	};
	
	public static String getRubiksCubeScramble(int length) {
		String scramble = "";
		
		String m0 = "   ";
		String m1 = "   ";
		
		String dir = "";
		
		for(int i = 0; i < length; ++i) {
			String currentMove = getMove(m0, m1);
			dir = directions[r.nextInt(directions.length)];
			
			scramble += currentMove.charAt(0) + dir;
			
			m0 = m1;
			m1 = currentMove;
		}
				
		return scramble;
	}
	
	private static String getMove(String m0, String m1) {
		String move = moves[r.nextInt(moves.length)];
		
		if(m1 == move || mesmoEixo(m0, m1, move) == true) {
			return getMove(m0, m1);
		}
		return move;
	}
	
	private static boolean mesmoEixo(String m1, String m2, String m3) {
		if(m2.charAt(1) == m1.charAt(1) && m2.charAt(1) == m3.charAt(1)) {
			return true;
		}
		
		return false;
	}
}
