package com.epicGamer.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;
	protected float xF, yF;
	protected float velX, velY;
	protected Handler handler;
	protected ID id;
	protected boolean visible = true;
	protected String direction = "none";
	protected Camera camera;
	protected int screenX, screenY;
	


	public GameObject(int x, int y, ID id, Handler handler, Camera camera) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.xF = x;
		this.yF = y;
		this.handler = handler;
		this.camera = camera;
		handler.addObject(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public int getScreenX() {
		return screenX;
	}


	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}


	public int getScreenY() {
		return screenY;
	}


	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}


	public float getxF() {
		return xF;
	}


	public void setxF(float xF) {
		this.xF = xF;
	}


	public float getyF() {
		return yF;
	}


	public void setyF(float yF) {
		this.yF = yF;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}





	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public float getVelX() {
		return velX;
	}


	public void setVelX(float velX) {
		this.velX = velX;
	}


	public float getVelY() {
		return velY;
	}


	public void setVelY(float velY) {
		this.velY = velY;
	}


	public ID getId() {
		return id;
	}


	public void setId(ID id) {
		this.id = id;
	}
	
	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	

	
}
