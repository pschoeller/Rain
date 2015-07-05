package com.swiftrunner.rain.entity;

import java.util.Random;

import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.level.Level;


public abstract class Entity {
	
	private boolean removed=false;
	
	protected int x, y;
	protected Level level;
	protected final Random random = new Random();
	
	
	public void update(){}
	public void render() {}
	public void render(Screen screen){}
	public void remove(){ removed = true; }
	public boolean isRemoved(){ return removed; }
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public void init(Level level) { this.level = level; }
}
