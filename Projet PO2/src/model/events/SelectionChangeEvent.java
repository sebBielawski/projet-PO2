package model.events;

import java.util.EventObject;

import model.ShapeSelection;


@SuppressWarnings("serial")
public final class SelectionChangeEvent extends EventObject {

	private final ShapeSelection newShapeSelection;
	
	public SelectionChangeEvent(final Object source, final ShapeSelection newShapeSelection) {
		super(source);
		this.newShapeSelection = newShapeSelection;
	}

	public ShapeSelection getNewShapeSelection() {
		return newShapeSelection;
	}
}
