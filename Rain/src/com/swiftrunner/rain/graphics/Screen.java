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
	
	
	public void clear(){
		for(int i=0; i<pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	
	public void render(int xOffset, int yOffset){
		for(int y=0; y<height; y++){
			int yy = y + yOffset;
			//if(yy < 0 || yy >= height) break;
			for(int x=0; x<width; x++){
				int xx = x + xOffset;
				//if(xx < 0 || xx >= width) break;
				int tileIndex = ((xx>>4) & MAP_SIZE_MASK) + ((yy>>4) & MAP_SIZE_MASK) * MAP_SIZE;
				pixels[x+y*width] = Sprite.grass.getPixels()[(x&15) + (y&15) * Sprite.grass.getSIZE()];
			}
		}
	}
}
