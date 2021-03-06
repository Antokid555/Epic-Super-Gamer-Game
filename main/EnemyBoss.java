package com.tutoral.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject{
	
	private Handler handler;
	private Random r = new Random();
	
	private int timer = 70;
	private int timer2 = 50;

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		velY = 2;
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (timer <= 0) velY = 0;
		else timer--;
		
		if (timer <= 0) timer2--;
		if (timer2 <= 0) {
			if (velX == 0) velX = 2;
			
			if (velX > 0) velX += 0.01f;
			else if (velX < 0) velX -= 0.01f;
			
			int spawn = r.nextInt(10);
			if (spawn == 0) handler.addObject(new EnemyBossBullet((int) x+48, (int)y+48, ID.BasicEnemy, handler));
		}
		
		velX = Game.clamp(velX, -10, 10);
		
		x = Game.clamp(x, 0, Game.WIDTH - 105);
		//y = Game.clamp(y, 0, Game.HEIGHT - 92);
		//if (y <= 0 || y >= Game.HEIGHT - 92) velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 105) velX *= -1;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 96, 96);
	}


	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 96, 96);
	}

}
