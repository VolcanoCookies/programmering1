package ou5;

import java.util.Objects;

public class Point {
	
	private String name;
	private int x;
	private int y;
	
	public Point(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this(p.getName(), p.getX(), p.getY());
	}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow(x - p.getX(), 2) + Math.pow(y - p.getY(), 2));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		Point point = (Point) o;
		return x == point.getX() &&
				y == point.getY() &&
				name.equals(point.getName());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, x, y);
	}
	
	@Override
	public String toString() {
		return "Point{" +
				"name='" + name + '\'' +
				", x=" + x +
				", y=" + y +
				'}';
	}
}

