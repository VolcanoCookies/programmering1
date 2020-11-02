package ou4;

import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class StringNumberOperations {
	
	public static void main(String[] args) {
		out.println("OPERATIONS WITH NATURAL WHOLE NUMBERS PROVIDED AS STRINGS\n");
		
		Random random = new Random();
		int incorrect = 0;
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
			out.println("Total incorrect: " + incorrect);
		}
		
		if (true) {
			System.exit(0);
		}

// mata in två naturliga heltal
		Scanner in = new Scanner(System.in);
		out.println("Two natural whole numbers:");
		String number1 = in.next();
		String number2 = in.next();
		out.println();

// addera heltalenoch visa resultatet
		String sum = add(number1, number2);
		show(number1, number2, sum, '+');

// subtrahera heltalen och visa resultatet
//koden här
		String diff = sub(number1, number2);
		show(number1, number2, diff, '-');
		
	}
	
	// addera tar emot två naturliga heltal givna somteckensträngar, och returnerar deras
// summa somen teckensträng.
	public static String add(String number1, String number2) {
		//return Integer.toString(Integer.parseInt(number1) + Integer.parseInt(number2));
		int diff = number1.length() - number2.length();
		if (diff > 0) {
			number2 = "0".repeat(diff) + number2;
		} else if (diff < 0) {
			number1 = "0".repeat(-diff) + number1;
		}
		StringBuilder stringBuilder = new StringBuilder();
		boolean carry = false;
		for (int i = number1.length() - 1; i >= 0; i--) {
			String res = String.valueOf(Character.getNumericValue(number1.charAt(i)) +
					Character.getNumericValue(number2.charAt(i)) +
					(carry ? 1 : 0));
			stringBuilder.insert(0, res.charAt(res.length() - 1));
			carry = res.length() > 1;
		}
		if (carry) {
			stringBuilder.insert(0, 1);
		}
		return stringBuilder.toString();
	}
	
	// subtrahera tar emot två naturliga heltal givna somteckensträngar, och returnerar
// deras differens somen teckensträng.
// Det första heltalet är inte mindre än det andra heltalet.
	public static String sub(String number1, String number2) {
// koden ska skrivas här
		//return Integer.toString(Integer.parseInt(number1) - Integer.parseInt(number2));
		boolean negative = false;
		int diff = number1.length() - number2.length();
		if (diff > 0) {
			number2 = "0".repeat(diff) + number2;
		} else if (diff < 0) {
			number1 = "0".repeat(-diff) + number1;
			String t = number1;
			number1 = number2;
			number2 = t;
			negative = true;
		} else {
			for (int i = 0; i < number1.length(); i++) {
				if (number1.codePointAt(i) < number2.codePointAt(i)) {
					String t = number1;
					number1 = number2;
					number2 = t;
					negative = true;
					break;
				} else if (number1.codePointAt(i) > number2.codePointAt(i)) {
					break;
				} else if (i + 1 == number1.length()) {
					return "0";
				}
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
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
			//out.println(number1.charAt(i) + " " + number2.charAt(i));
			//out.println(res);
			stringBuilder.insert(0, res.charAt(res.length() - 1));
		}
		return (negative ? "-" : "") + stringBuilder.toString();
	}
	
	// visa visar två givna naturliga heltal, och resultatet av en aritmetisk operation
// utförd i samband med hetalen
	public static void show(String number1, String number2, String result, char operator) {
// sätt en lämplig längd på heltalen och resultatet
		int len1 = number1.length();
		int len2 = number2.length();
		int len = result.length();
		int maxLen = Math.max(Math.max(len1, len2), len);
		number1 = sattLen(number1, maxLen - len1);
		number2 = sattLen(number2, maxLen - len2);
		result = sattLen(result, maxLen - len);

// visa heltalen och resultatet
		out.println("  " + number1);
		out.println(operator + " " + number2);
		out.println("-".repeat(maxLen + 2));
		out.println("  " + result + "\n");
	}
	
	// sattLen lägger till ett angivet antal mellanslagi början av en given sträng
	public static String sattLen(String s, int n) {
		StringBuilder sb = new StringBuilder(s);
		sb.insert(0, " ".repeat(n));
		return sb.toString();
	}
}