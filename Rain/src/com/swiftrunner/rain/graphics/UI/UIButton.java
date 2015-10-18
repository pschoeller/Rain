package com.swiftrunner.rain.graphics.UI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.swiftrunner.rain.input.Mouse;
import com.swiftrunner.rain.maths.Vector2i;

public class UIButton extends UIComponent{

	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	private UILabel label;
	
	private Image image;
	
	private boolean inside = false;
	private boolean pressed = false;
	
	
	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i lp = new Vector2i(position);
		lp.set(lp.getX()+4, lp.getY() + size.getY() - 4);
		label = new UILabel(lp, "");
		label.setColor(0x000000);
		label.active = false;
		init();
	}
	
	
	public UIButton(Vector2i position, BufferedImage image, UIActionListener actionListener){
		super(position, new Vector2i(image.getWidth(), image.getHeight()));
		this.actionListener = actionListener;
		setImage(image);
		init();
	}
	
	
	private void init(){
		buttonListener = new UIButtonListener();
		setColor(0xaaaaaa);
	}
	
	
	public void init(UIPanel panel){
		super.init(panel);
		if(label != null){
			panel.addComponent(label);
		}
	}
	
	
	public void setImage(Image image){
		this.image = image;
	}
	
	
	public void setButtonListener(UIButtonListener buttonListener){
		this.buttonListener = buttonListener;
	}

	
	public void setText(String text) { 
		if(text == "") label.active = false;
		else{
			label.setText(text);
		}
	}
	
	
	public void render(Graphics g){
		int x = position.getX() + offset.getX();
		int y = position.getY() + offset.getY();
		
		if(image != null){
			g.drawImage(image, x, y, null);
		}
		else{
			g.setColor(color);
			g.fillRect(x, y, size.getX(), size.getY());
			
			if(label != null){
				label.render(g);
			}
		}
	}
	
	
	public void update(){
		Rectangle rect = new Rectangle(getAbsolutePosition().getX(), getAbsolutePosition().getY(), size.getX(), size.getY());
		if(rect.contains(new Point(Mouse.getX(), Mouse.getY()))){
			if(!inside)
				buttonListener.entered(this);
			inside = true;
			
			if(!pressed && Mouse.getB() == MouseEvent.BUTTON1){
				buttonListener.pressed(this);
				pressed = true;
			}
			else if(pressed && Mouse.getB() == MouseEvent.MOUSE_RELEASED){
				buttonListener.released(this);
				pressed = false;
				actionListener.perform();
			}
		}
		else{
			if(inside){
				buttonListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}
}
