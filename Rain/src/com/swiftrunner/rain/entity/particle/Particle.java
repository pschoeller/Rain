package com.swiftrunner.rain.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;

public class Particle extends Entity{
	
	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;
	private int lifeSpan;
	
	
	public Particle(int x, int y, int lifeSpan){
		this.x = x;
		this.y = y;
		this.lifeSpan = lifeSpan;
		sprite = Sprite.particle_normal;
		particles.add(this);
	}
	
	
	public Particle(int x, int y, int lifeSpan, int amount){
		this(x, y, lifeSpan);			
		for(int i=0; i < amount - 1; i++){
			particles.add(new Particle(x, y, lifeSpan));
		}
	}
	
	
	public void update(){
		
	}
	
	
	public void render(Screen screen){
		
	}
}
