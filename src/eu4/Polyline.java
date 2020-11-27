package eu4;

import java.util.Iterator;

public interface Polyline extends Iterable<Point> {
	
	Point[] getPoints();
	
	String getColor();
	
	int getWidth();
	
	double length();
	
	void setColor(String color);
	
	void setWidth(int width);
	
	void addPoint(Point point);
	
	void addPointBefore(Point point, String pointBeforeName);
	
	void removePoint(String pointName);
	
	@Override
	Iterator<Point> iterator();
	
}
