package com.tutoral.main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public void tick() {
		for (int i = object.size() - 1; i >= 0; i--) {
			if (object.size() < i) break;
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
		

	}
	
	public void render(Graphics g) {
		for (int i = object.size() - 1; i >= 0; i--) {
			if (object.size() < i) break;
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		

	}
	
	public void clearEnemys() {
		for (int i = object.size() - 1; i >= 0; i--) {
			GameObject tempObject = object.get(i);
			
			if (tempObject.getId() != ID.Player) removeObject(tempObject);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
