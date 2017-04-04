import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class CoinJam {
	
	static BigInteger one = new BigInteger("1");
	static BigInteger two = new BigInteger("2");
	static BigInteger three = new BigInteger("3");
	static BigInteger six = new BigInteger("6");
	static BigInteger thousand = new BigInteger("1000");
	
	public static void main (String[] args)
	{
		try
		{
			Scanner in = new Scanner(new BufferedReader(new FileReader("coinjamlarge.txt")));
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
				String jamCoinInBaseX = "" + new BigInteger(smallestJamCoin, base);
				isPrime = isPrime(jamCoinInBaseX);
				//If it is prime, store the base 10 value of the jam coin at base x
				if (!isPrime)
				{
					jamCoins[jamCoinIndex] = "" + new BigInteger(jamCoinInBaseX, 10);
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
			BigInteger sum = new BigInteger(smallestJamCoin, 2);
			sum = sum.add(new BigInteger("10"));
			smallestJamCoin = sum.toString(2);
			
		}//while
		
	}//findDeezCoins
	
	static boolean isPrime(String jamCoin) {
		BigInteger jamCoinBaseTen = new BigInteger(jamCoin, 10);
		if(jamCoinBaseTen.compareTo(two) == -1) 
			return false;
	    if(jamCoinBaseTen.compareTo(two) == 0 || jamCoinBaseTen.compareTo(three) == 0) 
	    	return true;
	    if((jamCoinBaseTen.mod(two)).intValue() == 0 || (jamCoinBaseTen.mod(three)).intValue() == 0)
	    	return false;
	    for(BigInteger i = new BigInteger("6"); i.compareTo(thousand) <= 0; i = i.add(six)) {
	        if((jamCoinBaseTen.mod(i.subtract(one))).intValue() == 0 ||
        	   (jamCoinBaseTen.mod(i.add(one))).intValue() == 0) 
	        	return false;
	    }
	    return true;
	}
	
	static void printDivisors (String[] jamCoins)
	{
		boolean foundDivisor = false;
		
		for (int a = 0; a < jamCoins.length; a++)
		{
			
			BigInteger baseTenJamCoin = new BigInteger(jamCoins[a]);
			
			for (BigInteger i = two; i.compareTo(baseTenJamCoin.divide(two)) == -1 && !foundDivisor; i = i.add(one))
			{
				if ((baseTenJamCoin.mod(i)).intValue() == 0)
				{
					System.out.print(i + " ");
					foundDivisor = true;
				}
				
			}//for
			
			foundDivisor = false;
		}//for
		
		System.out.println();
	}//printDivisors
	
	static BigInteger squareRootBigInt(BigInteger x)
	        throws IllegalArgumentException {
	    if (x.compareTo(BigInteger.ZERO) < 0) {
	        throw new IllegalArgumentException("Negative argument.");
	    }
	    // square roots of 0 and 1 are trivial and
	    // y == 0 will cause a divide-by-zero exception
	    if (x .equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
	        return x;
	    } // end if
	    BigInteger two = BigInteger.valueOf(2L);
	    BigInteger y;
	    // starting with y = x / 2 avoids magnitude issues with x squared
	    for (y = x.divide(two);
	            y.compareTo(x.divide(y)) > 0;
	            y = ((x.divide(y)).add(y)).divide(two));
	    return y;
	} // end bigIntSqRootFloor

}//CoinJam
