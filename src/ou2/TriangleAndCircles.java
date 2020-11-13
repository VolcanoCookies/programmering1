package ou2;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

public class TriangleAndCircles {
	
	public static void main(String[] args) {
		
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(TriangleAndCircles.class.getResourceAsStream("./TestCases.txt"));
		scanner.useLocale(Locale.US);
		
		double[] triangle = new double[3];
		for (int i = 0; i < triangle.length; i++) {
			System.out.println("Please enter the length of side nr " + (i + 1));
			triangle[i] = scanner.nextDouble();
		}
		
		double innerCircleRadius = Triangle.innerCircleRadius(triangle[0], triangle[1], triangle[2]);
		
		double outerCircleRadius = Triangle.outerCircleRadius(triangle[0], triangle[1], triangle[2]);
		
		DecimalFormat decimalFormat = new DecimalFormat("##.#");
		
		System.out.println("Inner circle radius is " + decimalFormat.format(innerCircleRadius));
		
		System.out.println("Outer circle radius is " + decimalFormat.format(outerCircleRadius));
		
	}
	
}
