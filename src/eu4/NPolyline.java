package eu4;

import java.util.Iterator;

public class NPolyline implements Polyline {
	
	protected Node head;
	
	protected String color;
	
	protected int width;
	
	public NPolyline(String color, int width) {
		this.color = color;
		this.width = width;
	}
	
	public NPolyline(Point[] points, String color, int width) {
		this.color = color;
		this.width = width;
		if (points.length > 0) {
			head = new Node(points[0]);
			Node prev = head;
			for (int i = 1; i < points.length; i++) {
				Node node = new Node(points[i]);
				prev.next = node;
				prev = node;
			}
		}
	}
	
	@Override
	public Point[] getPoints() {
		int len = 0;
		
		Node curr = head;
		while (curr != null) {
			curr = curr.next;
			len++;
		}
		
		Point[] points = new Point[len];
		curr = head;
		
		int i = 0;
		while (curr != null) {
			points[i++] = curr.point;
			curr = curr.next;
		}
		
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
		
		Node curr = head;
		while (curr != null && curr.next != null) {
			len += curr.point.distanceTo(curr.next.point);
			curr = curr.next;
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
		if (head == null) {
			head = new Node(point);
		} else {
			Node curr = head;
			
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = new Node(point);
		}
	}
	
	@Override
	public void addPointBefore(Point point, String pointBeforeName) {
		if (head != null) {
			Node curr = head;
			while (curr.next != null) {
				if (curr.next.point.getName().equals(pointBeforeName)) {
					Node node = new Node(point);
					node.next = curr.next;
					curr.next = node;
					break;
				}
				curr = curr.next;
			}
		}
	}
	
	@Override
	public void removePoint(String pointName) {
		if (head != null) {
			Node curr = head;
			while (curr.next != null) {
				if (curr.next.point.getName().equals(pointName)) {
					curr.next = curr.next.next;
					break;
				}
				curr = curr.next;
			}
		}
	}
	
	@Override
	public Iterator<Point> iterator() {
		return new Iterator<>() {
			
			Node current = head;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}
			
			@Override
			public Point next() {
				Point point = current.point;
				current = current.next;
				return point;
			}
		};
	}
	
	@Override
	public String toString() {
		return "NPolyline{" +
				"points=" + length() +
				", color='" + color + '\'' +
				", width=" + width +
				'}';
	}
	
	private class Node {
		
		Point point;
		Node next;
		
		public Node(Point point) {
			this.point = point;
		}
	}
	
}
