package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.entity.spawner.ParticleSpawner;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;

public class WizardProjectile extends Projectile{

	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = random.nextInt(100) + 150;
		speed = 1;
		damage = 20;
		rateOfFire = 8;
		sprite = Sprite.wizard_arrow;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	
	public void update(){
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 7, 4, 4)) { 
			level.add(new ParticleSpawner((int)x, (int)y, 44, 50, level));
			remove(); 
		} 
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
