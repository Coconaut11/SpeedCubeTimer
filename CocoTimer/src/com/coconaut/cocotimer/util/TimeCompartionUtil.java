package com.coconaut.cocotimer.util;

import java.util.LinkedList;

import com.coconaut.cocotimer.data.Time;

public class TimeCompartionUtil {
	public static int getBest(int[] times) {
		int best = times[0];
		
		for(int i = 1; i < times.length; ++i) {
			int actual = times[i];
			
			if(actual < best) {
				best = actual;
			}
		}
		
		return best;
	}

	public static int getBest(LinkedList<Time> times) {
		int best = times.get(0).getTicks();
		
		for(int i = 1; i < times.size(); ++i) {
			int actual = times.get(i).getTicks();
			
			if(actual < best) {
				best = actual;
			}
		}
		
		return best;
	}

	public static int getWorst(int[] times) {
		int worst = times[0];
		
		for(int i = 1; i < times.length; ++i) {
			int actual = times[i];
			
			if(actual > worst) {
				worst = actual;
			}
		}
		
		return worst;
	}

	public static int getWorst(LinkedList<Time> times) {
		int worst = times.get(0).getTicks();
		
		for(int i = 1; i < times.size(); ++i) {
			int actual = times.get(i).getTicks();
			
			if(actual > worst) {
				worst = actual;
			}
		}
		
		return worst;
	}
}
