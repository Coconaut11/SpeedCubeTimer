package com.coconaut.cocotimer.data;

import com.coconaut.cocotimer.util.TimeUtil;

public class Time {
	private int ticks;
	private String scramble = "No Scramble";
	private String time = "DNF";
	
	public Time(int ticks, String scramble) {
		this.ticks = ticks;
		this.scramble = scramble;
	}

	public Time(int ticks) {
		this.ticks = ticks;
	}

	public Time(String time, String scramble) {
		this.time = time;
		this.scramble = scramble;
	}

	public Time(String time) {
		this.time = time;
	}

	public Time(boolean crossed) {
		time = "XX:XX:XX";
	}
	
	public int getTicks() {
		return ticks;
	}
	
	public String getTime() {
		if(time.equals("XX:XX:XX")) return time;
		time = TimeUtil.bulidTimeString(TimeUtil.intToTime(ticks));
		return time;
	}
	
	public String getScramble() {
		return scramble;
	}
	
}
