package com.tutoral.main;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;

		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			
			hud.setLevel(hud.getLevel() + 1);
		
			if (hud.getLevel() == 2) {
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			else if (hud.getLevel() == 3) {
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			else if (hud.getLevel() == 4) {
				handler.object.add(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			}
			else if (hud.getLevel() == 5) {
				handler.object.add(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			}
			else if (hud.getLevel() == 6) {
				handler.object.add(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));			
				}
			else if (hud.getLevel() == 7) {
			//	handler.object.add(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				}
			else if (hud.getLevel() == 8) {
			//	handler.object.add(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			//	handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
				handler.object.add(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			}
			else if (hud.getLevel() == 9) {
				handler.object.add(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			else if (hud.getLevel() == 10) {
				handler.clearEnemys();
				handler.addObject(new EnemyBoss((Game.WIDTH/2) - 48, -120, ID.EnemyBoss, handler));
			}
		}
		
	}
	
}
