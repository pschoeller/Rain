package com.swiftrunner.rain.graphics;



public class Sprite {
	
	private final int SIZE;
	private int x, y;
	private int[] pixels;
	private SpriteSheet sheet;
	
	
	public static Sprite grass = new Sprite(16,0, 1, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1b87e0);
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	
	
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	
	public int[] getPixels() { return pixels; }
	public int getSIZE() { return SIZE; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	
	private void setColor(int color) {
		for(int i=0; i<SIZE*SIZE; i++){
			pixels[i] = color;
		}
	}


	private void load(){
		for(int y=0; y<SIZE; y++){
			for (int x=0; x<SIZE; x++){
				pixels[x+y*SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSIZE()];
			}
		}
	}
}
