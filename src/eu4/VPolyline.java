package eu4;

import java.util.Iterator;

public class VPolyline implements Polyline {
	
	protected Point[] points;
	
	protected String color;
	
	protected int width;
	
	public VPolyline(String color, int width) {
		points = new Point[0];
		this.color = color;
		this.width = width;
	}
	
	public VPolyline(Point[] points, String color, int width) {
		this.points = points;
		this.color = color;
		this.width = width;
	}
	
	@Override
	public Point[] getPoints() {
		return points;
	}
	
	@Override
	public String getColor() {
		return color;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public double length() {
		double len = 0;
		for (int i = 0; i < points.length - 1; i++) {
			len += points[i].distanceTo(points[i + 1]);
		}
		return len;
	}
	
	@Override
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}
	
	@Override
	public void addPoint(Point point) {
		Point[] p = new Point[points.length + 1];
		for (int i = 0; i < points.length; i++) {
			p[i] = points[i];
		}
		p[points.length] = point;
		points = p;
	}
	
	@Override
	public void addPointBefore(Point point, String pointBeforeName) {
		Point[] p = new Point[points.length + 1];
		int shift = 0;
		for (int i = 0; i < points.length; i++) {
			if (points[i].getName().equals(pointBeforeName)) {
				p[i] = point;
				shift = 1;
			}
			p[shift + i] = points[i];
		}
		if (shift == 1) {
			points = p;
		}
	}
	
	@Override
	public void removePoint(String pointName) {
		Point[] p = new Point[Math.max(points.length - 1, 0)];
		if (points.length == 1) {
			if (points[0].getName().equals(pointName)) {
				points = new Point[0];
			}
		} else {
			int shift = 0;
			for (int i = 0; i < p.length; i++) {
				if (points[i].getName().equals(pointName)) {
					shift = 1;
				}
				p[i] = points[i + shift];
			}
			if (shift == 1) {
				points = p;
			}
		}
	}
	
	@Override
	public Iterator<Point> iterator() {
		return new Iterator<>() {
			
			int current = points.length == 0 ? -1 : 0;
			
			@Override
			public boolean hasNext() {
				return current != -1;
			}
			
			@Override
			public Point next() {
				Point point = points[current++];
				if (current >= points.length) {
					current = -1;
				}
				return point;
			}
		};
	}
	
	@Override
	public String toString() {
		return "VPolyline{" +
				"points=" + length() +
				", color='" + color + '\'' +
				", width=" + width +
				'}';
	}
}

