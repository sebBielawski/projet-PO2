package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import utils.Drawable;

public class Scene extends Observable implements Drawable, Serializable, Cloneable {

	private ArrayList<SceneObject> array = new ArrayList<>();
	
	private AffineTransform at = new AffineTransform();

	@Override
	public void draw(Graphics g) {
		for (Iterator<SceneObject> iter = array.iterator(); iter.hasNext();) {
			Drawable d = (Drawable) iter.next();
			d.draw((Graphics2D)g);
		}
	}
	
	public void add(SceneObject d) {
		array.add(d);
		setChanged();
		notifyObservers(array);
	}
	
	public void remove(SceneObject d) {
		array.remove(d);
		setChanged();
		notifyObservers(array);
	}
	
	public void clear() {
		array.clear();
		setChanged();
		notifyObservers(array);
	}
	
	public ArrayList<SceneObject> getArrayList() {
		return array;
	}
	
	public AffineTransform getTranform() {
		return at;
	}
	
	public void setTransform(AffineTransform a) {
		at = a;
	}
}
