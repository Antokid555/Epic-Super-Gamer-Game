package com.epicGamer.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Floor extends GameObject{
	private Color color = Color.red;

	public Floor(int x, int y, ID id, Handler handler, Camera camera, Color color) {
		super(x, y, id, handler, camera);
		this.color = color;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(screenX, screenY, 50, 50);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
