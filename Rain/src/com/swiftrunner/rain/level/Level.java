package com.swiftrunner.rain.level;

import com.swiftrunner.rain.graphics.Screen;

public class Level {
	
	private int width, height;
	private int[] tiles;
	
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width*height];
		
		generateLevel();
	}
	
	
	public Level(String path){
		loadLevel(path);
		generateLevel();
	}
	
	
	private void generateLevel(){}
	private void loadLevel(String path){}
	public void update(){}
	private void time(){}
	
	
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0=xScroll>>4; int x1=(xScroll+screen.getWidth()+16)>>4;
		int y0=yScroll>>4; int y1=(yScroll+screen.getHeight()+16)>>4;
		
		for(int y=y0; y<y1; y++){
			for(int x=x0; x<x1; x++){
				//getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	
	public void getTile(int x, int y){
		
	}
}
