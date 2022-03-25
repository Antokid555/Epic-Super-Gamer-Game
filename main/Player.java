package com.epicGamer.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private int width = 32, height = 32;
	private float movementSpeed = 0.5f;
	private float friction = 0.2f;

	public Player(int x, int y, ID id, Handler handler, Camera camera) {
		super(x, y, id, handler, camera);
	}

	public void tick() {
		xF += velX;
		yF += velY;
		
		camera.setX(-x  + getScreenX());
		camera.setY(-y + getScreenY());
		
		directionControls();
		
		//xF = UsefulThings.clamp(xF, Game.WIDTH_CONTENT - (width), 0f);
		//yF = UsefulThings.clamp(yF, Game.HEIGHT_CONTENT - (height), 0f);
		
		
		// speed limiters super cringe
		if(velX > 0 && velX - friction > 0) {velX -= friction;}
		else if (velX > 0){velX = 0f;}
		if(velX < 0 && velX + friction < 0) {velX += friction;}
		else if (velX < 0) {velX = 0f;}
		if(velY > 0 && velY - friction > 0) {velY -= friction;}
		else if (velY > 0) {velY = 0f;}
		if(velY < 0 && velY + friction < 0) {velY += friction;}
		else if (velY < 0) {velY = 0f;}
		
		velX = UsefulThings.clamp(velX, 10, -10);
		velY = UsefulThings.clamp(velY, 10, -10);
		
		
		x = (int)xF;
		y = (int)yF;
		screenX = (int) (Game.WIDTH_CONTENT/2 + width/2 + velX*2);
		screenY = (int) (Game.HEIGHT_CONTENT/2 + height/2 + velY*2);

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(screenX, screenY, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, width);
	}
	
	public void directionControls() {
		//case check for movement
				switch(direction) {
				case("up"):
					velY += -movementSpeed;
					break;
				case("down"):
					velY += movementSpeed;
					break;
				case("left"):
					velX += -movementSpeed;
					break;
				case("right"):
					velX += movementSpeed;
					break;
				case("upleft"):
					velX += -movementSpeed;
					velY += -movementSpeed;
					break;
				case("upright"):
					velX += movementSpeed;
					velY += -movementSpeed;
					break;
				case("downleft"):
					velX += -movementSpeed;
					velY += movementSpeed;
					break;
				case("downright"):
					velX += movementSpeed;
					velY +=movementSpeed;
					break;
				}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
