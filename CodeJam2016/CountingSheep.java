package CodeJam2016;
/*
 * Problem

Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster. First, she picks a number N. Then she starts naming N, 2 × N, 3 × N, and so on. Whenever she names a number, she thinks about all of the digits in that number. She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has seen at least once so far as part of any number she has named. Once she has seen each of the ten digits at least once, she will fall asleep.

Bleatrix must start with N and must always name (i + 1) × N directly after i × N. For example, suppose that Bleatrix picks N = 1692. She would count as follows:

N = 1692. Now she has seen the digits 1, 2, 6, and 9.
2N = 3384. Now she has seen the digits 1, 2, 3, 4, 6, 8, and 9.
3N = 5076. Now she has seen all ten digits, and falls asleep.
What is the last number that she will name before falling asleep? If she will count forever, print INSOMNIA instead.

Input

The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line with a single integer N, the number Bleatrix has chosen.

Output

For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is the last number that Bleatrix will name before falling asleep, according to the rules described in the statement.

Limits

1 ≤ T ≤ 100.
Small dataset

0 ≤ N ≤ 200.
Large dataset

0 ≤ N ≤ 106.
 */

import java.util.*;
import java.io.*;

public class CountingSheep {
	
	//COME BACK TO THIS QUESTION WHEN YOU AREN'T TIRED SLEEP HEAD!
  public static void main(String[] args) {
	  
    try
    {
    	
	 Scanner in = new Scanner(new BufferedReader(new FileReader("countingsheepinputlarge.txt")));
	    int cases = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
	    
	    //Main loop to count all cases
	    for (int caseNumber = 1; caseNumber <= cases; caseNumber++)
	    {
	    	int N = in.nextInt();
    		System.out.print("Case #" + caseNumber + ": ");
	    	
	    	if (N != 0)
	    	{
	    		findNumber(N, caseNumber);
	    	}
	    	
	    	else
	    	{
	    		System.out.println("INSOMNIA");
	    	}
	    }
    }
  
    catch (IOException e)
    {
    	System.out.println(e.getMessage());
    }
    
  }
    
    static int findNumber (int N, int caseNumber)
    {
        String[] digitsSeen = new String [10];
        boolean foundAllDigits = true;
    	int currentNumber; //The current number we're using.
    	String number; //Stores the current number as a String.

    	for (int b = 1; b <= 100000; b++)
    	{
    		currentNumber = N * b;
    		number = "" + currentNumber;
    		
    		//Go through the digit and put in numbers we haven't seen yet.
    		for (int c = 0; c < number.length(); c++)
    		{
    			if (digitsSeen[Character.getNumericValue(number.charAt(c))] == null)
    			{
    				digitsSeen[Character.getNumericValue(number.charAt(c))] = "" + Character.getNumericValue(number.charAt(c));
    			}
    		}
    		
    		//Do a check to see if we've seen all digits
    		for (int d = 0; d < digitsSeen.length && foundAllDigits; d++)
    		{
    			if (digitsSeen[d] == null)
    				foundAllDigits = false;
    		}
    		
    		//If found, end our search.
    		if (foundAllDigits)
    		{
    			System.out.println(currentNumber);
    			return currentNumber;
    		}
    		
    		//Assume we've found everything and check again.
    		else
    		{
    			foundAllDigits = true;
    		}
    			
    	}
    	
    	System.out.println("AHW:LKJASD:LASKJD::");
    	return 0;
    }
}
    











