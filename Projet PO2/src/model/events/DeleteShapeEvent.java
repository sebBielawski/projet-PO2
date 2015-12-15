package model.events;

import java.util.EventObject;

import model.MyShape;

@SuppressWarnings("serial")
public class DeleteShapeEvent extends EventObject {

	private final MyShape deletedShape;
	
	public DeleteShapeEvent(final Object source, final MyShape deletedShape) {
		super(source);
		this.deletedShape = deletedShape;
		System.out.println("dele");
	}

	public MyShape getDeletedShape() {
		return deletedShape;
	}
}
