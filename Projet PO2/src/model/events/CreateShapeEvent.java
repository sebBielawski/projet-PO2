package model.events;

import java.util.EventObject;

import model.Shape;

@SuppressWarnings("serial")
public final class CreateShapeEvent extends EventObject {
 
	private final Shape createdShape;
	
	public CreateShapeEvent(final Object source, final Shape createdShape) {
		super(source);
		this.createdShape = createdShape;
	}

	public Shape getCreatedShape() {
		return createdShape;
	}
}
