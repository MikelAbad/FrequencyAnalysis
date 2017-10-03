package mainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Decrypt {
	private static String text;
	private static double sortedDes[] = new double[26];
	private static char[] spanish = new char[26];

	public static void main(String args[]) throws IOException {
		String path = args[0];
		text = readFile(path);
		createSpanishAlphabet();
		countFreq();
	}

	private static String readFile(String path) throws IOException {
		File f = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(f));

		String line = null;
		String reading = "";
		while ((line = br.readLine()) != null) {
			reading += line;
		}

		br.close();
		System.out.println();
		System.out.println("Source text:");
		System.out.println(reading);
		System.out.println();

		return reading;
	}

	private static void createSpanishAlphabet() {
		spanish[0] = 'E';
		spanish[1] = 'A';
		spanish[2] = 'O';
		spanish[3] = 'S';
		spanish[4] = 'R';
		spanish[5] = 'N';
		spanish[6] = 'I';
		spanish[7] = 'D';
		spanish[8] = 'L';
		spanish[9] = 'C';
		spanish[10] = 'T';
		spanish[11] = 'U';
		spanish[12] = 'M';
		spanish[13] = 'P';
		spanish[14] = 'B';
		spanish[15] = 'G';
		spanish[16] = 'V';
		spanish[17] = 'Y';
		spanish[18] = 'Q';
		spanish[19] = 'H';
		spanish[20] = 'F';
		spanish[21] = 'Z';
		spanish[22] = 'J';
		spanish[23] = 'X';
		spanish[24] = 'W';
		spanish[25] = 'K';
	}

	private static void countFreq() throws IOException {
		// Count number of letters
		char[] textArray = text.toCharArray();
		int[] cont = new int[26];

		for (char c : textArray) {
			if (c == 'A') cont[0]++;
			if (c == 'B') cont[1]++;
			if (c == 'C') cont[2]++;
			if (c == 'D') cont[3]++;
			if (c == 'E') cont[4]++;
			if (c == 'F') cont[5]++;
			if (c == 'G') cont[6]++;
			if (c == 'H') cont[7]++;
			if (c == 'I') cont[8]++;
			if (c == 'J') cont[9]++;
			if (c == 'K') cont[10]++;
			if (c == 'L') cont[11]++;
			if (c == 'M') cont[12]++;
			if (c == 'N') cont[13]++;
			if (c == 'O') cont[14]++;
			if (c == 'P') cont[15]++;
			if (c == 'Q') cont[16]++;
			if (c == 'R') cont[17]++;
			if (c == 'S') cont[18]++;
			if (c == 'T') cont[19]++;
			if (c == 'U') cont[20]++;
			if (c == 'V') cont[21]++;
			if (c == 'W') cont[22]++;
			if (c == 'X') cont[23]++;
			if (c == 'Y') cont[24]++;
			if (c == 'Z') cont[25]++;
		}
		
		// Calculate total
		double total = 0;
		for (int i = 0; i < 26; i++) {
			total = total + cont[i];
		}
		System.out.println("Total number of letters: " + (int) total);

		// Create freq array
		double[] freqs = new double[26];
		double[] sortedAsc = new double[26];
		for (int i = 0; i < 26; i++) {
			freqs[i] = cont[i] / total;
			sortedAsc[i] = cont[i] / total;
		}

		// Sort freq array (ascending)
		Arrays.sort(sortedAsc);

		// Sort freq array (descending) using the previous one
		int k = 25;
		for (int i = 0; i < 26; i++) {
			sortedDes[i] = sortedAsc[k];
			k--;
		}

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] abc = alphabet.toCharArray();
		
		// Print the information
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < 26; i++) {
			System.out.println("Number of " + abc[i] + "'s: " + cont[i] + "	| Frequency: " + df.format(freqs[i] * 100)
					+ "%	| Spanish prediction: " + getSpanishLetter(freqs[i]));
		}
	}

	private static String getSpanishLetter(double freq) {
		String letter = "";
		int i = 0;
		boolean found = false;
		while (!found) {
			if (sortedDes[i] == freq) {
				letter = "" + spanish[i];
				found = true;
			}
			i++;
		}
		return letter;
	}
}