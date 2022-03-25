package com.tutoral.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{
	
	private Handler handler;
	
	

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = 2;
		velY = 9;
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 52);
		if (y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		handler.object.add(new Trail(x, y, ID.Trail, Color.pink, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
