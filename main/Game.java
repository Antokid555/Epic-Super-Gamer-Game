package com.epicGamer.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5950954143775484936L;
	public static final int WIDTH = 1080, HEIGHT = WIDTH/12 * 9;
	public static int WIDTH_CONTENT, HEIGHT_CONTENT;
	
	private Thread thread;
	private boolean running = false;
	private int fps;
	
	private Handler handler = new Handler();
	private KeyInput keylistener = new KeyInput(handler, this);
	private Random r = new Random();
	private Camera camera = new Camera(0, 0);
	
	
	public Game() {
		this.addKeyListener(keylistener);
		
		new Window(WIDTH, HEIGHT, "Super Epic Time #3", this);
		WIDTH_CONTENT = this.getWidth();
		HEIGHT_CONTENT = this.getHeight();
		
		new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player, handler, camera);
		
		new WallTile(100, 100, ID.Tile, handler, camera);
		
		
		for (int i = 0; i < 2000; i++) {
			new WallTile(r.nextInt(50000), r.nextInt(50000), ID.Tile, handler, camera);
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
				System.out.println("FPS: " + frames);
				fps = frames;
				frames = 0;
			}
		}
		stop();	
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
		g.drawString("fps: " + fps, 10, 10);
		
		
		handler.render(g);
		
		
		g.dispose();
		bs.show();
		
	}
	
	private void tick() {
		handler.tick();
	}
	


	public static void main(String[] args) {
		new Game();
	}




}
