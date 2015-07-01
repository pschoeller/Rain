package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	protected int distance;
	protected double angle;
	protected double x, y;
	protected static Sprite sprite;
	protected double nx, ny;
	protected double speed, rateOfFire, range, damage;
	
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	
	protected void move(){
		
	}
	
	
	public void render(Screen screen){
		screen.renderSprite((int)x, (int)y, sprite);
	}
}
