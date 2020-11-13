package ou4;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class StringNumberOperations {
	
	public static void main(String[] args) {
		out.println("OPERATIONS WITH NATURAL WHOLE NUMBERS PROVIDED AS STRINGS\n");
		
		boolean useRandom = true;
		
		if (useRandom) {
			
			Random random = new Random();
			int incorrect = 0;
			long startTime = System.nanoTime();
			for (int i = 0; i < 100000; i++) {
				int r1 = random.nextInt(1000000) + 1;
				int r2 = random.nextInt(1000000) + 1;
				String res = add(Integer.toString(r1), Integer.toString(r2));
				if (Integer.parseInt(res) == r1 + r2) {
					out.println("#" + (i + 1) + " Correct");
					out.println(r1 + " + " + r2 + " was correct");
				} else {
					incorrect++;
					out.println("#" + (i + 1) + " Incorrect");
					out.println(r1 + " + " + r2 + " != " + res);
					out.println("Correct is: " + (r1 + r2));
				}
			}
			out.println("Total incorrect: " + incorrect);
			out.println("Took " + ((System.nanoTime() - startTime) / 1000000) + "ms");
			
		} else {
			
			// Input two natural whole numbers
			Scanner in = new Scanner(System.in);
			out.println("Two natural whole numbers:");
			String number1 = in.next();
			String number2 = in.next();
			out.println();
			
			// Add the numbers and show the result
			String sum = add(number1, number2);
			show(number1, number2, sum, '+');
			
			// Subtract the numbers and show the results
			String diff = sub(number1, number2);
			show(number1, number2, diff, '-');
			
		}
	}
	
	/**
	 * Add two whole natural numbers represented as strings
	 *
	 * @param number1 first number as a string
	 * @param number2 second number as a string
	 * @return the result of adding number1 and number2
	 */
	public static String add(String number1, String number2) {
		int diff = number1.length() - number2.length();
		if (diff > 0) { // Add 0s to the start of number 2
			number2 = "0".repeat(diff) + number2;
		} else if (diff < 0) { // Add 0s to the start of number 1
			number1 = "0".repeat(-diff) + number1;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		// If there was any carry of the operation
		boolean carry = false;
		for (int i = number1.length() - 1; i >= 0; i--) {
			String res = String.valueOf(Character.getNumericValue(number1.charAt(i)) +
					Character.getNumericValue(number2.charAt(i)) +
					(carry ? 1 : 0));
			stringBuilder.insert(0, res.charAt(res.length() - 1));
			carry = res.length() > 1;
		}
		if (carry) {
			// There was carry, insert a 1 to the start of the string
			stringBuilder.insert(0, 1);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * Subtract two whole natural numbers represented as strings
	 *
	 * @param number1 first number as a string
	 * @param number2 second number as a string
	 * @return the result of subtracting number1 and number2
	 */
	public static String sub(String number1, String number2) {
		// If the result is negative
		boolean negative = false;
		// The difference in length
		// We want to flip the numbers so the bigger number is being subtracted from
		int diff = number1.length() - number2.length();
		
		if (diff > 0) { // We dont need to swap, just add 0s to the start of the second number
			number2 = "0".repeat(diff) + number2;
		} else if (diff < 0) { // We switch the numbers around and add 0s to the start of the new first number
			number1 = "0".repeat(-diff) + number1;
			String t = number1;
			number1 = number2;
			number2 = t;
			negative = true;
		} else { // the numbers are of equal length, we compare their codepoint to determine the bigger number
			for (int i = 0; i < number1.length(); i++) {
				if (number1.codePointAt(i) < number2.codePointAt(i)) { // Number 2 is bigger, flip
					String t = number1;
					number1 = number2;
					number2 = t;
					negative = true;
					break;
				} else if (number1.codePointAt(i) > number2.codePointAt(i)) { // Number 1 is bigger, no change
					break;
				} else if (i + 1 == number1.length()) { // The numbers are equal, result is 0
					return "0";
				}
			}
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		// If there is any carry of one operation
		boolean carry = false;
		for (int i = number1.length() - 1; i >= 0; i--) {
			String res;
			if (number1.charAt(i) < number2.charAt(i) + (carry ? 1 : 0)) {
				res = String.valueOf((number1.codePointAt(i) -
						(carry ? 1 : 0) +
						10) -
						number2.codePointAt(i));
				carry = true;
			} else {
				res = String.valueOf(number1.codePointAt(i) -
						(carry ? 1 : 0) -
						number2.codePointAt(i));
				carry = false;
			}
			stringBuilder.insert(0, res.charAt(res.length() - 1));
		}
		return (negative ? "-" : "") + stringBuilder.toString();
	}
	
	/**
	 * Show the result of a calculator of 2 numbers
	 *
	 * @param number1  the first number
	 * @param number2  the second number
	 * @param result   the result of the calculation
	 * @param operator the operator of the operation
	 */
	public static void show(String number1, String number2, String result, char operator) {
		// Set a desired length of the numbers
		// We set the length to the longest length of the 3 numbers
		int len1 = number1.length();
		int len2 = number2.length();
		int len = result.length();
		int maxLen = Math.max(Math.max(len1, len2), len);
		number1 = sattLen(number1, maxLen - len1);
		number2 = sattLen(number2, maxLen - len2);
		result = sattLen(result, maxLen - len);
		
		// Show the numbers and the result
		out.println("  " + number1);
		out.println(operator + " " + number2);
		out.println("-".repeat(maxLen + 2));
		out.println("  " + result + "\n");
	}
	
	/**
	 * Insert n spaces to the start of the string
	 *
	 * @param s the string
	 * @param n the desired length of the string
	 * @return the same string with n spaces added to the start
	 */
	public static String sattLen(String s, int n) {
		StringBuilder sb = new StringBuilder(s);
		sb.insert(0, " ".repeat(n));
		return sb.toString();
	}
}