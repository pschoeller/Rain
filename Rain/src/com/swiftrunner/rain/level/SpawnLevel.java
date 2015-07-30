package com.swiftrunner.rain.level;

import com.swiftrunner.rain.entity.mob.Chaser;
import com.swiftrunner.rain.entity.mob.Dummy;
import com.swiftrunner.rain.entity.mob.Shooter;
import com.swiftrunner.rain.entity.mob.Star;


public class SpawnLevel extends Level{
	
	
	public SpawnLevel(String path) { super(path); 
			TileCoordinate spawnCoord = new TileCoordinate(25, 62);
//			add(new Chaser(spawnCoord.getX(), spawnCoord.getY()));
//			
//			spawnCoord.setTileCoordinate(17, 18);
//			add(new Star(spawnCoord.getX(), spawnCoord.getY()));
			
			spawnCoord.setTileCoordinate(20, 55);
			add(new Shooter(spawnCoord.getX(), spawnCoord.getY()));
			spawnCoord.setTileCoordinate(20, 48);
			add(new Shooter(spawnCoord.getX(), spawnCoord.getY()));
			
			spawnCoord.setTileCoordinate(15, 52);
			add(new Dummy(spawnCoord.getX(), spawnCoord.getY()));
			
//			for(int i=0; i<10; i++){
//				spawnCoord.setTileCoordinate(22, 62);
//				add(new Dummy(spawnCoord.getX(), spawnCoord.getY()));
//			}
	}	
	
	
	protected void generateLevel() {}
}
