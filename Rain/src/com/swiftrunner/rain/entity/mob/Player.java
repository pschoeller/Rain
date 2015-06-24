package com.swiftrunner.rain.entity.mob;

import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.input.Keyboard;



public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private int flip = 0;
	private boolean walking = false;
	
	
	public Player(Keyboard input){
		this.input = input;
		sprite = Sprite.player_back_1;
	}
	
	
	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_back_1;
	}
	
	
	public Sprite getSprite() { return sprite; }
	
	
	public void update(){
		int xa=0, ya=0;
		
		if(anim<7500) anim++;
		else anim = 0;
		
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
	}
	
	
	public void render(Screen screen){
		if(dir == 0) {
			sprite = Sprite.player_forward_1;
			if(walking){
				if(anim%20 > 10){ sprite = Sprite.player_forward_2; } 
				else { sprite = Sprite.player_forward_3; }
			}
		}
		
		if(dir == 1) {
			flip = 0;
			sprite = Sprite.player_side_1;
			if(walking){
				if(anim%20 > 10) { sprite = Sprite.player_side_2; }
				else { sprite = Sprite.player_side_3; }
			}
		}
		
		if(dir == 2) {
			sprite = Sprite.player_back_1;
			if(walking){
				if(anim%20 > 10) { sprite = Sprite.player_back_2; }
				else { sprite = Sprite.player_back_3; }
			}
		}
		
		if(dir == 3) {
			flip = 1;
			sprite = Sprite.player_side_1;
			if(walking){
				if(anim%20 > 10) { sprite = Sprite.player_side_2; }
				else { sprite = Sprite.player_side_3; }
			}
		}
		
		screen.renderPlayer(x, y, sprite, flip);	
	}
}
