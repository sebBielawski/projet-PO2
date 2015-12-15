package model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Observable;

import model.events.EditNameShapeEvent;


public final class MyShape extends Observable implements Namable, Shape, Serializable, Cloneable {
	public static long count = 0;
	
	private String name = "object #" + count;
	private Point position = new Point();
	private Dimension dimension = new Dimension(1, 1);
	private Color color = Color.MAGENTA;
	
	public MyShape() {
		super();
		count++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != this.name) {
			this.name = name;
			this.setChanged();
			this.notifyObservers(new EditNameShapeEvent(this, name));
		}
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		if (position != this.position) {
			this.position = position;
			this.setChanged();
			this.notifyObservers(position);
		}
	}
	
	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		if (dimension != this.dimension) {
			this.dimension = dimension;
			this.setChanged();
			this.notifyObservers(dimension);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		if (color != this.color) {
			this.color = color;
			this.setChanged();
			this.notifyObservers(color);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(position, dimension);
	}

	@Override
	public Rectangle2D getBounds2D() {
		return new Rectangle(position, dimension);
	}

	@Override
	public boolean contains(double x, double y) {
		return this.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return this.contains(p.getX(), p.getY());
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return new Rectangle(position, dimension).intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return new Rectangle(position, dimension).intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return new Rectangle(position, dimension).contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return new Rectangle(position, dimension).intersects(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}
}
