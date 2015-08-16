package com.swiftrunner.rain.graphics.UI;

import com.swiftrunner.rain.graphics.Font;
import com.swiftrunner.rain.graphics.Screen;
import com.swiftrunner.rain.maths.Vector2i;

public class UILabel extends UIComponent {
	
	private String text;
	private Font font;

	
	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font();
		this.text = text;
	}
	
	
	public String getText() { return this.text; }
	public void setText(String text) { this.text = text; }
	
	
	public void render(Screen screen) {
		font.render(position.getX() + offset.getX(), position.getY() + offset.getY(), -3, 0xffff00, text, screen);
	}
}
