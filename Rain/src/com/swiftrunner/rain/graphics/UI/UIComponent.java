package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import com.swiftrunner.rain.maths.Vector2i;

public class UIComponent {
	
	protected Vector2i position, offset, size;
	protected Color color, foregroundColor;
	protected UIPanel panel;
	protected boolean active = true;

	
	public UIComponent(Vector2i position){
		this.position = position;
		offset = new Vector2i();
	}
	
	
	public UIComponent(Vector2i position, Vector2i size){
		this.position = position;
		this.size = size;
		offset = new Vector2i();
	}
	
	
	public void init(UIPanel panel){
		this.panel = panel;
	}
	
	
	public Vector2i getPosition() { return this.position; }
	public void setPosition(Vector2i position) { this.position = position; }
	public Vector2i getSize() { return this.size; }
	public void setSize(Vector2i size) { this.size = size; }
	public Vector2i getOffset() { return this.offset; }
	public void setOffset(Vector2i offset) { this.offset = offset; }
	public UIComponent setColor(int color) { this.color = new Color(color); return this; }
	public Color getForegroundColor() { return this.foregroundColor; }
	public void setForegroundColor(int color) { this.foregroundColor = new Color(color); }
	public void update(){}
	public void render(Graphics g){}
}
