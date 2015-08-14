package com.swiftrunner.rain.maths;

import com.swiftrunner.rain.graphics.Screen;

public class Debug {
	
	private Debug(){}
	
	public static void drawRect(Screen screen, int x, int y, int w, int h, int color, boolean fixed){
		screen.drawRect(x, y, w, h, color, fixed);
	}
	
	public static void drawRect(Screen screen, int xp, int yp, int w, int h, boolean fixed){
		drawRect(screen, xp, yp, w, h, 0xff0000, fixed);
	}
}
