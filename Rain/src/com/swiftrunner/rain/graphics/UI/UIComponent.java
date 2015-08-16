package com.swiftrunner.rain.graphics.UI;

import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.maths.Vector2i;

public class UIComponent {
	
	protected Vector2i position, offset;
	protected int bgColor;
	protected int fgColor;
	
	
	public UIComponent(Vector2i position){
		this.position = position;
		offset = new Vector2i();
	}
	
	
	public Vector2i getPosition() { return this.position; }
	public void setPosition(Vector2i newPosition) { this.position = newPosition; }
	public Vector2i getOffset() { return this.offset; }
	public void setOffset(Vector2i newOffset) { this.offset = newOffset; }
	public int getFGColor() { return this.fgColor; }
	public void setFGColor(int newColor) { this.fgColor = newColor; }
	public int getBGColor() { return this.bgColor; }
	public void setBGColor(int newColor) { this.bgColor = newColor; }
	
	
	public void update(){
		
	}
	
	
	public void render(Screen screen){
		
	}
}
