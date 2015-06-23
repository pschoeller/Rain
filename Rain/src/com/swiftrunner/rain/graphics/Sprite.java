package com.swiftrunner.rain.graphics;




public class Sprite {
	
	private final int SIZE;
	private int x, y;
	private int[] pixels;
	private SpriteSheet sheet;
	
	
	public static Sprite grass = new Sprite(16,0, 1, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1b87e0);
	
	public static Sprite player_forward_1 = new Sprite(32, 0, 0, SpriteSheet.player);
	public static Sprite player_forward_2 = new Sprite(32, 0, 1, SpriteSheet.player);
	public static Sprite player_forward_3 = new Sprite(32, 0, 2, SpriteSheet.player);
	public static Sprite player_back_1 = new Sprite(32, 2, 0, SpriteSheet.player);
	public static Sprite player_back_2 = new Sprite(32, 2, 1, SpriteSheet.player);
	public static Sprite player_back_3 = new Sprite(32, 2, 2, SpriteSheet.player);
	public static Sprite player_left_1 = new Sprite(32, 3, 0, SpriteSheet.player);
	public static Sprite player_left_2 = new Sprite(32, 3, 1, SpriteSheet.player);
	public static Sprite player_left_3 = new Sprite(32, 3, 2, SpriteSheet.player);
	public static Sprite player_right_1 = new Sprite(32, 1, 0, SpriteSheet.player);
	public static Sprite player_right_2 = new Sprite(32, 1, 1, SpriteSheet.player);
	public static Sprite player_right_3 = new Sprite(32, 1, 2, SpriteSheet.player);
	
	
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
