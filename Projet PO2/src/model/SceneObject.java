package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.Observable;

import utils.Drawable;

public abstract class SceneObject extends Observable implements Drawable, Serializable, Cloneable {

	protected Color color;
	protected Point position;
	protected Dimension dimension;
	protected String name;
	
	
	public SceneObject() {
		color = Color.BLACK;
		position = new Point(0, 0);
		dimension = new Dimension(100, 100);
		name = "unnamed";
	}
	
	public SceneObject(Color c, Dimension d, Point p, String s) {
		color = c;
		position = p;
		dimension = d;
		name = s;
	}
	
	public abstract void draw(Graphics g);
	
	/* GETTERS */
	public Color getColor() {
		return color;
	}
	
	public Point getPos() {
		return position;
	}
	
	public Dimension getDim() {
		return dimension;
	}
	
	public String getName() {
		return name;
	}
	
	/* SETTERS */	
	public void setColor(Color c) {
		color = c;
		setChanged();
		notifyObservers(color);
	}
	
	public void setPos(Point p) {
		position = p;
		setChanged();
		notifyObservers(position);
	}
	
	public void setDimension(Dimension d) {
		dimension = d;
		setChanged();
		notifyObservers(dimension);
	}
	
	public void setName(String s) {
		name = s;
		setChanged();
		notifyObservers(name);
	}
}
