package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.entity.Entity;
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
	
	
	private boolean collision(int xa, int ya){
		boolean solid = false;
		if(level.getTile((x + xa)/16, (y + ya)/16).solid()) return true;
		return solid;
	}
	
	
	public void update(){}
	public void render(){}
}
