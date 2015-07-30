package com.swiftrunner.rain.entity.mob;

import java.util.List;

import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;
import com.swiftrunner.rain.level.Node;
import com.swiftrunner.rain.level.tile.Tile;
import com.swiftrunner.rain.maths.Vector2i;

public class Star extends Mob{
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	
	private int time=0;
	private double xa = 0;
	private double ya = 0;
	
	private List<Node> path = null;
	
	
	public Star(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 1.0;
		sprite = Sprite.dummy;
	}
	
	
	private void move(){
		List<Player> players = level.getPlayers(this, 85);
		
		if(players.size() > 0){
			xa = 0;
			ya = 0;
			int px = (int)level.getPlayerAt(0).getX();
			int py = (int)level.getPlayerAt(0).getY();
			Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
			Vector2i dest = new Vector2i(px >> 4, py >> 4);
			if(time % 3 == 0) path = level.findPath(start, dest);
			
			if(path != null){
				if(path.size() > 0){
					Vector2i vec = path.get(path.size() - 1).tile;
					if((int)x >> 4 < vec.getX()){ xa++; }
					if((int)x >> 4 > vec.getX()){ xa--; }
					if((int)y >> 4 < vec.getY()){ ya++; }
					if((int)y >> 4 > vec.getY()){ ya--; }
				}
			}
			
			if(xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			}
			else{
				walking = false;
			}
		}
	}

	
	public void update(){
		//if(time++ % 10000 == 0) time = 0;
		time++;
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
