package ou1;

import java.util.Locale;
import java.util.Scanner;

public class Temperatures {
	
	public static void main(String[] args) {
		System.out.println("TEMPERATURES\n");
		
		// Input device
		Scanner scanner = new Scanner(Temperatures.class.getResourceAsStream("./TestCases.txt"));
		scanner.useLocale(Locale.US);
		
		// Input information regarding total number of weeks and measurements per week
		System.out.print("Number of weeks: ");
		int weeks = scanner.nextInt();
		System.out.print("Number of measurements per week: ");
		int measurementsPerWeek = scanner.nextInt();
		
		double[][] t = new double[weeks][measurementsPerWeek];
		
		for (int week = 0; week < weeks; week++) {
			System.out.println("Temperatures -week " + week + ":");
			for (int measurement = 0; measurement < measurementsPerWeek; measurement++) {
				t[week][measurement] = scanner.nextDouble();
			}
		}
		
		// Show the temperatures
		System.out.println("\nTemperatures:");
		for (int week = 0; week < weeks; week++) {
			for (int measurement = 0; measurement < measurementsPerWeek; measurement++) {
				System.out.print(t[week][measurement] + " ");
			}
			System.out.println();
		}
		
		double[] minT = new double[weeks];
		double[] maxT = new double[weeks];
		double[] sumT = new double[weeks];
		double[] avgT = new double[weeks];
		
		for (int week = 0; week < weeks; week++) {
			minT[week] = t[week][0];
			maxT[week] = t[week][0];
			sumT[week] = t[week][0];
			for (int measurement = 0; measurement < measurementsPerWeek; measurement++) {
				double current = t[week][measurement];
				if (minT[week] > current) {
					minT[week] = current;
				}
				if (maxT[week] < current) {
					maxT[week] = current;
				}
				sumT[week] += current;
			}
			avgT[week] = sumT[week] / t[week].length;
			
			System.out.println("\n==============================");
			System.out.println("Measurements for week #" + (week + 1));
			System.out.println("Lowest: " + minT[week]);
			System.out.println("Highest: " + maxT[week]);
			System.out.println("Sum: " + sumT[week]);
			System.out.println("Average: " + avgT[week]);
			
		}
		
		double minTemp = minT[0];
		double maxTemp = maxT[0];
		double sumTemp = sumT[0];
		double avgTemp = 0;
		
		for (int week = 0; week < weeks; week++) {
			if (minTemp > minT[week]) {
				minTemp = minT[week];
			}
			if (maxTemp > maxT[week]) {
				maxTemp = maxT[week];
			}
			sumTemp += sumT[week];
		}
		
		avgTemp = sumTemp / (weeks * measurementsPerWeek);
		
		System.out.println("\n==============================");
		System.out.println("Measurements for the whole period");
		System.out.println("Lowest: " + minTemp);
		System.out.println("Highest: " + maxTemp);
		System.out.println("Sum: " + sumTemp);
		System.out.println("Average: " + avgTemp);
		
	}
}
