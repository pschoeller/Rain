package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
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
}
