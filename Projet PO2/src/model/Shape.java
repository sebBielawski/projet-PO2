package model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Observable;


public final class Shape extends Observable {
	public static long count = 0;
	
	private String name = "object #" + count;
	private Point position = new Point();
	private Dimension dimension = new Dimension(1, 1);
	private Color color = Color.MAGENTA;
	
	public Shape() {
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
			this.notifyObservers(name);
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
}
