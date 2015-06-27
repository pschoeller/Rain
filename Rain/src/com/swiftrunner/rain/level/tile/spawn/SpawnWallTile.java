package com.swiftrunner.rain.level.tile.spawn;

import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.level.tile.Tile;


public class SpawnWallTile extends Tile{
	
	public SpawnWallTile(Sprite sprite) {
		super(sprite);
		
	}

	public boolean solid(){ return true; }
}
