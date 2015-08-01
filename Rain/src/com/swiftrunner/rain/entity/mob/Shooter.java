package com.swiftrunner.rain.entity.mob;

import java.util.List;

import com.swiftrunner.rain.entity.Entity;
import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;
import com.swiftrunner.rain.maths.Debug;
import com.swiftrunner.rain.maths.Vector2i;

public class Shooter extends Mob{
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	private Entity rand = null;
	
	private int time=0;
	private int xa=0, ya=0;
	
	
	public Shooter(int x, int y){
		this.x = x;
		this.y = y;
		sprite = Sprite.dummy;
	}
	
	
	public void update(){
		time++;
		if(time >= 100000) time = 1;
//		if(time % (random.nextInt(50) + 30) == 0){
//			xa = random.nextInt(3) - 1;
//			ya = random.nextInt(3) - 1;
//			if(random.nextInt(3) == 0){
//				xa = 0;
//				ya = 0;
//			}
//		}
//		
//		if (walking) animSprite.update();
//		else animSprite.setFrame(0);
//		
//		if(ya < 0)		{ dir = Direction.UP; animSprite = up; }
//		else if(ya > 0)	{ dir = Direction.DOWN; animSprite = down; }
//		if(xa < 0)		{ dir = Direction.LEFT; animSprite = left; }
//		else if(xa > 0)	{ dir = Direction.RIGHT; animSprite = right; }
//		
//		if(xa != 0 || ya != 0) {
//			move(xa, ya);
//			walking = true;
//		}
//		else{
//			walking = false;
//		}
		
		shootRandom();
	}
	
	
	private void shootClosest(){
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());
		
		double min = 0;
		Entity closest = null;
		
		for(int i=0; i<entities.size(); i++){
			Entity e = entities.get(i);
			double distance = Vector2i.getDistance(new Vector2i(x, y), new Vector2i(e.getX(), e.getY()));
			if(i == 0 || distance < min){
				min = distance;
				closest = e;
			}
		}
		
		if(closest != null){
			double dx = closest.getX() - this.x;
			double dy = closest.getY() - this.y;
			double fireDir = Math.atan2(dy, dx);			
			shoot(x, y, fireDir);
		}
	}
	
	
	private void shootRandom(){
		List<Entity> entities = level.getEntities(this, 500);
		entities.add(level.getClientPlayer());
		if(time % 60 == 0){
			int index = random.nextInt(entities.size());
			rand = entities.get(index);
		}

		
		if(rand != null){
			double dx = rand.getX() - this.x;
			double dy = rand.getY() - this.y;
			double fireDir = Math.atan2(dy, dx);			
			shoot(x, y, fireDir);
		}
	}
	
	
	public void render(Screen screen){ 
		sprite = animSprite.getSprite();
		Debug.drawRect(screen, 40, 40, 32, 32, false);
		screen.renderMob(x, y, this, true); 
	}
}
