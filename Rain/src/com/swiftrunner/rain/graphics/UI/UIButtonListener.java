package com.swiftrunner.rain.graphics.UI;

public class UIButtonListener {
	
	public void entered(UIButton button){ button.setColor(0x777777); }
	public void exited(UIButton button){ button.setColor(0xaaaaaa); }
	public void pressed(UIButton button){ button.setColor(0x700000); }
	public void released(UIButton button){ button.setColor(0xaaaaaa); }
}
