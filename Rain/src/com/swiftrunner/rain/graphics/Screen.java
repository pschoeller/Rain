package com.swiftrunner.rain.graphics;

import java.util.Random;

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
	
	
	public void render(int xOffset, int yOffset){
		for(int y=0; y<height; y++){
			int yp = y + yOffset;
			if(yp < 0 || yp >= height) continue;
			for(int x=0; x<width; x++){
				int xp = x + xOffset;
				if(xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = Sprite.grass.getPixels()[(x&15) + (y&15) * Sprite.grass.getSIZE()];
			}
		}
	}
}
