package com.swiftrunner.rain.graphics;

import java.util.Random;

import com.swiftrunner.rain.entity.mob.Chaser;
import com.swiftrunner.rain.entity.mob.Mob;
import com.swiftrunner.rain.entity.mob.Star;

public class Screen {
	
	private int width, height;
	private int[] pixels;	
	private final int MAP_SIZE = 64;
	private final int MAP_SIZE_MASK = MAP_SIZE - 1;
	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private int xOffset, yOffset;
	private final int ALPHA_COL = 0xffff00ff;
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
			int ys = y;
			for(int x=0; x<spriteWidth; x++){
				int xa = x + xp;
				int xs = x;
				if(xa < -spriteWidth || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = sprite.getPixels()[xs + ys * spriteWidth];
				if(color != ALPHA_COL) pixels[xa + ya * width] = color;
			}
		}
	}
	
	
	public void renderTextCharacter(int xp, int yp, Sprite sprite, int color, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		int spriteHeight = sprite.getHeight(); 
		int spriteWidth = sprite.getWidth(); 
		for(int y=0; y<spriteHeight; y++){
			int ya =  y + yp;
			int ys = y;
			for(int x=0; x<spriteWidth; x++){
				int xa = x + xp;
				int xs = x;
				if(xa < -spriteWidth || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int col = sprite.getPixels()[xs + ys * spriteWidth];
				if(color != ALPHA_COL && color == 0) pixels[xa + ya * width] = col;
				else if(color != ALPHA_COL || color != 0) pixels[xa + ya * width] = color;
			}
		}
	}
	
	
	public void renderMob(int xp, int yp, Mob mob, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		int spriteHeight = mob.getSprite().getHeight(); 
		int spriteWidth = mob.getSprite().getWidth(); 
		for(int y=0; y<spriteHeight; y++){
			int ya =  y + yp;
			int ys = y;
			for(int x=0; x<spriteWidth; x++){
				int xa = x + xp;
				int xs = x;
				if(xa < -spriteWidth || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = mob.getSprite().getPixels()[xs + ys * spriteWidth];
				if((mob instanceof Chaser) && (color == 0xff472bbf)) color = 0xffba0015;
				if((mob instanceof Star) && (color == 0xff472bbf)) color = 0xffe8e83a;
				if(color != ALPHA_COL) pixels[xa + ya * width] = color;
			}
		}
	}
	
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed, int flip){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		int spriteHeight = sheet.getSPRITEHEIGHT(); 
		int spriteWidth = sheet.getSPRITEWIDTH(); 
		for(int y=0; y<spriteHeight; y++){
			int ya =  y + yp;
			int ys = y;
			if(flip == 2 || flip == 3) { ys = (spriteHeight - 1) - y; }
			for(int x=0; x<spriteWidth; x++){
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip == 3) { xs = (spriteWidth - 1) - x; }
				if(xa < -spriteWidth || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int color = sheet.getPixels()[xs + ys * spriteWidth];
				if(color != ALPHA_COL) pixels[xa + ya * width] = color;
			}
		}
	}
	

	public void drawRect(int xp, int yp, int w, int h, int color, boolean fixed) {
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int x = xp; x < xp + w; x++){
			if(x < 0 || x >= this.width || yp >= this.height) continue;
			if(yp > 0) pixels[x + yp * this.width] = color;
			if(yp + h >= this.height) continue;
			if(yp + h > 0 && (yp + h) < this.height) pixels[x + (yp + h) * this.width] = color;
		}
		
		for(int y = yp; y <= yp + h; y++){
			if(xp >= this.width || y < 0 || y >= this.height) continue;
			if(xp > 0) pixels[xp + y * this.width] = color;
			if(xp + w >= this.width) continue;
			if((xp + w) > 0 && (xp + h) < this.width) pixels[(xp + w) + y * this.width] = color;
		}
	}
	
	
	public void fillRect(int xp, int yp, int w, int h, int color, boolean fixed){
		if(fixed){
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y=0; y<w; y++){
			int yo = yp + y;
			if(yo < 0 || yo >= this.height) continue;
			for(int x=0; x<w; x++){
				int xo = xp + x;
				if(xo < 0 || xo >= this.width) continue;
				pixels[xo+yo*this.width] = color;
			}
		}
	}
}
