package model.events;

import java.util.EventObject;

import model.Shape;

@SuppressWarnings("serial")
public final class ShapeEditEvent extends EventObject {

	private final Shape editedShape;
	
	public ShapeEditEvent(final Object source, final Shape createdShape) {
		super(source);
		this.editedShape = createdShape;
	}

	public Shape getEditedShape() {
		return editedShape;
	}
}
