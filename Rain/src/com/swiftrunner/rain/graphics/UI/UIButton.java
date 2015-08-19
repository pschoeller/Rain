package com.swiftrunner.rain.graphics.UI;

import java.awt.Graphics;

import com.swiftrunner.rain.maths.Vector2i;

public class UIButton extends UIComponent{

	private UIButtonListener buttonListener;
	private UILabel label;
	
	public UIButton(Vector2i position, Vector2i size) {
		super(position, size);
		Vector2i lp = new Vector2i(position);
		lp.set(lp.getX()+4, lp.getY() + size.getY() - 4);
		label = new UILabel(lp, "");
		label.setColor(0x000000);
		label.active = false;
		setColor(0xaaaaaa);
	}
	
	
	public void init(UIPanel panel){
		super.init(panel);
		panel.addComponent(label);
	}
	
	
	public void setText(String text) { 
		if(text == "") label.active = false;
		else{
			label.setText(text);
		}
	}
	
	
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), size.getX(), size.getY());
		
		if(label != null){
			label.render(g);
		}
	}
}
