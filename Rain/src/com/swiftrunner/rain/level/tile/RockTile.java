package com.swiftrunner.rain.level.tile;

import com.swiftrunner.rain.graphics.Sprite;

public class RockTile extends Tile{
	
	public RockTile(Sprite sprite) {
		super(sprite);
		
	}
	
	
	public boolean solid() { return true; }
}
