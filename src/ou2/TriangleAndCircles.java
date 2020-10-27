package ou2;

import java.util.Locale;
import java.util.Scanner;

public class TriangleAndCircles {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		scanner.useLocale(Locale.US);
		
		double[] triangle = new double[3];
		for (int i = 0; i < 3; i++) {
			System.out.println("Please enter the length side nr " + (i + 1));
			triangle[i] = scanner.nextDouble();
		}
		
	}
	
}
