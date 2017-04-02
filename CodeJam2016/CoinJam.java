package CodeJam2016;

import java.io.*;
import java.util.Scanner;

public class CoinJam {
	
	public static void main (String[] args)
	{
		try
		{
			Scanner in = new Scanner(new BufferedReader(new FileReader("coinjamsmall.txt")));
			int cases = in.nextInt();
			int N; //The length of the jam coin
			int J; //The number of different jam coins you need to print
			
			//Need to print out 9 integers representing divisors from bases 2-10
			
			for (int caseCount = 1; caseCount <= cases; caseCount++)
			{
				N = in.nextInt();
				J = in.nextInt();
				
				System.out.println("Case #" + caseCount + ": ");
				String smallestJamCoin = getSmallestJamCoin(N);
				findDeezCoins(smallestJamCoin,J);
			}//for
			
			in.close();
		}//try
		
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}//catch

	}//main
	
	//Build the smallest jam coin for N
	static String getSmallestJamCoin (int N)
	{
		String smallestJamCoin = "";
	
		for (int a = 0; a < N; a++)
		{
			if (a == 0)
				smallestJamCoin += "1";

			else if (a == N-1)
				smallestJamCoin += "1";
					
			else
				smallestJamCoin += "0";

		}//for
		
		return smallestJamCoin;
		
	}//getSmallestJamCoin
	
	static void findDeezCoins(String smallestJamCoin, int J)
	{
		int base;
		boolean isPrime = false;
		int lineCount = 0;
		String[] jamCoins = new String [9]; //Store the values of a jam coin in different bases.
		int jamCoinIndex = 0;
		int one = Integer.parseInt("1", 2); //Used to increment the current jam coin
		
		while (lineCount < J)
		{
			
			for (base = 2; base <= 10 && !isPrime; base++)
			{
				//Convert jam coins to different values depending on the base
				String jamCoinInBaseX = Long.toString(Long.parseLong(smallestJamCoin, base));
				isPrime = checkIfPrime(Long.parseLong(jamCoinInBaseX));
				
				//If it is prime, store the base 10 value of the jam coin at base x
				if (!isPrime)
				{
					jamCoins[jamCoinIndex] = Long.toString(Long.parseLong(jamCoinInBaseX, 10));
					jamCoinIndex++;
				}
				
				else
				{
					//Begin to rewrite the current coins in the array for the next jam coin.
					jamCoinIndex = 0;
				}
				
			}//for
			
			//If it is a proper jam coin
			if (!isPrime)
			{
				System.out.print(smallestJamCoin + " ");
				printDivisors(jamCoins); //Print divisors for each jam coin in bases 2-10
				lineCount++;
			}
			
			//Reset the jam coin for checking
			else
			{
				isPrime = false;
			}
			
			//Reset the jam coin values in base 10 for the next jam coin
			jamCoinIndex = 0;
			
			//Increment the jam coin by 1
			long sum = Long.parseLong(smallestJamCoin, 2);
			sum = sum + one;
			smallestJamCoin = Long.toBinaryString(sum);
			
		}//while
		
	}//findDeezCoins
	
	//Check if a jam coin is a prime number in base 10
	static boolean checkIfPrime (long jamCoin)
	{
		long jamCoinBaseTen = Long.parseLong("" + jamCoin, 10);
		
	    //Check if the jam coin is a multiple of 2
	    if (jamCoinBaseTen %2 == 0)
	    	return false;
	    
	    //if not, then just check the odds
	    for(int a=3; a*a<=jamCoinBaseTen; a+=2) 
	    {
	        if(jamCoinBaseTen % a == 0)
	            return false;
	    }
	    
	    return true;
	    
	}//checkIfPrime
	
	static void printDivisors (String[] jamCoins)
	{
		boolean foundDivisor = false;
		
		for (int a = 0; a < jamCoins.length; a++)
		{
			
			for (int i = 2; i < Long.parseLong(jamCoins[a]) / 2 && !foundDivisor; i++)
			{
				if (Long.parseLong(jamCoins[a]) % i == 0)
				{
					System.out.print(i + " ");
					foundDivisor = true;
				}
				
			}//for
			
			foundDivisor = false;
		}//for
		
		System.out.println();
	}//printDivisors

}//CoinJam
