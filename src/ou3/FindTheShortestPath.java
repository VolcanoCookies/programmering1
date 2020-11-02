package ou3;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class FindTheShortestPath {
	
	public static void main(String[] args) {
		
		//Scanner scanner = new Scanner(FindTheShortestPath.class.getResourceAsStream("./TestData.txt"));
		
		int m = 10000;
		int n = 10000;
		
		//int m = scanner.nextInt();
		//int n = scanner.nextInt();
		
		double[] a = new double[m];
		double[][] b = new double[m][n];
		double[] c = new double[n];
		
		Random r = new Random();
		for (int i = 0; i < m; i++) {
			
			a[i] = 1 + r.nextInt(1000) / 10d;
			//a[i] = scanner.nextInt();
			
			for (int j = 0; j < n; j++) {
				if (i == 0) {
					c[j] = 1 + r.nextInt(1000) / 10d;
					//c[j] = scanner.nextInt();
				}
				b[i][j] = 1 + r.nextInt(1000) / 10d;
				//b[i][j] = scanner.nextInt();
			}
			
		}
		
		//System.out.println(Arrays.toString(a));
		//System.out.println(Arrays.deepToString(b));
		//System.out.println(Arrays.toString(c));
		
		long nano = System.nanoTime();
		System.out.println(TheShortestPath.distance(a, b, c));
		System.out.println(Arrays.toString(TheShortestPath.stations(a, b, c)));
		long time = System.nanoTime() - nano;
		DecimalFormat decimalFormat = new DecimalFormat("##.##");
		System.out.println("Calculation took " + decimalFormat.format(time / 1000000d) + "ms");
		
	}
	
}

class TheShortestPath {
	
	public static int[] stations(double[] a, double[][] b, double[] c) {
		double min = Double.MAX_VALUE;
		int[] si = new int[2];
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				for (int j = 0; j < b[i].length; j++) {
					if (a[i] + b[i][j] < min) {
						if (a[i] + b[i][j] + c[j] < min) {
							min = a[i] + b[i][j] + c[j];
							si[0] = i;
							si[1] = j;
						}
					}
				}
			}
		}
		return si;
	}
	
	public static double distance(double[] a, double[][] b, double[] c) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < min) {
				for (int j = 0; j < b[i].length; j++) {
					if (a[i] + b[i][j] < min) {
						if (a[i] + b[i][j] + c[j] < min) {
							min = a[i] + b[i][j] + c[j];
						}
					}
				}
			}
		}
		return min;
	}
	
}
