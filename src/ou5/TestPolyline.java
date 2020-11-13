package ou5;

public class TestPolyline {
	
	public static void main(String[] args) {
		
		Polyline polyline = new Polyline();
		polyline.addEdge(new Point("A", 1, 2));
		polyline.addEdge(new Point("C", 3, 6));
		polyline.addEdge(new Point("E", 7, 12));
		polyline.addEdge(new Point("F", 2, 4));
		polyline.addEdgeAfter("C", new Point("D", 4, 8));
		polyline.addEdgeBefore("C", new Point("B", 6, 3));
		
		Polyline.PolylineIterator iterator = polyline.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.edge().toString());
			iterator.next();
		}
		
		polyline.setWidth(2);
		polyline.setColor("red");
		System.out.println(polyline.getColor());
		System.out.println(polyline.getLength());
		System.out.println(polyline.getWidth());
		
		polyline.remove("B");
		System.out.println(polyline.toString());
		
	}
	
}
