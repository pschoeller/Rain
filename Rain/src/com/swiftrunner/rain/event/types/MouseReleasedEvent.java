package com.swiftrunner.rain.event.types;

import com.swiftrunner.rain.event.Event;

public class MouseReleasedEvent extends MouseButtonEvent{

	public MouseReleasedEvent(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_RELEASED);
	}

}
