package model;
import java.util.Observable;
import java.util.Vector;

import model.events.CreateShapeEvent;
import model.events.DeleteShapeEvent;
import model.events.SelectionChangeEvent;


public final class Model extends Observable {

	private final Vector<MyShape> shapes = new Vector<MyShape>();
	private ShapeSelection shapeSelection; // remplacer par hashset ??
	// ajouter selectedShape ?????? et effacer class ShapeSelection ???
	
	
	public Model() {
		this.shapeSelection = new ShapeSelection();
	}
	
	public MyShape createShape() {
		final MyShape shape = new MyShape();
		this.shapes.add(shape);
		this.setChanged(); 
		this.notifyObservers(new CreateShapeEvent(this, shape));
		return shape;
	}
	
	public void deleteShape(final MyShape shape) {
		if (shape != null) {
			if (shapeSelection.getSelectedShape() == shape)
				shapeSelection.setSelectedShape(null);
			this.shapes.remove(shape);
			this.setChanged(); 
			this.notifyObservers(new DeleteShapeEvent(this, shape));
		}
	}
	
	public MyShape getShape(final int index) {
		return shapes.get(index);
	}
	
	public Vector<MyShape> getShapes() {
		return shapes;
	}

	public ShapeSelection getShapeSelection() {
		return shapeSelection;
	}
	
	public void setShapeSelection(final ShapeSelection shapeSelection) {
		if (this.shapeSelection.equals(shapeSelection) == false) {
			this.shapeSelection = shapeSelection;
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, shapeSelection));
		}
	}
}
