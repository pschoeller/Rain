package com.swiftrunner.rain.level.tile;


import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.level.tile.spawn.SpawnFloorTile;
import com.swiftrunner.rain.level.tile.spawn.SpawnGrassTile;
import com.swiftrunner.rain.level.tile.spawn.SpawnHedgeTile;
import com.swiftrunner.rain.level.tile.spawn.SpawnWallTile;
import com.swiftrunner.rain.level.tile.spawn.SpawnWaterTile;

public class Tile {
	
	private Sprite sprite;
	
	
	public static Tile grass    = new GrassTile(Sprite.grass);
	public static Tile flower   = new FlowerTile(Sprite.flower);
	public static Tile rock     = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public final static int col_spawn_grass = 0xff00ff00;
	public final static int col_spawn_hedge = 0xff35853a;
	public final static int col_spawn_water = 0xff4bc6e5;
	public final static int col_spawn_wall1 = 0xff9f9f9f;
	public final static int col_spawn_wall2 = 0xff4f4f4f;
	public final static int col_spawn_floor = 0xff9f5e33;
	
	
	public Tile(Sprite sprite){ this.sprite = sprite; }
	public Sprite getSprite() { return this.sprite; }
	public boolean solid(){ return false; }
	
	
	public void render(int x, int y, Screen screen){
		screen.renderSprite(x<<4, y<<4, this.sprite, true);
	}
}
