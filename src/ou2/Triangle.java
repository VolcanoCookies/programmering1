package ou2;

public class Triangle {
	
	/**
	 * @param w width of the triangle
	 * @param h height of the triangle
	 * @return the area of the triangle
	 */
	public static double area(double w, double h) {
		return (w * h) / 2;
	}
	
	/**
	 * @param a length of side a
	 * @param b length of side b
	 * @param c length of side c
	 * @param p the perimeter
	 * @return the area of the triangle
	 */
	public static double area(double a, double b, double c, double p) {
		double halfPeri = p / 2;
		return Math.sqrt(halfPeri * (halfPeri - a) * (halfPeri - b) * (halfPeri - c));
	}
	
	/**
	 * @param a length of side a
	 * @param b length of side b
	 * @param c length of side c
	 * @return the circumference of the triangle
	 */
	public static double perimeter(double a, double b, double c) {
		return a + b + c;
	}
	
	// We can calculate all bisectrixes in a given triangle as long as we supply the method with the correct variables
	// This method gets all of its parameters from its arguments,
	// thus given the correct arguments, any besectrix can be calculated
	public static double bisectrix(double b, double c, double alfa) {
		double p = 2 * b * c * Math.cos(alfa / 2);
		double bis = p / (b + c);
		return bis;
	}
	
	// A = pr / 2
	// r = A2 / p
	public static double innerCircleRadius(double a, double b, double c) {
		double p = perimeter(a, b, c);
		double area = area(a, b, c, p);
		return area * 2 / p;
	}
	
	public static double outerCircleRadius(double a, double b, double c) {
		double p = perimeter(a, b, c);
		double area = area(a, b, c, p);
		return (a * b * c) / (4 * area);
	}
	
}