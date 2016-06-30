package com.coconaut.cocotimer.util;

import java.util.Random;

public class RandomUtil {
	public static Random r = new Random();
	
	public static int random(int arg0, int arg1) {
		return r.nextInt(arg1 - arg0) + arg0;
	}
}