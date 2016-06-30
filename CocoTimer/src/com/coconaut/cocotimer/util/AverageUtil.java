package com.coconaut.cocotimer.util;

import java.util.LinkedList;

import com.coconaut.cocotimer.data.Time;

public class AverageUtil {
	public static int average(int[] times) {		
		int sum = 0;
		int worst = Integer.MIN_VALUE;
		int best = Integer.MAX_VALUE;
		
		for(int time : times) {
            if (time > worst) {
                worst = time;
            }

            if (time < best) {
                best = time;
            }
			
			sum += time;
		}
		
		int avg = (sum - worst - best) / (times.length - 2);
		
		return avg;
	}

	public static int average(LinkedList<Time> times) {		
		int sum = 0;
		int worst = Integer.MIN_VALUE;
		int best = Integer.MAX_VALUE;
		
		for(Time time : times) {
            if (time.getTicks() > worst) {
                worst = time.getTicks();
            }

            if (time.getTicks() < best) {
                best = time.getTicks();
            }
			
			sum += time.getTicks();
		}
		
		int avg = (sum - worst - best) / (times.size() - 2);
		
		return avg;
	}
}
