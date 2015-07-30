package com.swiftrunner.rain.maths;

public class Vector2i {
	
	private int x, y;
	
	
	public Vector2i(){ set(0, 0); }
	public Vector2i(int x, int y){ set(x, y); }
	public Vector2i(Vector2i vector){ set(vector.x, vector.y); }
	
	public int getX(){ return this.x; }
	public Vector2i setX(int x){ this.x = x; return this; }
	public int getY(){ return this.y; }
	public Vector2i setY(int y){ this.y = y; return this; }
	public void set(int x, int y){ this.x = x; this.y = y; }
	
	
	public Vector2i add(Vector2i vector){
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}
	
	
	public Vector2i subtract(Vector2i vector){
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}
	
	
	public boolean equals(Object object){
		if(!(object instanceof Vector2i)){ return false; }
		Vector2i vec = (Vector2i)object;
		if((vec.getX() == this.x) && (vec.getY() == this.y)) { return true; }
		return false;
	}
	
	
	public static double getDistance(Vector2i v0, Vector2i v1){
		double x = v0.getX() - v1.getX();
		double y = v0.getY() - v1.getY();
		
		return Math.sqrt((x*x) + (y*y));
	}
}
