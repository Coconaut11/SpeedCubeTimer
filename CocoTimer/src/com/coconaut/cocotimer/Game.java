package com.coconaut.cocotimer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.coconaut.cocotimer.data.StatsManager;
import com.coconaut.cocotimer.data.Time;
import com.coconaut.cocotimer.data.TimeList;
import com.coconaut.cocotimer.input.Keyboard;
import com.coconaut.cocotimer.input.Mouse;
import com.coconaut.cocotimer.panes.ConfigPane;
import com.coconaut.cocotimer.panes.ManualTimeInput;
import com.coconaut.cocotimer.panes.SessionInfoPane;
import com.coconaut.cocotimer.util.AverageUtil;
import com.coconaut.cocotimer.util.ImageLoadUtil;
import com.coconaut.cocotimer.util.ScrambleUtil;
import com.coconaut.cocotimer.util.TimeUtil;

public class Game extends Canvas implements Runnable, ComponentListener {
	//TODO Make actual ao's text on click show pop up with more ao's info
	
	public static final String VERSION = "0.3.1";
	public static int WIDTH = 800, HEIGHT = 600;
	private static int minW = (int) (WIDTH/1.1), minH = (int) (HEIGHT/1.5);
	
	private JFrame frame;
	
	private boolean running = false;
	private int fps, ups;
	Thread thread;
	private Keyboard kb;
	private TimeList timelist;
	private Mouse mouse;
	
	public boolean timeing = false;
	public boolean delay = false;
	public int time = 0;
	private static String scramble = "";
	private boolean showTimes = true;
	
	private Color timecolor = Color.black;
	private Color infocolor = Color.black;
	
	private BufferedImage config_dis, config_en, config_btn;
	
	int min;
	int sec;
	int mil;
	
	public Game(JFrame frame) {
		this.frame = frame;
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
		
		running = true;
	}
	
	public void init() {		
		kb = new Keyboard(this);
		timelist = new TimeList();
		mouse = new Mouse(timelist, this);
		
		scramble = ScrambleUtil.getRubiksCubeScramble(20);
		
		timelist.initSession("savedata/times.ct");
		ConfigPane.loadConfig();
		
		addMouseWheelListener(mouse);
		addMouseListener(mouse);
		
		config_dis = ImageLoadUtil.load("config_dis.png");
		config_en = ImageLoadUtil.load("config_en.png");
		config_btn = config_dis;

		StatsManager.updateStats();
	}
	
	public void run() {
		requestFocus();
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				ups++;
				delta--;
			}
			render();
			fps++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + fps + " UPS: " + ups);
				ups = 0;
				fps = 0;
			}
		}
		onClose();
	}
	
	public void tick() {
		if(timeing && !delay)
			++time;
		else if(delay && kb.delay > 0) {
			--kb.delay;
			System.out.println(kb.delay);
			if(kb.delay == 0) {
				delay = false;
				timeing = false;
				kb.delay = 30;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		sec = TimeUtil.intToTime(time)[0];
		min = TimeUtil.intToTime(time)[1];
		mil = TimeUtil.intToTime(time)[2];
	
		int timeX = 215, timeY = 213, timeW = 473, timeH = 89;
		g.setColor(timecolor);
		g.setFont(new Font("Arial", Font.BOLD, 120));
		g.drawString(TimeUtil.bulidTimeString(min, sec, mil), WIDTH/2 - timeW/2 + 46 + ((StatsManager.getLength() < 100 ? 0 : 7)), HEIGHT/2 - timeH/2);

		if(ConfigPane.scramble) {
			g.setColor(Color.black);
			g.setFont(new Font("Arial", Font.PLAIN, 23));
			g.drawString(scramble, (StatsManager.getLength() < 100 ? 125 : 133) + 10, 20);		
		}
		
		g.setColor(infocolor);
		g.setFont(new Font("Arial", Font.PLAIN, 40));					
		g.drawString("+", Game.WIDTH - 47, Game.HEIGHT - 45);
		
		g.drawImage(config_btn, WIDTH - 90, HEIGHT - 76, 32, 32, null);
		
		timelist.render(g);
		
		g.dispose();
		bs.show();		
	
	}
	
	private void onClose() {
        if(ConfigPane.saveTimes)
			timelist.saveSession();
		ConfigPane.saveConfig();
        System.exit(1);
	}
	
	public void deleteLastSolve() {
		StatsManager.deleteLastOne();
	}
	
	public void startTime() {
		min = 0;
		sec = 0;
		mil = 0;
		time = 0;
		timeing = true;
	}
	
	public void stopTime() {
		Time currentTime = new Time(time, scramble);
		
		StatsManager.addTime(currentTime);		
		StatsManager.updateStats();
		
		scramble = ScrambleUtil.getRubiksCubeScramble(ConfigPane.scrambleSize);
		
		delay = true;
	}
	
	public static void reloadScramble() {
		scramble = ScrambleUtil.getRubiksCubeScramble(ConfigPane.scrambleSize);		
	}

	public TimeList getTimeList() {
		return timelist;
	}
	
	public void setTimeColor(Color color) {
		this.timecolor = color;
	}
	
	public void setConfigBtn(boolean state) {
		if(state)
			config_btn = config_en;
		else
			config_btn = config_dis;
	}
	
	public static void main(String args[]) {
		 SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				final JFrame frame = new JFrame("CocoTimer " + VERSION + " - Rubik's Cube Timer");
				final Game game = new Game(frame);
				
				frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				frame.setMinimumSize(new Dimension(minW, minH));		
				frame.setSize(WIDTH, HEIGHT);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				
				Image icon = Toolkit.getDefaultToolkit().getImage("res/icon.png");	
				
				frame.setLocationRelativeTo(null);
				frame.setResizable(true);
				frame.setIconImage(icon);
				frame.setVisible(true);
				frame.addComponentListener(game);
				frame.add(game);
				
				frame.addWindowListener(new java.awt.event.WindowAdapter() {
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						if(ConfigPane.askOnClose) {
					        if (JOptionPane.showConfirmDialog(frame, 
					            "Are you sure?!", "Really Closing?!", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					            game.running = false;
					        }
						} else {
							game.running = false;
						}
					}
				});		game.start();				
			}
		});
	}
	
	public void componentResized(ComponentEvent e) {
		this.WIDTH = frame.getWidth();
		this.HEIGHT = frame.getHeight();
	}

	public void setInfoBtnColor(Color color) {
		this.infocolor = color;
	}

	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

}
