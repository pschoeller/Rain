package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import com.swiftrunner.rain.maths.Vector2i;

public class UIComponent {
	
	protected Vector2i position, offset;
	protected Color color;

	
	
	public UIComponent(Vector2i position){
		this.position = position;
		offset = new Vector2i();
	}
	
	
	public Vector2i getPosition() { return this.position; }
	public void setPosition(Vector2i position) { this.position = position; }
	public Vector2i getOffset() { return this.offset; }
	public void setOffset(Vector2i offset) { this.offset = offset; }
	public UIComponent setColor(int color) { this.color = new Color(color); return this; }
	
	
	public void update(){
		
	}
	
	
	public void render(Graphics g){
		
	}
}
