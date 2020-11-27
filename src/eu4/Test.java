package eu4;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Test {
	
	public static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	public static final String[] COLORS = {"red", "yellow", "blue"};
	public static final Random rand = new Random();
	public static final int POLYLINE_COUNT = 10;
	
	public static void main(String[] args) {
		
		testPolyline(new VPolyline("yellow", 3));
		testPolyline(new NPolyline("yellow", 3));
		
		Polyline[] polylines = new Polyline[POLYLINE_COUNT];
		for (int i = 0; i < POLYLINE_COUNT; i++) {
			polylines[i] = randomPolyline();
		}
		
		System.out.println("\nShortest yellow");
		System.out.println(shortestYellow(polylines));
		
	}
	
	public static void testPolyline(Polyline polyline) {
		
		System.out.println("Testing");
		
		polyline.addPoint(new PointImpl(1, 1, "A"));
		polyline.addPoint(new PointImpl(1, 2, "B"));
		polyline.addPoint(new PointImpl(2, 4, "D"));
		polyline.addPoint(new PointImpl(1, 5, "E"));
		
		System.out.println(Arrays.toString(polyline.getPoints()));
		
		polyline.addPointBefore(new PointImpl(9, 7, "C"), "D");
		
		System.out.println(Arrays.toString(polyline.getPoints()));
		
		System.out.println(polyline.length());
		
		for (Point point : polyline) {
			System.out.println(point);
		}
		
	}
	
	public static Polyline shortestYellow(Polyline[] polylines) {
		
		double len = Double.MAX_VALUE;
		Polyline shortest = null;
		
		for (Polyline polyline : polylines) {
			if (polyline.getColor().equals("yellow")) {
				double dLen = polyline.length();
				if (dLen < len) {
					shortest = polyline;
					len = dLen;
				}
			}
		}
		
		return shortest;
		
	}
	
	private static Polyline randomPolyline() {
		Point[] points = new Point[2 + rand.nextInt(7)];
		String[] names = new String[points.length];
		
		for (int i = 0; i < points.length; i++) {
			Point point = randomPoint();
			while (containsName(names, point)) {
				point = randomPoint();
			}
			points[i] = point;
			names[i] = point.getName();
		}
		
		Polyline polyline;
		if (rand.nextBoolean()) {
			polyline = new VPolyline(COLORS[rand.nextInt(COLORS.length - 1)], 1);
		} else {
			polyline = new NPolyline(COLORS[rand.nextInt(COLORS.length - 1)], 1);
		}
		for (int i = 0; i < rand.nextInt(10) + 3; i++) {
			polyline.addPoint(randomPoint());
		}
		
		return polyline;
	}
	
	private static boolean containsName(String[] names, Point point) {
		for (String name : names) {
			if (Objects.equals(name, point.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private static Point randomPoint() {
		return new PointImpl(rand.nextInt(11),
				rand.nextInt(11),
				String.valueOf(ALPHABET[rand.nextInt(ALPHABET.length)]));
	}
	
}
