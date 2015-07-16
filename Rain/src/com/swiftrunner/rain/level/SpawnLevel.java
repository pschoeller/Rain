package com.swiftrunner.rain.level;

import com.swiftrunner.rain.entity.mob.Dummy;


public class SpawnLevel extends Level{
	
	
	public SpawnLevel(String path) { super(path); 
		for(int i=0; i<100; i++){
			TileCoordinate newSpawn = new TileCoordinate(22, 62);
			add(new Dummy(newSpawn.getX(), newSpawn.getY()));
		}
	}	
	protected void generateLevel() {}
}
