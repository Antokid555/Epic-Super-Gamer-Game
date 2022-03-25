package com.tutoral.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = 5746755803055166899L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	

	private Handler handler;
	private HUD hud;
	private Random r;
	private Spawn spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Game,
		Lose
	};
	
	public STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	private int FPSIDK;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, hud, this));
		this.addMouseListener(menu);
		
		AudioPlayer.load();
		
		AudioPlayer.getMusic("music").loop();
		AudioPlayer.changeVolume(-0.9f);
		
		new Window(WIDTH, HEIGHT, "Epic Gamer Moment", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		r = new Random();
		
		spawner = new Spawn(handler, hud);
		
		if (gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		} else if (gameState == STATE.Menu) {
			for (int i = 0; i < 20; i++) {
				handler.object.add(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
			}
		}
		
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + "   entities: " + handler.object.size());
				FPSIDK = frames;
				frames = 0;
			}
		}
		stop();	
	}
	
	private void tick() {
		if(!paused) {
			
			handler.tick();
			
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				handler.object.clear();
				for (int i = 0; i < 20; i++) {
					handler.object.add(new MenuParticle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.MenuParticle, handler));
				}
				gameState = STATE.Lose;
			}
			
			if (gameState == STATE.Game) {
				hud.tick();
				spawner.tick();
			} else if (gameState == STATE.Menu || gameState == STATE.Lose) {
				menu.tick();
			}
		}
		

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.green);
		g.drawString("FPS: " + FPSIDK, 20, 10);
		
		handler.render(g);
		
		if(paused) {
			g.drawString("PAUSED", 100, 100);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Lose) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) 
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
	}

}
