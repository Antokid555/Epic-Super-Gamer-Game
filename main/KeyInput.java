package com.tutoral.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private HUD hud;
	
	private boolean[] playerInput = new boolean[4];
	
	private Game game;
	
	public KeyInput(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Key Inputs for Gamer things
		if (key == KeyEvent.VK_Q) hud.setLevel(hud.getLevel() + 1);
		
		for (int i = 0; i < handler.object.size(); i++) {
			
			//Key Inputs for player things
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {tempObject.setVelY(-5); playerInput[0] = true;}
				if(key == KeyEvent.VK_S) {tempObject.setVelY(+5); playerInput[1] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); playerInput[2] = true;}
				if(key == KeyEvent.VK_D) {tempObject.setVelX(+5); playerInput[3] = true;}
			}
						
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		if (key == KeyEvent.VK_P) {
			if(game.gameState == Game.STATE.Game) {
				if (Game.paused) Game.paused = false;
				else Game.paused = true;
			}
			
		}

		
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
				if (!playerInput[0] && !playerInput[1]) {
					tempObject.setVelY(0);
				}
				if (!playerInput[2] && !playerInput[3]) {
					tempObject.setVelX(0);
				}
			}
			
		}
	}
	
}
