package eu4;

public class PointImpl implements Point {
	
	protected double x, y;
	
	protected String name;
	
	public PointImpl(double x, double y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public double distanceTo(Point point) {
		return Math.sqrt(Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2));
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "PointImpl{" +
				"x=" + x +
				", y=" + y +
				", name='" + name + '\'' +
				'}';
	}
	
}
