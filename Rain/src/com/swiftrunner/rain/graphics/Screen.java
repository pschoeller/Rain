package com.swiftrunner.rain.graphics;

import java.util.Random;

import com.swiftrunner.rain.entity.mob.Player;
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
	
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		int spriteHeight = sprite.getHeight(); 
		int spriteWidth = sprite.getWidth(); 
		for(int y=0; y<spriteHeight; y++){
			int ya =  y + yp;
			for(int x=0; x<spriteWidth; x++){
				int xa = x + xp;
				if(xa < -spriteWidth || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = sprite.getPixels()[x + y * spriteWidth];
				if(color != 0xffff00ff) pixels[xa + ya * width] = color;
			}
		}
	}
	
	
	public void renderPlayer(int xp, int yp, Sprite player, int flip){
		xp -= xOffset;
		yp -= yOffset;
		int tileSpriteSize = player.getSIZE(); 
		for(int y=0; y<tileSpriteSize; y++){
			int ya =  y + yp;
			int ys = y;
			if(flip == 2 || flip == 3) { ys = (tileSpriteSize-1) - y; }
			for(int x=0; x<tileSpriteSize; x++){
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3) { xs = (tileSpriteSize-1) - x; }
				if(xa < - player.getSIZE() || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = player.getPixels()[xs + ys * player.getSIZE()];
				if(col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
}
