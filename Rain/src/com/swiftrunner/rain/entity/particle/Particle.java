package com.swiftrunner.rain.entity.particle;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;

public class Particle extends Entity{
	
	private Sprite sprite;
	private int lifeSpan;
	
	protected double xx, yy, xa, ya;
	
	
	public Particle(int x, int y, int lifeSpan){
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.lifeSpan = lifeSpan;
		sprite = Sprite.particle_normal;
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}
	
	
	public void update(){
		this.xx += xa;
		this.yy += ya;
	}
	
	
	public void render(Screen screen){
		screen.renderSprite((int)xx, (int)yy, sprite, true, 0);
	}
}
