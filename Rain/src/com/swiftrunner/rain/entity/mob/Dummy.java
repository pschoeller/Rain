package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;

public class Dummy extends Mob{
	
	
	public Dummy(int x, int y){
		this.x = x << 32;
		this.y = y << 32;
		sprite = Sprite.player_back_1;
	}
	
	
	public void update(){}
	
	
	public void render(Screen screen){
		screen.renderSprite(x, y, sprite, true, 0);
	}
}
