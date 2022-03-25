package com.tutoral.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	
	private Handler handler;
	
	private Random r = new Random();
	
	private Color col;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = r.nextInt(7 - -7) - 7;
		velY = r.nextInt(7 - -7) - 7;
		if (velX == 0) velX = 1;
		if (velY == 0) velY = 1;
		
		
		this.handler = handler;
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255), 80);
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 52);
		if (y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.object.add(new Trail(x, y, ID.Trail, col, 16, 16, 0.05f, handler));
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
