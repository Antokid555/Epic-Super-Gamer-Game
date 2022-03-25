package com.epicGamer.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WallTile extends GameObject{
	private Player player;
	private int width = 40;
	private int left;
	private int right;
	private int top;
	private int bottom;

	public WallTile(int x, int y, ID id, Handler handler, Camera camera) {
		super(x, y, id, handler, camera);
		for (int i = handler.object.size() - 1;i >= 0; i--) {
			if(handler.object.get(i).id == ID.Player) {
				player = (Player) handler.object.get(i);
			}
		}
		
		left = x;
		right = x + width;
		top = y;
		bottom = y + width;
		
	}

	public void tick() {
		
		left = x;
		right = x + width;
		top = y;
		bottom = y + width;
		
		
		Collision(); 
		
		screenX = x + camera.getX();
		screenY = y + camera.getY();
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(screenX, screenY, width, width);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, width);
	}
	
	public void Collision() {
		// right side of player
				if((player.getX() + player.getWidth()) + player.velX >= left && player.getX() <= right && (player.getY() + player.getWidth() > top && player.getY() < bottom)) {
					player.setVelX(0);
					player.setxF(left - player.getWidth());
				} // left side
				if((player.getX() + player.velX <= right && player.getX() >= left && (player.getY() + player.getWidth() > top && player.getY() < bottom))) {
					player.setVelX(0);
					player.setxF(right + 1f);
				} // top
				if((player.getY() + player.velY <= bottom && player.getY() >= bottom && (player.getX() + player.getWidth() > left && player.getX() < right))) {
					player.setVelY(0);
					player.setyF(bottom);
					
				} // Collision for player thing idk bottom
				if((player.getY() + player.getHeight() + player.velY >= top && player.getY() + player.getHeight() <= top && (player.getX() + player.getWidth() > left && player.getX() < right))) {
					player.setVelY(0);
					player.setyF(top - player.getHeight());
				}
	}
	
	

}
