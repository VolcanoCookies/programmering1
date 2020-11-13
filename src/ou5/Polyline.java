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
	
	/**
	 * @return the length of the polyline
	 */
	public double getLength() {
		double len = 0;
		for (int i = 1; i < edges.length; i++) {
			len += edges[i - 1].distance(edges[i]);
		}
		return len;
	}
	
	/**
	 * Add a point to the end of the polyline
	 *
	 * @param point the point ot add
	 */
	public void addEdge(Point point) {
		Point[] h = new Point[edges.length + 1];
		for (int i = 0; i < h.length - 1; i++) {
			h[i] = new Point(edges[i]);
		}
		h[h.length - 1] = point;
		edges = h;
	}
	
	/**
	 * Add a point to the start of this polyline
	 *
	 * @param point the point to add
	 */
	public void addEdgeToFront(Point point) {
		Point[] h = new Point[edges.length + 1];
		h[0] = point;
		for (int i = 1; i < h.length; i++) {
			h[i] = new Point(edges[i - 1]);
		}
		edges = h;
	}
	
	/**
	 * Remove a point with a specific name
	 *
	 * @param pointName the name of the point to remove
	 */
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
	
	/**
	 * Get a new iterator for this polyline
	 *
	 * @return the new iterator
	 */
	public PolylineIterator iterator() {
		return new PolylineIterator();
	}
	
	/**
	 * @return the string representation of the polyline
	 */
	@Override
	public String toString() {
		return "Polyline{" +
				"edges=" + Arrays.toString(edges) +
				", color='" + color + '\'' +
				", width=" + width +
				'}';
	}
	
	/**
	 * Add a point after another
	 *
	 * @param pointName the name of the point to add after
	 * @param edge      the point to add
	 */
	public void addEdgeAfter(String pointName, Point edge) {
		Point[] h = new Point[edges.length + 1];
		int shift = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[i].getName().equals(pointName)) {
				h[i + shift] = edges[i];
				h[i + ++shift] = edge;
			} else if (i + 1 == edges.length) {
				return;
			} else {
				h[i + shift] = edges[i];
			}
		}
		edges = h;
	}
	
	/**
	 * Add a point before another
	 *
	 * @param pointName the name of the point to add before
	 * @param edge      the point to add
	 */
	public void addEdgeBefore(String pointName, Point edge) {
		Point[] h = new Point[edges.length + 1];
		int shift = 0;
		for (int i = 0; i < edges.length; i++) {
			if (edges[i].getName().equals(pointName)) {
				h[i + shift++] = edge;
				h[i + shift] = edges[i];
			} else if (i + 1 == edges.length) {
				return;
			} else {
				h[i + shift] = edges[i];
			}
		}
		edges = h;
	}
	
	/**
	 * Iterator to use when iterating through all the points in a polyline
	 */
	public class PolylineIterator {
		
		private int current = -1;
		
		public PolylineIterator() {
			if (edges.length > 0) {
				current = 0;
			}
		}
		
		/**
		 * @return {@code true} if there are more points to go over
		 */
		public boolean hasNext() {
			return current != -1;
		}
		
		/**
		 * @return return the current edge
		 * @throws java.util.NoSuchElementException if there are no more points left
		 */
		public Point edge() throws java.util.NoSuchElementException {
			if (!hasNext()) {
				throw new java.util.NoSuchElementException("End of iterationen");
			}
			
			Point edge = edges[current];
			return edge;
		}
		
		/**
		 * Go to the next point in he polyline
		 */
		public void next() {
			if (current >= 0 && current < edges.length - 1) {
				current++;
			} else {
				current = -1;
			}
		}
	}
}
