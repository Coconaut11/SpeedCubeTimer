package com.coconaut.cocotimer.util;

import com.coconaut.cocotimer.data.StatsManager;
import com.coconaut.cocotimer.data.TimeList;

public class TimeUtil {
	public static int[] intToTime(int time) {
		int[] t = new int[3];
		
		t[0] = time/60;
		t[1] = t[0]/60;
		t[2] = (time%60) * 100 / 60;
		t[1] %= 60;
		t[0] %= 60;
		
		return t;
	}
	
	public static int timeToInt(String time) {
		String[] _time = time.split("\\.");
		
		boolean hasMin = true;
		if(_time.length == 2) {
			hasMin = false;
		}

		int min = 0;
		int sec = 0;
		int mil = 0;
		
		if(hasMin) {
			min = Integer.parseInt(_time[0]);
			sec = Integer.parseInt(_time[1]);
			mil = Integer.parseInt(_time[2]);
		}

		if(!hasMin) {
			min = 0;
			sec = Integer.parseInt(_time[0]);
			mil = Integer.parseInt(_time[1]);
		}
				
		int finalTime = 0;
		
		finalTime += min * 3600;
		finalTime += sec * 60;
		finalTime += (mil * 60 / 100);
		
		return finalTime;
	} 
	
	public static String bulidTimeString(int[] time) {
		return (time[1] < 10 ? "0" + time[1] : time[1]) + ":" + (time[0] < 10 ? "0" + time[0] : time[0]) + ":" + (time[2] < 10 ? "0" + time[2] : time[2]);
	}
	
	public static String bulidTimeString(int min, int sec, int mil) {
		return (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec) + ":" + (mil < 10 ? "0" + mil : mil);
	}

	public static int getCurrentAOFive() {
		return AverageUtil.average(StatsManager.getLastFive());
	}

	public static int getCurrentAOTwelve() {
		return AverageUtil.average(StatsManager.getLastTwelve());
	}

	public static int getBestAOFive() {
		return TimeCompartionUtil.getBest(StatsManager.ao5);
	}

	public static int getBestAOTwelve() {
		return TimeCompartionUtil.getBest(StatsManager.ao12);
	}

	public static int getBestTime() {
		return TimeCompartionUtil.getBest(StatsManager.times);
	}

	public static int getWorstTime() {
		return TimeCompartionUtil.getWorst(StatsManager.times);
	}
}
