package com.coconaut.cocotimer.data;

import java.util.LinkedList;

import com.coconaut.cocotimer.util.TimeUtil;

public class StatsManager {
	public static LinkedList<Time> times = new LinkedList<Time>();
	public static LinkedList<Time> ao5 = new LinkedList<Time>();
	public static LinkedList<Time> ao12 = new LinkedList<Time>();
	
	public static Time currentTime = new Time(true);
	public static Time currentAOF = new Time(true);
	public static Time currentAOT = new Time(true);
	public static Time bestAOF = new Time(true);
	public static Time bestAOT = new Time(true);
	public static Time bestTime = new Time(true);
	public static Time worstTime = new Time(true);

	public static Time getTime(int index) {
		return times.get(index);
	}
	
	public static void addTime(Time time) {
		times.add(time);
	}
	
	public static void setTime(int index, Time time) {
		times.add(index, time);
	}
	
	public static int getLength() {
		return times.size();
	}

	public static void deleteLastOne() {
		if(getLength() < 1) return; 
		times.removeLast();
		if(getLength() < 5)ao5.removeLast();
		if(getLength() < 12)ao12.removeLast();
		StatsManager.updateStats();
	}
	
	public static void removeAll() {
		for(int i = times.size() - 1; i > 0; i--) {
			times.remove(i);
		}
		times.remove(0);
		System.out.println("Removed all times!");
	}
	
	public static void updateStats() {
		bestTime = new Time(true);
		worstTime = new Time(true);	
		currentAOF = new Time(true);
		bestAOF = new Time(true);
		currentAOT = new Time(true);
		bestAOT = new Time(true);

		if(getLength() < 1) return;
		
		Time currentTime = times.getLast();

		StatsManager.currentTime = currentTime;
		bestTime = new Time(TimeUtil.getBestTime());
		worstTime = new Time(TimeUtil.getWorstTime());
		
		if(getLength() > 4) {
			ao5.add(new Time(TimeUtil.getCurrentAOFive()));
			currentAOF = new Time(TimeUtil.getCurrentAOFive());
			if(ao5.size() > 0)
				bestAOF = new Time(TimeUtil.getBestAOFive());
		}
		
		if(getLength() > 11) {
			ao12.add(new Time(TimeUtil.getCurrentAOTwelve()));
			currentAOT = new Time(TimeUtil.getCurrentAOTwelve());
			if(ao12.size() > 0)
				bestAOT = new Time(TimeUtil.getBestAOTwelve());
		}	
	}
	
	public static String getSessionInfoAsString() {
		String string = "";
		
		for(int i = 0; i < times.size(); ++i) {
			Time time = times.get(i);
			
			string += (i != 0 ? "\n\r" : "") + (i+1) + ". " + time.getTime() + " - " + time.getScramble();
		}
		
		return string;
	}
	
	public static LinkedList<Time> getTimes() {
		return times;
	}

	public static int[] getLastFive() {
		int[] times = {getTime(getLength() - 1).getTicks(), 
				getTime(getLength() - 2).getTicks(), 
				getTime(getLength() - 3).getTicks(), 
				getTime(getLength() - 4).getTicks(),
				getTime(getLength() - 5).getTicks()};
		return times;
	}

	public static int[] getLastTwelve() {
		int[] times = {getTime(getLength() - 1).getTicks(), 
				getTime(getLength() - 2).getTicks(), 
				getTime(getLength() - 3).getTicks(), 
				getTime(getLength() - 4).getTicks(),
				getTime(getLength() - 5).getTicks(),
				getTime(getLength() - 6).getTicks(),
				getTime(getLength() - 7).getTicks(),
				getTime(getLength() - 8).getTicks(),
				getTime(getLength() - 9).getTicks(),
				getTime(getLength() - 10).getTicks(),
				getTime(getLength() - 11).getTicks(),
				getTime(getLength() - 12).getTicks()};
		
		return times;
	}
}
