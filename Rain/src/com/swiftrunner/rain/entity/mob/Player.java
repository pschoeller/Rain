package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.Game;
import com.swiftrunner.rain.entity.projectile.WizardProjectile;
import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;
import com.swiftrunner.rain.input.Keyboard;
import com.swiftrunner.rain.input.Mouse;



public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int fireRate = 0;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	
	
	public Player(Keyboard input){
		this.input = input;
		sprite = Sprite.player_back_1;
	}
	
	
	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_back_1;
		fireRate = WizardProjectile.getRateOfFire();
	}
	
	
	public Sprite getSprite() { return sprite; }
	
	
	public void update(){
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if(fireRate > 0) { fireRate--; }
		
		int xa=0, ya=0;
		
		if(input.up)			{ ya -= 2; animSprite = up; }
		else if(input.down)		{ ya += 2; animSprite = down; }
		if(input.left)			{ xa -= 2; animSprite = left; }
		else if(input.right)	{ xa += 2; animSprite = right; }
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
		
		clear();
		updateShooting();
	}
	
	
	private void clear() {
		for(int i=0; i<level.getProjectiles().size(); i++){
			if(level.getProjectiles().get(i).isRemoved()){ level.getProjectiles().remove(i); }
		}
	}


	private void updateShooting() {		
		if(Mouse.getB() == 1 && fireRate <= 0){
			double dx = Mouse.getX() - Game.getWindowWidth()/2;
			double dy = Mouse.getY() - Game.getWindowHeight()/2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.getRateOfFire();
		}
	}


	public void render(Screen screen){
		sprite = animSprite.getSprite();
		screen.renderSprite(x, y, sprite, true);	
	}
}
