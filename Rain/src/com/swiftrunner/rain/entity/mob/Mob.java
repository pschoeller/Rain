package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.Sprite;


public abstract class Mob extends Entity{
	
	protected Sprite sprite;
	protected int dir = -1;
	protected boolean moving = false;
	
	
	public void move(int xa, int ya){
		if(xa>0) dir = 1;
		if(xa<0) dir = 3;
		if(ya>0) dir = 2;
		if(ya<0) dir = 0;
		
		if(!collision()){
			x += xa;
			y += ya;
		}
	}
	
	
	public void update(){}
	private boolean collision(){ return false; }
	public void render(){}
}
