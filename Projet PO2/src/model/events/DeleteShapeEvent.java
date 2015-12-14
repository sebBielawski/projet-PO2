package model.events;

import java.util.EventObject;

import model.Shape;

@SuppressWarnings("serial")
public class DeleteShapeEvent extends EventObject {

	private final Shape deletedShape;
	
	public DeleteShapeEvent(final Object source, final Shape deletedShape) {
		super(source);
		this.deletedShape = deletedShape;
		System.out.println("dele");
	}

	public Shape getDeletedShape() {
		return deletedShape;
	}
}
