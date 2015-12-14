package model;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import model.events.SelectionChangeEvent;


public final class ShapeSelection extends Observable implements Set<Integer> {

	private final HashSet<Integer> set = new HashSet<Integer>();
	private Shape selectedShape;
	
	public ShapeSelection() {
		selectedShape = null;
	}
	
	public ShapeSelection(final Collection<? extends Integer> c, final Shape selectedShape) {
		addAll(c);
		this.selectedShape = selectedShape;
	}
	
	public Shape getSelectedShape() {
		return selectedShape;
	}

	public void setSelectedShape(final Shape selectedShape) {
		if (this.selectedShape != selectedShape) {
			this.selectedShape = selectedShape;
			this.setChanged();
			this.notifyObservers(new ShapeSelection(this.set, selectedShape));
		}
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return set.contains(o);
	}

	@Override
	public Iterator<Integer> iterator() {
		return set.iterator();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	@Override
	public boolean add(Integer e) {
		if (!set.add(e))
			return false;
		else {
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, this));
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		if (!set.remove(o))
			return false;
		else {
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, this));
			return true;
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		if (!set.addAll(c))
			return false;
		else {
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, this));
			return true;
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		if (!set.retainAll(c))
			return false;
		else {
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, this));
			return true;
		}
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if (!set.removeAll(c))
			return false;
		else {
			this.setChanged();
			this.notifyObservers(new SelectionChangeEvent(this, this));
			return true;
		}
	}

	@Override
	public void clear() {
		set.clear();
		this.setChanged();
		this.notifyObservers(new SelectionChangeEvent(this, this));
	}
	
	@Override
	public String toString() {
		return set.toString();
	}
	
	public boolean equals(ShapeSelection shapeSelection) {
		return set.equals(shapeSelection.set);
	}
}
