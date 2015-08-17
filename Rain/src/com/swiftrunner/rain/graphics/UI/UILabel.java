package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.swiftrunner.rain.maths.Vector2i;

public class UILabel extends UIComponent {
	
	private String text;
	private Font font;

	
	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font("Helvetica", Font.PLAIN, 30);
		this.text = text;
		this.color = new Color(0xff00ff);
	}
	
	
	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }
	public UILabel setFont(Font font) { this.font = font; return this; }
	
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, position.getX() + offset.getX(), position.getY() + offset.getY());
	}
}
