package com.tutoral.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	private Handler handler;
	private Random r = new Random();

	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		
		//x = Game.clamp(x, 0, Game.WIDTH - 32);
		//y = Game.clamp(y, 0, Game.HEIGHT - 52);
		//if (y <= 0 || y >= Game.HEIGHT - 52) velY *= -1;
		//if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
		
		if (y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.object.add(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

}
