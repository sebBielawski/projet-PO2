package model.events;

import java.util.EventObject;

import model.MyShape;

@SuppressWarnings("serial")
public final class ShapeEditEvent extends EventObject {

	private final MyShape editedShape;
	
	public ShapeEditEvent(final Object source, final MyShape createdShape) {
		super(source);
		this.editedShape = createdShape;
	}

	public MyShape getEditedShape() {
		return editedShape;
	}
}
