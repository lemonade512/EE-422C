package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class to find palindromes in input text. Solves EE422C programming assignment #1
 * Student Name: Phillip Lemons, Lab Section: 16830
 * 
 * @author Phillip Lemons
 * @version 1.0 2010-08-15
 */

public class A1Driver
{

	public static void main(String args[])
	{
		if (args.length != 1)
		{
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile(args[0]);

	}

	/**
	 * Opens the file specified in String filename, reads each line in it
	 * Invokes parse () on each line in the file, and prints out the string with
	 * all discovered palindromes in it.
	 * 
	 * @param filename - the name of the file that needs to be read
	 */
	public static void processLinesInFile(String filename)
	{

		A1Driver myPalFinder = new A1Driver();
		try
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);

			for (String s = reader.readLine(); s != null; s = reader.readLine())
			{
				System.out.println("The input string is: " + s);
				String palindromes = myPalFinder.parse(s);
				System.out.println("The Palindromes found: " + palindromes);
			}
		} catch (FileNotFoundException e)
		{
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e)
		{
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Parses the inputString to find all palindromes based on rules specified
	 * in your assignment write-up.
	 * 
	 * @param inputString - The String that needs to be parsed to discover palindromes
	 * 
	 * @return the String object containing the Palindromes found in the
	 *         inputString
	 */
	public String parse(String inputString)
	{
		String filteredString = filter(inputString);
		TreeSet<String> palindromeSet = findPalindromes(filteredString);

		// Remove middle palindromes
		TreeSet<String> blacklist = new TreeSet<String>();
		Iterator<String> it = palindromeSet.iterator();
		while (it.hasNext())
		{
			String element = it.next();
			blacklist.addAll(findMiddlePalindromes(element));
		}
		palindromeSet.removeAll(blacklist);

		// Reset the iterator to loop through again adding the palindromes to report
		it = palindromeSet.iterator();
		StringBuilder outputString = new StringBuilder();
		String loopDelim = "";
		while (it.hasNext())
		{
			String element = it.next();
			outputString.append(loopDelim);
			outputString.append(element);
			loopDelim = " ";
		}
		return outputString.toString();
	}

	/**
	 * Filters all punctuation and spaces out of the string, makes everything
	 * uppercase, and returns the new string.
	 * 
	 * @param inputString - the String that needs to be filtered
	 * 
	 * @return the String object that has no punctuation and is all uppercase
	 */
	private String filter(String inputString)
	{
		String s = inputString.replaceAll("[^a-zA-Z]", "").toUpperCase();
		return s;
	}
	
	/**
	 * Finds all of the palindromes in the input string
	 * @param inputString
	 * @return TreeSet<String> with all the palindromes (TreeSet is a sorted set)
	 */
	private TreeSet<String> findPalindromes(String inputString)
	{
		TreeSet<String> outputSet = new TreeSet<String>();
		// Find all palindromes
		for (int i = 0; i < inputString.length(); i++)
		{
			for (int j = i + 3; j <= inputString.length(); j++)
			{
				String sub = inputString.substring(i, j);
				if (isPalindrome(sub))
				{
					outputSet.add(sub);
				}
			}
		}
		return outputSet;
	}

	/**
	 * Finds all of the palindromes in the middle of another palindrome
	 * @param inputString - palindrome to search
	 * @return TreeSet<String> with all the middle palindromes
	 */
	private TreeSet<String> findMiddlePalindromes(String inputString)
	{
		TreeSet<String> output = new TreeSet<String>();
		for (int i = 1; i < inputString.length() / 2; i++)
		{
			output.add(inputString.substring(i, inputString.length() - i));
		}
		return output;
	}

	/**
	 * Determines if the given string is a palindrome. This is a case-sensitive
	 * method.
	 * 
	 * @param inputString - The string you want to check
	 * 
	 * @return boolean value that tells if the String is a palindrome or note
	 */
	private boolean isPalindrome(String inputString)
	{
		String reversed = new StringBuilder(inputString).reverse().toString();

		if (reversed.equals(inputString))
		{
			return true;
		} else
		{
			return false;
		}
	}

}