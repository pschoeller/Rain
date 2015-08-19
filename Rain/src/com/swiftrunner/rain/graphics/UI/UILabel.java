package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.swiftrunner.rain.maths.Vector2i;

public class UILabel extends UIComponent {
	
	private String text;
	private Font font;
	private boolean dropShadow = false;
	private int dropShadowOffset = 2;

	
	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font("Verdana", Font.PLAIN, 30);
		this.text = text;
		this.color = new Color(0xff00ff);
	}
	
	
	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }
	public UILabel setFont(Font font) { this.font = font; return this; }
	public boolean getShadow() { return dropShadow; }
	public void toggleShadow() { if(dropShadow == true) dropShadow = false; else dropShadow = true; }
	public void setDropShadowOffset(int offset) { this.dropShadowOffset = offset; }
	
	
	public void render(Graphics g) {
		if(dropShadow){			
			g.setColor(Color.BLACK);
			g.setFont(new Font(font.getName(), font.getStyle(), font.getSize()));
			g.drawString(text, position.getX() + offset.getX()+dropShadowOffset, position.getY() + offset.getY()+dropShadowOffset);
		}
		
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.getX() + offset.getX(), position.getY() + offset.getY());
	}
}
