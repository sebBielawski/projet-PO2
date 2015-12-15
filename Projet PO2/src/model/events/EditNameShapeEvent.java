package model.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public final class EditNameShapeEvent extends EventObject {

	private final String newShapeName;
	
	public EditNameShapeEvent(final Object source, final String newShapeName) {
		super(source);
		this.newShapeName = newShapeName;
	}

	public String getNewShapeName() {
		return newShapeName;
	}
}
