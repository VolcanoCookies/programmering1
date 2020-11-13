package eu1;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		int correct = 0;
		int incorrect = 0;
		
		for (int j = 0; j < 10000; j++) {
			
			Random random = new Random();
			
			int[] values = new int[19];
			
			int min = random.nextInt(100);
			
			for (int i = 0; i < values.length; i++) {
				values[i] = (min + 1 + random.nextInt(100));
			}
			
			values[16] = min;
			
			if (min != min(values)) {
				
				System.out.println(values.length);
				System.out.println(min);
				System.out.println(min(values));
				
				incorrect++;
				
			} else {
				correct++;
			}
			
		}
		
		System.out.println(correct + " correct");
		System.out.println(incorrect + " incorrect");
		
	}
	
	// min returnerar det minsta elementet i en sekventiell samling.
	// Om samlingen är tom, kastas ett undantag av typen IllegalArgumentException.
	public static int min(int[] element) throws IllegalArgumentException {
		if (element.length == 0) {
			throw new IllegalArgumentException("tom samling");
		}
		// hör ihop med spårutskriften 2:
		// int antalVarv = 1;
		int[] sekvens = element;
		int antaletPar = sekvens.length / 2;
		int antaletOparadeElement = sekvens.length % 2;
		int antaletTankbaraElement = antaletPar + antaletOparadeElement;
		int[] delsekvens = new int[antaletTankbaraElement];
		int i;
		int j;
		while (antaletPar > 0) {
			// skilj ur en delsekvens med de tänkbara elementen
			i = 0;
			j = 0;
			while (j < antaletPar) {
				delsekvens[j++] = (sekvens[i] < sekvens[i + 1]) ? sekvens[i] : sekvens[i + 1];
				i += 2;
			}
			if (antaletOparadeElement == 1) {
				delsekvens[j] = sekvens[antaletPar * 2];
			}
			// utgå nu ifrån delsekvensen
			sekvens = delsekvens;
			antaletPar = antaletTankbaraElement / 2;
			antaletOparadeElement = antaletTankbaraElement % 2;
			antaletTankbaraElement = antaletPar + antaletOparadeElement;
			// spårutskrift 1 – för att följa sekvensen
			// System.out.println (java.util.Arrays.toString (sekvens));
			// spårutskrift 2 - för att avsluta loopen i förväg
			// (för att kunna se vad som händer i början)
			// if (antalVarv++ == 10)
			// System.exit (0);
		}
		// sekvens[0] är det enda återstående tänkbara elementet
		// - det är det minsta elementet
		return sekvens[0];
	}
	
}