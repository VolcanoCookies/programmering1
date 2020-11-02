package ou5;

import java.util.Arrays;

public class Polyline {
	
	private Point[] edges;
	private String color;
	private int width = 1;
	
	public Polyline() {
		edges = new Point[0];
	}
	
	public Polyline(Point[] edges) {
		this.edges = new Point[edges.length];
		for (int i = 0; i < edges.length; i++) {
			this.edges[i] = new Point(edges[i]);
		}
	}
	
	public Point[] getEdges() {
		return edges;
	}
	
	public void setEdges(Point[] edges) {
		this.edges = edges;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public double getLength() {
		double len = 0;
		for (int i = 1; i < edges.length; i++) {
			len += edges[i - 1].distance(edges[i]);
		}
		return len;
	}
	
	public void addEdge(Point edge) {
		Point[] h = new Point[edges.length + 1];
		for (int i = 0; i < h.length - 1; i++) {
			h[i] = new Point(edges[i]);
		}
		h[h.length - 1] = edge;
		edges = h;
	}
	
	public void addEdgeToFront(Point edge) {
		Point[] h = new Point[edges.length + 1];
		h[0] = edge;
		for (int i = 1; i < h.length; i++) {
			h[i] = new Point(edges[i - 1]);
		}
		edges = h;
	}
	
	public void remove(String pointName) {
		Point[] h = new Point[edges.length - 1];
		for (int i = 0; i < edges.length; i++) {
			if (i == h.length) {
				return;
			} else if (!edges[i].getName().equals(pointName)) {
				h[i] = new Point(edges[i]);
			}
		}
		edges = h;
	}
	
	public PolylineIterator iterator() {
		return new PolylineIterator();
	}
	
	@Override
	public String toString() {
		return "Polyline{" +
				"edges=" + Arrays.toString(edges) +
				", color='" + color + '\'' +
				", width=" + width +
				'}';
	}
	
	public void addEdgeAfter(String pointName, Point edge) {
		Point[] h = new Point[edges.length + 1];
		for (int i = 1; i < h.length; i++) {
			if (edges[i].getName().equals(pointName)) {
				h[i] = edge;
			} else if (i + 1 == edges.length) {
				return;
			} else {
				h[i] = edges[i];
			}
		}
		edges = h;
	}
	
	public class PolylineIterator {
		
		private int current = -1;
		
		public PolylineIterator() {
			if (edges.length > 0) {
				current = 0;
			}
		}
		
		public boolean hasNext() {
			return current != -1;
		}
		
		public Point edge() throws java.util.NoSuchElementException {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException("End of iterationen");
			}
			
			Point edge = edges[current];
			return edge;
		}
		
		public void next() {
			if (current >= 0 && current < edges.length - 1) {
				current++;
			} else {
				current = -1;
			}
		}
	}
}
