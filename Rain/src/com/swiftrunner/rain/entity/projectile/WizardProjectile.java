package com.swiftrunner.rain.entity.projectile;

import com.swiftrunner.rain.graphics.Screen;

public class WizardProjectile extends Projectile{

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = sprite.grass;
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	
	public void update(){
		move();
	}
	
	
	protected void move(){
		x += nx;
		y += ny;
	}
	
	
	public void render(Screen screen){
		screen.renderTile(x, y, sprite);
	}
}
