package com.swiftrunner.rain.level;

import com.swiftrunner.rain.entity.mob.Chaser;
import com.swiftrunner.rain.entity.mob.Dummy;


public class SpawnLevel extends Level{
	
	
	public SpawnLevel(String path) { super(path); 
			TileCoordinate newSpawn = new TileCoordinate(22, 62);
			add(new Chaser(newSpawn.getX(), newSpawn.getY()));
	}	
	
	
	protected void generateLevel() {}
}
