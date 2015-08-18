package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.swiftrunner.rain.maths.Vector2i;

public class UIPanel extends UIComponent{
	
	private List<UIComponent> components = new ArrayList<UIComponent>();
	private Vector2i size;
	
	
	public UIPanel(Vector2i position, Vector2i size){
		super(position);
		this.size = size;
		color = new Color(0xcacaca);
	}
	
	
	public void addComponent(UIComponent component){
		components.add(component);
	}
	
	
	public void update(){
		for(UIComponent component : components){
			component.setOffset(position);
			component.update();
		}
	}
	
	
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(this.position.getX(), this.position.getY(), this.size.getX(), this.size.getY());
		for(UIComponent component : components){
			component.render(g);
		}
	}
}
