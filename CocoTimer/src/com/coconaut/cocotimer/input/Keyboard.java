package com.coconaut.cocotimer.input;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.coconaut.cocotimer.Game;
import com.coconaut.cocotimer.data.StatsManager;
import com.coconaut.cocotimer.panes.ManualTimeInput;

public class Keyboard implements KeyListener {
	
	private Game game;
	
	private boolean cond = false;
	public int delay = 30;
	
	public Keyboard(Game game) {
		game.addKeyListener(this);
		this.game = game;
	}
	
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP || arg0.getKeyCode() == KeyEvent.VK_PAGE_UP) {
			game.getTimeList().scrollUp();
		}

		if(arg0.getKeyCode() == KeyEvent.VK_DOWN || arg0.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
			game.getTimeList().scrollDown();			
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE && !game.timeing) {
			game.setTimeColor(Color.green);
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_END) {
			game.getTimeList().down();
		}

		if(arg0.getKeyCode() == KeyEvent.VK_END) {
			game.getTimeList().down();
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_HOME) {
			game.getTimeList().up();
		}
		
		if(arg0.isControlDown()) {
			if(arg0.getKeyCode() == KeyEvent.VK_S) {
				game.getTimeList().saveSession();
			}

			if(arg0.getKeyCode() == KeyEvent.VK_Q) {
				StatsManager.deleteLastOne();
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_I) {
				new ManualTimeInput(game.getTimeList());
			}
			
			if(arg0.getKeyCode() == KeyEvent.VK_A) {
				StatsManager.removeAll();
			}
		}
				
		if(game.timeing && !cond && !game.delay) {
			game.stopTime();
			cond = true;
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		if(!game.timeing)
			if(arg0.getKeyCode() == arg0.VK_SPACE && !cond) {
				game.startTime();
				game.setTimeColor(Color.black);				
			}
			
			cond = false;
	}

	public void keyTyped(KeyEvent arg0) {
	}

}
