package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.graphics.Sprite;

public class WizardProjectile extends Projectile{

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 20;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = Sprite.wizard_projectile;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	
	public void update(){
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
