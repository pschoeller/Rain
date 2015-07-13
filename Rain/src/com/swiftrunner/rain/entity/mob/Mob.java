package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.entity.projectile.WizardProjectile;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;


public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = -1;
	protected boolean moving = false;
	
	public void move(int xa, int ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa>0) dir = 1;
		if(xa<0) dir = 3;
		if(ya>0) dir = 2;
		if(ya<0) dir = 0;
		
		if(!collision(xa, ya)){
			x += xa;
			y += ya;
		}
	}
	
	
	protected void shoot(int x, int y, double dir){
		level.add(new WizardProjectile(x, y, dir));
	}
	
	
	private boolean collision(int xa, int ya){
		boolean solid = false;
		for(int c=0; c<4; c++){
			int xt = ((x + xa) + c % 2 * 14 - 8)/16;
			int yt = ((y + ya) + c / 2 * 12 + 3)/16;
			if(level.getTile(xt, yt).solid()) return true;
		}
		return solid;
	}
	
	
	public abstract void update();
	public abstract void render(Screen screen);
}
