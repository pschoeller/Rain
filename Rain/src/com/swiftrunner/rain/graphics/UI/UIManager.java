package com.swiftrunner.rain.graphics.UI;

import java.util.ArrayList;
import java.util.List;

import com.swiftrunner.rain.graphics.Screen;

public class UIManager {
	
	protected List<UIPanel> panels = new ArrayList<UIPanel>();
	
	
	public UIManager(){
		
	}
	
	
	public void addPanel(UIPanel panel){
		panels.add(panel);
	}
	
	
	public void update(){
		for(UIPanel panel : panels){
			panel.update();
		}
	}
	
	
	public void render(Screen screen){
		for(UIPanel panel : panels){
			panel.render(screen);
		}
	}
}
