package com.tutoral.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutoral.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// play button
		if (game.gameState == Game.STATE.Menu) {
			AudioPlayer.getSound("menu_sound").play();
			
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = Game.STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			if(mouseOver(mx, my, 210, 250, 200, 64)) System.exit(1); //Quit Button
			
			
			
		} else if (game.gameState == Game.STATE.Lose) {
			AudioPlayer.getSound("menu_sound").play();
			if(mouseOver(mx, my, 210, 300, 200, 64)) {
				game.gameState = Game.STATE.Menu; // try again button
				hud.setScore(0);
				hud.setLevel(0);
			}
		}
			
	}
		

	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		if (game.gameState == Game.STATE.Menu) {
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("Epic Super Gamer Game!", 10, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 280, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Quit", 280, 290);
		} else if (game.gameState == STATE.Lose) {
			
			g.setFont(fnt);
			g.setColor(Color.red);
			g.drawString("You Lost #RIPBOZO", 90, 70);
			
			g.setFont(fnt2);
			g.drawString("Cope + Cry Harder + Cope and Seeth", 60, 190);
			
			g.drawString("Score: " + hud.getScore(), 235, 260);
			
			g.drawRect(210, 300, 200, 64);
			g.drawString("Try Again", 240, 340);
			
		}
	}

}
