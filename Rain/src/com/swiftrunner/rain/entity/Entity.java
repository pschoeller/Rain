package com.swiftrunner.rain.entity;

import java.util.Random;

import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.level.Level;


public abstract class Entity {
	
	private boolean removed=false;
	
	protected double x, y;
	protected Level level;
	protected final Random random = new Random();
	protected Sprite sprite = null;
	
	
	public Entity() {}
	
	
	public Entity(int x, int y, Sprite sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	
	public void update(){}
	public void render(Screen screen) { if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, true); }
	public Sprite getSprite() { return sprite; }
	public void remove(){ removed = true; }
	public boolean isRemoved(){ return removed; }
	public double getX() { return this.x; }
	public void setX(int x) { this.x = x; }
	public double getY() { return this.y; }
	public void setY(int y) { this.y = y; }
	public void init(Level level) { this.level = level; }	
}
