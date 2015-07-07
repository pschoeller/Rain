package com.swiftrunner.rain.graphics;


public class Sprite {
	
	private final int SIZE;
	private int x, y;
	private int width, height;
	private int a, b, c, d;
	private int[] pixels;
	private SpriteSheet sheet;
	
	
	public static Sprite grass = new Sprite(16,0, 1, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16,0, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16,1, 1, SpriteSheet.tiles);
	public static Sprite voidSprite	= new Sprite(16, 0x1b87e0);
	
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	
	public static Sprite player_forward_1 = new Sprite(32, 0, 0, SpriteSheet.player);
	public static Sprite player_forward_2 = new Sprite(32, 0, 1, SpriteSheet.player);
	public static Sprite player_forward_3 = new Sprite(32, 0, 2, SpriteSheet.player);
	public static Sprite player_side_1 = new Sprite(32, 1, 0, SpriteSheet.player);
	public static Sprite player_side_2 = new Sprite(32, 1, 1, SpriteSheet.player);
	public static Sprite player_side_3 = new Sprite(32, 1, 2, SpriteSheet.player);
	public static Sprite player_back_1 = new Sprite(32, 2, 0, SpriteSheet.player);
	public static Sprite player_back_2 = new Sprite(32, 2, 1, SpriteSheet.player);
	public static Sprite player_back_3 = new Sprite(32, 2, 2, SpriteSheet.player);
	
	public static Sprite wizard_projectile = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	public static Sprite particle_normal = new Sprite(3, 0xffaaaaaa);

	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.width = size;
		this.height = size;
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	
	
	public Sprite(int size, int color){
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[width*height];
		setColor(color);
	}
	
	
	public Sprite(int width, int height, int color){
		this.width = width;
		this.height = height;
		SIZE = -1;
		pixels = new int[width*height];
		setColor(color);
	}
	
	
	public int[] getPixels() { return pixels; }
	public int getSIZE() { return SIZE; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; } 
	
	
	private void setColor(int color) {
		for(int i=0; i<width*height; i++){
			pixels[i] = color;
		}
	}


	private void load(){
		for(int y=0; y<height; y++){
			for (int x=0; x<width; x++){
				pixels[x+y*SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSIZE()];
			}
		}
	}
}
