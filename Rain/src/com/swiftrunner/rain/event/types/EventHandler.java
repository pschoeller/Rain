package com.swiftrunner.rain.event.types;

import com.swiftrunner.rain.event.Event;

public interface EventHandler {
	
	public boolean onEvent(Event event);
}
