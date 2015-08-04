package com.swiftrunner.rain.graphics;

public class Font {
	
	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16);
	private static Sprite[] characters = Sprite.split(font);
	
	public Font(){
		
	}
}
