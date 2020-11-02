package ou5;

public class TestPolyline {
	
	public static void main(String[] args) {
		
		Polyline polyline = new Polyline();
		polyline.addEdge(new Point("A", 1, 2));
		polyline.addEdge(new Point("B", 3, 6));
		polyline.addEdge(new Point("D", 7, 12));
		polyline.addEdge(new Point("E", 2, 4));
		polyline.addEdgeAfter("B", new Point("C", 4, 8));
		
		polyline.setWidth(2);
		polyline.setColor("red");
		System.out.println(polyline.getColor());
		System.out.println(polyline.getLength());
		System.out.println(polyline.getWidth());
		
	}
	
}
