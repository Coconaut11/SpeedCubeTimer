package com.coconaut.cocotimer.data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import com.coconaut.cocotimer.Game;
import com.coconaut.cocotimer.util.AverageUtil;
import com.coconaut.cocotimer.util.FileUtil;
import com.coconaut.cocotimer.util.TimeCompartionUtil;
import com.coconaut.cocotimer.util.TimeUtil;

public class TimeList {
	private boolean enabled = true;
	private int timesoffY = 0;
	private int maxOffsetY = Game.HEIGHT;
	
	private String filePath = "";
	
	public void render(Graphics g) {
		draw(g);
	}
	
	public void draw(Graphics g) {
		maxOffsetY = ((StatsManager.getLength() * 20 + 25) * -1) + (Game.HEIGHT - 25);
		int width = StatsManager.getLength() < 100 ? 125 : 133;
		
		g.setColor(new Color(200, 200, 200));
		g.fillRect(0, 0, width, Game.HEIGHT);
		g.setFont(new Font("Arial", Font.PLAIN, 20));			
		g.setColor(Color.black);
		
		for(int i = 0; i < StatsManager.getLength(); ++i) {
			int ty = i * 20 + 25;
			
			int sec = TimeUtil.intToTime(StatsManager.getTime(i).getTicks())[0];
			int min = TimeUtil.intToTime(StatsManager.getTime(i).getTicks())[1];
			int mil = TimeUtil.intToTime(StatsManager.getTime(i).getTicks())[2];			
			
			
			if(ty + timesoffY > -40 && ty + timesoffY < Game.HEIGHT){
				g.drawString((i+1) + ". " + (i+1 < 10 ? "  " : "") + (TimeUtil.bulidTimeString(min, sec, mil)), 5, ty + timesoffY);
			}
		}
		
		Time aoff = StatsManager.currentAOF;
		Time aott = StatsManager.currentAOT;
		Time best = StatsManager.bestTime;
		Time worst = StatsManager.worstTime;

		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 20));			
		g.drawString("AO5: " + (StatsManager.getLength() > 4 ? aoff.getTime() : "XX:XX:XX"), width + 10, Game.HEIGHT - 120);
		g.drawString("AO12: "  + (StatsManager.getLength() > 11 ? aott.getTime() : "XX:XX:XX"), width + 10, Game.HEIGHT - 100);		

		g.drawString("BEST: "  + (StatsManager.getLength() > 0 ? best.getTime() : "XX:XX:XX"), width + 10, Game.HEIGHT - 67);		
		g.drawString("WORST: "  + (StatsManager.getLength() > 0 ? worst.getTime() : "XX:XX:XX"), width + 10, Game.HEIGHT - 45);		
	}
	
	public void initSession(String filepath) {
		this.filePath = filepath;
		String file = FileUtil.readFile(filepath);
		if(file == null) return;
		
		String[] text = file.split("-");
		
		String[] times = text[0].split(",");
		String[] ao5s = text[1].split(",");
		String[] ao12s = text[2].split(",");
		
		for(String t : times) {
			String[] time = t.split("\\.");	
			int ticks = Integer.parseInt(time[0]);
			String scramble = time[1];
			StatsManager.times.add(new Time(ticks, scramble));
		}
		
		for(String s : ao5s) {
			int ticks = Integer.parseInt(s);
			StatsManager.ao5.add(new Time(ticks));
		}
		
		for(String s : ao12s) {
			int ticks = Integer.parseInt(s);
			StatsManager.ao12.add(new Time(ticks));
		}
	}
	
	public void saveSession() {
		if(StatsManager.getLength() < 0) return;
		String path = filePath;
		String text = "";
		
		for(Time time : StatsManager.times) {
			text += time.getTicks() + "." + time.getScramble() + ",";
		}
		
		text += "-";
		
		for(Time time : StatsManager.ao5) {
			text += time.getTicks() + ",";
		}
		
		text += "-";
		
		for(Time time : StatsManager.ao12) {
			text += time.getTicks() + ",";
		}

		FileUtil.writeFile(text, path);
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void _switch() {
		enabled = !enabled;
	}
	
	public void up() {
		timesoffY = 0;
	}

	public void down() {
		timesoffY = maxOffsetY;
	}
	
	public void scrollUp() {
		if(timesoffY < 0)
			timesoffY += 5;
	}
	
	public void scrollDown() {
		if(timesoffY > maxOffsetY)
			timesoffY -= 5;
	}
	
	public void scroll(int steps) {
		int scrollVel = steps;
	
		if(steps < 0) {
			if(steps < timesoffY) {
				scrollVel = timesoffY;
			}
		}
		
		if (steps > 0 && timesoffY > maxOffsetY) {
			timesoffY  = maxOffsetY;
			scrollVel = 0;
		}
		
		timesoffY -= scrollVel;
	}
}
	