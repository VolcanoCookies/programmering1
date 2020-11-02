package ou5;

import java.util.Random;

public class ChoosePolyline {
	
	public static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static final String[] COLORS = {"red", "yellow", "blue"};
	public static final Random rand = new Random();
	public static final int POLYLINE_COUNT = 10;
	
	public static void main(String[] args) {
		
		Polyline[] lines = new Polyline[POLYLINE_COUNT];
		for (int i = 0; i < POLYLINE_COUNT; i++) {
			lines[i] = randomPolyline();
		}
		
		Polyline shortest = null;
		double shortestLength = Double.MAX_VALUE;
		for (Polyline line : lines) {
			if (line.getColor().equals("yellow")) {
				double len = line.getLength();
				if (shortest == null) {
					shortest = line;
					shortestLength = len;
				} else if (shortestLength > len) {
					shortestLength = len;
				}
			}
		}
		
		if (shortest == null) {
			System.out.println("No yellow lines");
		} else {
			Polyline.PolylineIterator iterator = shortest.iterator();
			do {
				System.out.println(iterator.edge());
				iterator.next();
			} while (iterator.hasNext());
		}
		
	}
	
	private static Polyline randomPolyline() {
		Point[] edges = new Point[2 + rand.nextInt(7)];
		String[] names = new String[edges.length];
		for (int i = 0; i < edges.length; i++) {
			Point point = randomPoint();
			while (containsName(names, point)) {
				point = randomPoint();
			}
			edges[i] = point;
			names[i] = point.getName();
		}
		Polyline polyline = new Polyline(edges);
		polyline.setColor(COLORS[rand.nextInt(COLORS.length - 1)]);
		polyline.setWidth(1);
		return polyline;
	}
	
	private static boolean containsName(String[] names, Point point) {
		for (int i = 0; i < names.length; i++) {
			if (names[i] == null) {
				return false;
			} else if (names[i].equals(point.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private static Point randomPoint() {
		return new Point(String.valueOf(ALPHABET[rand.nextInt(ALPHABET.length)]),
				rand.nextInt(11),
				rand.nextInt(11));
	}
	
}
