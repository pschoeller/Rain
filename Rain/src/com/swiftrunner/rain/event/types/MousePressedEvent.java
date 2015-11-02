package com.swiftrunner.rain.event.types;

import com.swiftrunner.rain.event.Event;

public class MousePressedEvent extends MouseButtonEvent{

	public MousePressedEvent(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_PRESSED);
	}

}
