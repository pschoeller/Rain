package com.swiftrunner.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SpriteSheet {
	private String path;
	private final int SIZE;
	private int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheets/spritesheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/spritesheets/spawn-level.png", 48);
	
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	
	public int getSIZE() { return SIZE; }
	public int[] getPixels() { return pixels; }
	
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0,  0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
