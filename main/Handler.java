package com.epicGamer.main;

import java.awt.Graphics;
import java.util.ArrayList;


public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public void tick() {
		for (int i = object.size() - 1; i >= 0; i--) {
			if(object.size() < i) break;
			object.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = object.size() - 1; i >= 0; i--) {
			if (object.size() < i) break;
			
			object.get(i).render(g);
		}
		
	}
	
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	
}
