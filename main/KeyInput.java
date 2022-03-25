package com.epicGamer.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	private boolean[] playerInput = new boolean[4];
	
	private Game game;
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
				
		for (int i = 0; i < handler.object.size(); i++) {
			
			//Key Inputs for player things
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {playerInput[0] = true;}
				if(key == KeyEvent.VK_S) {playerInput[1] = true;}
				if(key == KeyEvent.VK_A) {playerInput[2] = true;}
				if(key == KeyEvent.VK_D) { playerInput[3] = true;}
				
				if(!playerInput[0] && !playerInput[1] && !playerInput[2] && !playerInput[3]) {
					tempObject.setDirection("none");
				}
				
				if(playerInput[0] && !(playerInput[2] || playerInput[3])) {tempObject.setDirection("up");}
				else if(playerInput[1] && !(playerInput[2] || playerInput[3])) {tempObject.setDirection("down");}
				else if(playerInput[2] && !(playerInput[0] || playerInput[1])) {tempObject.setDirection("left");}
				else if(playerInput[3] && !(playerInput[0] || playerInput[1])) {tempObject.setDirection("right");}
				
				if(playerInput[0] && playerInput[2]) {tempObject.setDirection("upleft");}
				else if(playerInput[0] && playerInput[3]) {tempObject.setDirection("upright");}
				else if(playerInput[1] && playerInput[2]) {tempObject.setDirection("downleft");}
				else if(playerInput[1] && playerInput[3]) {tempObject.setDirection("downright");}
				
			}
						
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);

		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				// key events for p1
					if(key == KeyEvent.VK_W) playerInput[0] = false;
					if(key == KeyEvent.VK_S) playerInput[1] = false;
					if(key == KeyEvent.VK_A) playerInput[2] = false;
					if(key == KeyEvent.VK_D) playerInput[3] = false;
					
					if(!playerInput[0] && !playerInput[1] && !playerInput[2] && !playerInput[3]) {
						tempObject.setDirection("none");
					}
					
					if(playerInput[0] && !(playerInput[2] || playerInput[3])) {tempObject.setDirection("up");}
					else if(playerInput[1] && !(playerInput[2] || playerInput[3])) {tempObject.setDirection("down");}
					else if(playerInput[2] && !(playerInput[0] || playerInput[1])) {tempObject.setDirection("left");}
					else if(playerInput[3] && !(playerInput[0] || playerInput[1])) {tempObject.setDirection("right");}
					
					if(playerInput[0] && playerInput[2]) {tempObject.setDirection("upleft");}
					else if(playerInput[0] && playerInput[3]) {tempObject.setDirection("upright");}
					else if(playerInput[1] && playerInput[2]) {tempObject.setDirection("downleft");}
					else if(playerInput[1] && playerInput[3]) {tempObject.setDirection("downright");}
			}
			
		}
	}
	
}
