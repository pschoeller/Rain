package com.swiftrunner.rain.graphics;


public class Sprite {
	
	private final int SIZE;
	private int x, y;
	private int width, height;
	private int[] pixels;
	
	protected SpriteSheet sheet;
	
	
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
	public static Sprite wizard_arrow = new Sprite(16, 1, 0, SpriteSheet.projectile_wizard);
	
	public static Sprite particle_normal = new Sprite(3, 0xffaaaaaa);
	
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);
	
	
	protected Sprite(int[] pixels, int width, int height){
		this.SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		System.arraycopy(pixels, 0, this.pixels, 0, pixels.length);
	}
	
	
	protected Sprite(SpriteSheet sheet, int width, int height){
		this.SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	
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
	
	
	public static Sprite[] split(SpriteSheet sheet){
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.getSPRITEWIDTH() * sheet.getSPRITEHEIGHT());
		Sprite[] sprites = new Sprite[amount];
		int sheetWidth = sheet.getWidth();
		int sheetHeight = sheet.getHeight();
		int spriteWidth = sheet.getSPRITEWIDTH();
		int spriteHeight = sheet.getSPRITEHEIGHT();
		int current = 0;
		int[] pixels = new int[spriteWidth * spriteHeight];
		
		for(int yp = 0; yp < sheetHeight / spriteHeight; yp++){
			for(int xp = 0; xp < sheetWidth / spriteWidth; xp++){
				for(int y=0; y < spriteHeight; y++){
					for(int x=0; x < spriteWidth; x++){
						int xo = x + xp * spriteWidth;
						int yo = y + yp * spriteHeight;
						pixels[x + y * spriteWidth] = sheet.getPixels()[xo + yo * sheetWidth];
					}
				}
				
				sprites[current++] = new Sprite(pixels, spriteWidth, spriteHeight);
			}
		}
		
		return sprites;
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
				pixels[x+y*width] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSPRITEWIDTH()];
			}
		}
	}
	
	
	public static Sprite rotate(Sprite sprite, double angle){
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}
	
	
	private static int[] rotate(int[] pixels, int width, int height, double angle){
		int [] result = new int[width * height];
		double nx_x = rotX(-angle, 1.0, 0.0);
		double nx_y = rotY(-angle, 1.0, 0.0);
		double ny_x = rotX(-angle, 0.0, 1.0);
		double ny_y = rotY(-angle, 0.0, 1.0);
		double x0 = rotX(-angle, -width/2.0, -height/2.0) + width/2.0;
		double y0 = rotY(-angle, -width/2.0, -height/2.0) + height/2.0;
		
		for(int y=0; y<height; y++){
			double x1 = x0;
			double y1 = y0;
			for(int x=0; x<width; x++){
				int xx = (int)x1;
				int yy = (int)y1;
				int col=0;
				if(xx < 0 || xx >= width || yy < 0 || yy >= height) col = 0xffff00ff;
				else col = pixels[xx + yy * width];
				result[x + y * width] = col;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}
		
		return result;
	}
	
	
	private static double rotX(double angle, double x, double y){
		double cos = Math.cos(angle - Math.PI/2);
		double sin = Math.sin(angle - Math.PI/2);
		
		return x*cos + y*-sin;
	}
	
	
	private static double rotY(double angle, double x, double y){
		double cos = Math.cos(angle - Math.PI/2);
		double sin = Math.sin(angle - Math.PI/2);
		
		return x*sin + y*cos;
	}
}
