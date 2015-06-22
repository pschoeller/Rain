package com.swiftrunner.rain.graphics;

import java.util.Random;

import com.swiftrunner.rain.level.tile.Tile;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private int xOffset, yOffset;
	
	private Random random = new Random();
	
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for(int i=0; i<tiles.length; i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	
	public int[] getPixels(){ return pixels; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}


	public void clear(){
		for(int i=0; i<pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	
	public void renderTile(int xp, int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		int tileSpriteSize = tile.getSprite().getSIZE(); 
		for(int y=0; y<tileSpriteSize; y++){
			int ya =  y + yp;
			for(int x=0; x<tileSpriteSize; x++){
				int xa = x + xp;
				if(xa < -tile.getSprite().getSIZE() || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.getSprite().getPixels()[x + y * tile.getSprite().getSIZE()];
			}
		}
	}
}
