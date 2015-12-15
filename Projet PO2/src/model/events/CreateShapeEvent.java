package model.events;

import java.util.EventObject;

import model.MyShape;

@SuppressWarnings("serial")
public final class CreateShapeEvent extends EventObject {
 
	private final MyShape createdShape;
	
	public CreateShapeEvent(final Object source, final MyShape createdShape) {
		super(source);
		this.createdShape = createdShape;
	}

	public MyShape getCreatedShape() {
		return createdShape;
	}
}
