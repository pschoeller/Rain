package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.entity.projectile.WizardProjectile;
import com.swiftrunner.rain.graphics.Screen;


public abstract class Mob extends Entity{
	
	protected boolean moving = false;
	protected boolean walking = false;
	protected enum Direction{ UP, DOWN, LEFT, RIGHT };
	protected Direction dir;
	
	public void move(double xa, double ya){
		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa>0) dir = Direction.RIGHT;
		if(xa<0) dir = Direction.LEFT;
		if(ya>0) dir = Direction.UP;
		if(ya<0) dir = Direction.DOWN;
		
		for(int y=0; y < Math.abs(ya); y++){
			if(!collision(abs(xa), ya)) this.y += abs(ya);
		}
		
		for(int x=0; x < Math.abs(xa); x++){
			if(!collision(xa, abs(ya))) this.x += abs(xa);
		}
	}
	
	
	private int abs(double value){
		if(value < 0) return -1;
		return 1;
	}
	
	
	protected void shoot(int x, int y, double dir){
		level.add(new WizardProjectile(x, y, dir));
	}
	
	
	private boolean collision(double xa, double ya){
		boolean solid = false;
		for(int c=0; c<4; c++){
			double xt = ((x + xa) - c % 2 * 16)/16;
			double yt = ((y + ya) - c / 2 * 16)/16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) ix = (int) Math.floor(xt);
			if(c / 2 == 0) iy = (int) Math.floor(yt);
			if(level.getTile(ix, iy).solid()) return true;
		}
		return solid;
	}
	
	
	public abstract void update();
	public abstract void render(Screen screen);
}
