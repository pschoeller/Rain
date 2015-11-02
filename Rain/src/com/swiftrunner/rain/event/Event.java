package com.swiftrunner.rain.event;

public class Event {
	
	public enum Type{
		MOUSE_PRESSED,
		MOUSE_RELEASED,
		MOUSE_MOVED
	}
	
	public Type type;
	boolean handled;
	
	
	protected Event(Type type){
		this.type = type;
	}
	
	
	public Type getType() { return type; }
}
