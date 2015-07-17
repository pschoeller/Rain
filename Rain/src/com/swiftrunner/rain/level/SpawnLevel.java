package com.swiftrunner.rain.level;

import com.swiftrunner.rain.entity.mob.Chaser;
import com.swiftrunner.rain.entity.mob.Dummy;


public class SpawnLevel extends Level{
	
	
	public SpawnLevel(String path) { super(path); 
		for(int i=0; i<5; i++){
			TileCoordinate newSpawn = new TileCoordinate(22, 62);
			add(new Chaser(newSpawn.getX(), newSpawn.getY()));
		}
		
		for(int i=0; i<5; i++){
			TileCoordinate newSpawn = new TileCoordinate(22, 62);
			add(new Dummy(newSpawn.getX(), newSpawn.getY()));
		}
	}	
	protected void generateLevel() {}
}
