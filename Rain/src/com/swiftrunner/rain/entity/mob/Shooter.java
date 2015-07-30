package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;

public class Shooter extends Mob{
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	
	private int time=0;
	private int xa=0, ya=0;
	
	
	public Shooter(int x, int y){
		this.x = x;
		this.y = y;
		sprite = Sprite.dummy;
	}
	
	
	public void update(){
		Player p = level.getClientPlayer();
		double dx = p.getX() - this.x;
		double dy = p.getY() - this.y;
		double fireDir = Math.atan2(dy, dx);
		
		shoot(x, y, fireDir);
		
		time++;
		if(time >= 100000) time = 1;
		if(time % (random.nextInt(50) + 30) == 0){
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if(random.nextInt(3) == 0){
				xa = 0;
				ya = 0;
			}
		}
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if(ya < 0)		{ dir = Direction.UP; animSprite = up; }
		else if(ya > 0)	{ dir = Direction.DOWN; animSprite = down; }
		if(xa < 0)		{ dir = Direction.LEFT; animSprite = left; }
		else if(xa > 0)	{ dir = Direction.RIGHT; animSprite = right; }
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
	}
	
	
	public void render(Screen screen){ 
		sprite = animSprite.getSprite(); 
		screen.renderMob((int)x, (int)y, this, true); 
	}
}
