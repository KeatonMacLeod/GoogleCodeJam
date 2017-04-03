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
		
		while (lineCount < J)
		{
			
			for (base = 2; base <= 10 && !isPrime; base++)
			{
				//Convert jam coins to different values depending on the base
				String jamCoinInBaseX = Long.toString(Long.parseLong(smallestJamCoin, base));
				isPrime = isPrime(Long.parseLong(jamCoinInBaseX));
				
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
			sum = sum + 10;
			smallestJamCoin = Long.toBinaryString(sum);
			
		}//while
		
	}//findDeezCoins
	
	static boolean isPrime(long jamCoin) {
		long jamCoinBaseTen = Long.parseLong("" + jamCoin, 10);
		
	    if(jamCoinBaseTen < 2) return false;
	    if(jamCoinBaseTen == 2 || jamCoinBaseTen == 3) return true;
	    if(jamCoinBaseTen%2 == 0 || jamCoinBaseTen%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(jamCoinBaseTen)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(jamCoinBaseTen%(i-1) == 0 || jamCoinBaseTen%(i+1) == 0) return false;
	    }
	    return true;
	}
	
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
