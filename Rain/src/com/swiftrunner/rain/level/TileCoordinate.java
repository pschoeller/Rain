package com.swiftrunner.rain.level;

public class TileCoordinate {
	
	private int x, y;
	private final int TILE_WIDTH = 16;
	private final int TILE_HEIGHT = 16;
	
	
	public TileCoordinate(int x, int y){ setTileCoordinate(x, y); }
	
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	
	public int[] getXY(){
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
	
	
	public void setTileCoordinate(int x, int y){
		this.x = x * TILE_WIDTH;
		this.y = y * TILE_HEIGHT;
	}
}
