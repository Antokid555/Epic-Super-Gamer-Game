package com.tutoral.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	private int greenValue = 255;
	
	public static int HEALTH = 100;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		HEALTH = (int)Game.clamp(HEALTH, 0, 100);
		greenValue = (int)Game.clamp(greenValue, 0, 255);
		
		greenValue = (int)(HEALTH*2.5);
		
		score++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		
		
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	
}
