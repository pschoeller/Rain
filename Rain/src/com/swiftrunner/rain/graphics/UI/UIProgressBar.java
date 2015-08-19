package com.swiftrunner.rain.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import org.w3c.dom.ranges.RangeException;

import com.swiftrunner.rain.maths.Vector2i;

public class UIProgressBar extends UIComponent{
	
	private double progress;	// 0 - 100
	
	
	public UIProgressBar(Vector2i position, Vector2i size) {
		super(position, size);
		this.foregroundColor = new Color(0xff00ff); 
	}
	
	
	public double getProgress() { return this.progress; }
	
	
	public void setProgress(double progress){
		if(progress < 0.0 || progress > 1.0)
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0.0 and 1.0.");
		this.progress = progress;
	}
	
	
	public void update() {
		super.update();
	}


	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), size.getX(), size.getY());
		
		g.setColor(foregroundColor);
		g.fillRect(position.getX() + offset.getX(), position.getY() + offset.getY(), (int)(progress * size.getX()), size.getY());
	}
}
