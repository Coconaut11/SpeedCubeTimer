package com.coconaut.cocotimer.input;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.coconaut.cocotimer.Game;
import com.coconaut.cocotimer.data.TimeList;
import com.coconaut.cocotimer.panes.ConfigPane;
import com.coconaut.cocotimer.panes.SessionInfoPane;

public class Mouse implements MouseWheelListener, MouseListener {
	
	private TimeList timelist;
	private Game game;
	
	public Mouse(TimeList timelist, Game game) {
		this.timelist = timelist;
		this.game = game;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(isPointInside(mx, my, Game.WIDTH - 40, 540, 25, 25)) {
			game.setInfoBtnColor(Color.gray);	
		}		

		if(isPointInside(mx, my, Game.WIDTH - 91, Game.HEIGHT - 77, 32, 32)) {
			game.setConfigBtn(true);
		}		
	}

	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(isPointInside(mx, my, Game.WIDTH - 40, 540, 25, 25)) {
			game.setInfoBtnColor(Color.black);	
			new SessionInfoPane();
		}

		if(isPointInside(mx, my, Game.WIDTH - 91, Game.HEIGHT - 77, 32, 32)) {
			game.setConfigBtn(false);
			new ConfigPane();
		}		
	}
	
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int steps = arg0.getWheelRotation();
		
		if(steps < 0) {
			timelist.scroll(steps * 10);
		} else {
			timelist.scroll(steps * 10);
		}
		
	}
	
	public boolean isPointInside(int mx, int my, int  x, int y, int w, int h) {
		return mx > x && mx < x+w && my > y && my < my + h;
	}
}
