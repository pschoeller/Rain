package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.graphics.Sprite;

public class WizardProjectile extends Projectile{

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(100) + 150;
		speed = 5;
		damage = 20;
		rateOfFire = 8;
		sprite = Sprite.wizard_projectile;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	
	public void update(){
		if(level.tileCollision(x, y, nx, ny, sprite.getSIZE())) { remove(); } 
		move();
	}
	
	
	protected void move(){
			x += nx;
			y += ny;
		
		if(distance() > range) remove();
	}
	
	
	protected double distance(){
		double dist=0;
		dist = Math.sqrt(Math.abs(Math.pow(xOrigin - x, 2) + Math.pow(yOrigin - y, 2)));
		return dist;
	}
}
