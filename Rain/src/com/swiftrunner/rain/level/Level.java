package com.swiftrunner.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.entity.projectile.Projectile;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tiles;
	protected int[] tilesInt;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		this.tilesInt = new int[width * height];
		generateLevel();
	}
	
	
	public Level(String path){
		loadLevel(path);
		generateLevel();
	}
	
	
	protected void generateLevel(){}
	private void time(){}
	
	
	public void update(){
		for(int i=0; i<entities.size(); i++){
			entities.get(i).update();
		}
		
		for(int i=0; i<projectiles.size(); i++){
			projectiles.get(i).update();
		}
	}
	
	
	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w *h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch (IOException e){
			e.printStackTrace();
			System.out.println("Exception! Could not load level file.");
		}
	}
	
	
	
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0=xScroll>>4; int x1=(xScroll+screen.getWidth()+16)>>4;
		int y0=yScroll>>4; int y1=(yScroll+screen.getHeight()+16)>>4;
		
		for(int y=y0; y<y1; y++){
			for(int x=x0; x<x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
		
		for(int i=0; i<entities.size(); i++){
			entities.get(i).render(screen);
		}
		
		for(int i=0; i<projectiles.size(); i++){
			projectiles.get(i).render(screen);
		}
	}
	
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
//		if(tiles[x + y * width] == 0xff00ff00) return  Tile.grass;
//		if(tiles[x + y * width] == 0xffffff00) return Tile.flower;
//		if(tiles[x + y * width] == 0xff7f7f00) return Tile.rock;
		
		if(tiles[x+y*width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if(tiles[x+y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if(tiles[x+y*width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if(tiles[x+y*width] == Tile.col_spawn_water) return Tile.spawn_water;
		if(tiles[x+y*width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if(tiles[x+y*width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		
		return Tile.voidTile;
	}
	
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	
	public void addProjectile(Projectile p){
		projectiles.add(p);
	}
	
	
	public List<Projectile> getProjectiles(){
		return projectiles;
	}
}
