package com.swiftrunner.rain.entity.mob;

import java.util.List;

import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;

public class Chaser extends Mob{
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	
	private int time=0;
	private double xa = 0;
	private double ya = 0;
	
	
	public Chaser(int x, int y){
		this.x = x << 32;
		this.y = y << 32;
		this.speed = 0.5;
		sprite = Sprite.dummy;
	}
	
	
	private void move(){
		xa = 0;
		ya = 0;
		List<Mob> players = level.getPlayers(this, 75);

		if(players.size() > 0){ 
			Mob player = players.get(0);
			if(x < player.getX()) xa += speed;
			if(x > player.getX()) xa -= speed;
			if(y < player.getY()) ya += speed;
			if(y > player.getY()) ya -= speed;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
	}

	
	public void update(){
		move();
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if(ya < 0)		{ dir = Direction.UP; animSprite = up; }
		else if(ya > 0)	{ dir = Direction.DOWN; animSprite = down; }
		if(xa < 0)		{ dir = Direction.LEFT; animSprite = left; }
		else if(xa > 0)	{ dir = Direction.RIGHT; animSprite = right; }
	}
	
	
	public void render(Screen screen){
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, this, true);
	}

}
