package com.swiftrunner.rain.entity.mob;

import java.awt.Font;

import com.swiftrunner.rain.Game;
import com.swiftrunner.rain.entity.projectile.WizardProjectile;
import com.swiftrunner.rain.graphics.AnimatedSprite;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.graphics.Sprite;
import com.swiftrunner.rain.graphics.SpriteSheet;
import com.swiftrunner.rain.graphics.UI.UILabel;
import com.swiftrunner.rain.graphics.UI.UIManager;
import com.swiftrunner.rain.graphics.UI.UIPanel;
import com.swiftrunner.rain.input.Keyboard;
import com.swiftrunner.rain.input.Mouse;
import com.swiftrunner.rain.maths.Vector2i;



public class Player extends Mob{
	
	private String name;
	private Keyboard input;
	private Sprite sprite;
	private int fireRate = 0;
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	private AnimatedSprite animSprite = down;
	private UIManager ui;
	
	
	public Player( Keyboard input, String name){
		this.input = input;
		this.name = name;
		sprite = Sprite.player_back_1;
	}
	
	
	public Player(int x, int y, Keyboard input, String name){
		this.x = x;
		this.y = y;
		this.input = input;
		this.speed = 2.0;
		fireRate = WizardProjectile.getRateOfFire();
		sprite = Sprite.player_back_1;
		ui = Game.getUIManager();
		UIPanel panel = (UIPanel) new UIPanel(new Vector2i((300-80)*3, 0), new Vector2i(80*3, 168*3)).setColor(0x4f4f4f);
		ui.addPanel(panel);
		UILabel nameLabel = new UILabel(new Vector2i(40, 200), name);
		nameLabel.setColor(0xbbbbbb);
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
		nameLabel.toggleShadow();
		panel.addComponent(nameLabel);
	}
	
	
	public Sprite getSprite() { return sprite; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	
	public void update(){
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if(fireRate > 0) { fireRate--; }
		
		double xa=0, ya=0;
		
		if(input.up)			{ ya -= speed; animSprite = up; }
		else if(input.down)		{ ya += speed; animSprite = down; }
		if(input.left)			{ xa -= speed; animSprite = left; }
		else if(input.right)	{ xa += speed; animSprite = right; }
		
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
			shoot((int)x, (int)y, dir);
			fireRate = WizardProjectile.getRateOfFire();
		}
	}


	public void render(Screen screen){
		sprite = animSprite.getSprite();
		screen.renderSprite((int)x, (int)y, sprite, true);	
	}
}
