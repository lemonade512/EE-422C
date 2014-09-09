/**
 * Class to find palindromes in input text.
 * Solves EE422C programming assignment #1
 * Student Name: Phillip Lemons, Lab Section: 16830
 * @author Phillip Lemons
 * @version 0.01 2010-08-15
 */

package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class A1Driver {

	public static void main(String args[]) {
		if (args.length != 1) {
			System.err
					.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile(args[0]);

	}

	/**
	 * Opens the file specified in String filename, reads each line in it
	 * Invokes parse () on each line in the file, and prints out the string with
	 * all discovered palindromes in it.
	 * 
	 * @param filename
	 *            - the name of the file that needs to be read
	 */
	public static void processLinesInFile(String filename) {

		A1Driver myPalFinder = new A1Driver();
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				System.out.println("The input string is: " + s);
				String palindromes = myPalFinder.parse(s);
				System.out.println("The Palindromes found: " + palindromes);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Parses the inputString to find all palindromes based on rules specified
	 * in your assignment write-up.
	 * 
	 * @param inputString
	 *            - the String that needs to be parsed to discover palindromes
	 * 
	 * @return the String object containing the Palindromes found in the
	 *         inputString
	 */
	public String parse(String inputString) {
		// modify the following code. Add/delete anything you want after this
		// point.
		String outputString = new String(inputString); // makes a copy of
														// inputString.
		// Make sure to report each palindrome ONLY ONCE
		// TODO make sure to test with an empty line
		System.out.println("Starting string: " + outputString);
		outputString = filter(outputString);
		System.out.println("After filter: " + outputString);
		TreeSet<String> palindrome_set = findPalindromes(outputString);
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = palindrome_set.iterator();
		while (it.hasNext()) {
			String element = it.next();
			System.out.println(element);
		}
		return outputString;
	}

	/**
	 * Filters all punctuation and spaces out of the string, makes everything
	 * uppercase, and returns the new string.
	 * 
	 * @param inputString
	 *            - the String that needs to be filtered
	 * 
	 * @return the String object that has no punctuation and is all uppercase
	 */
	private String filter(String inputString) {
		String s = inputString.replaceAll("[^a-zA-Z]", "").toUpperCase();
		return s;
	}

	private TreeSet<String> findPalindromes(String inputString) {
		TreeSet<String> output_set = new TreeSet<String>();
		//for (int i = 0; i < inputString.length(); i++) {
		//	
		//}
		output_set.add("Hello");
		output_set.add("Hello");
		output_set.add("Goodbye");
		System.out.println(isPalindrome("Hello"));
		System.out.println(isPalindrome("Goodbye"));
		System.out.println(isPalindrome("HELLEH"));
		return output_set;
	}
	
	/**
	 * Determines if the given string is a palindrome. This is a case-sensitive
	 * method.
	 * 
	 * @param inputString - The string you want to check
	 * 
	 * @return boolean value that tells if the String is a palindrome or note
	 */
	private boolean isPalindrome(String inputString) {
		String reversed = new StringBuilder(inputString).reverse().toString();
		
		if(reversed.equals(inputString)) {
			return true;
		} else {
			return false;
		}
	}

}