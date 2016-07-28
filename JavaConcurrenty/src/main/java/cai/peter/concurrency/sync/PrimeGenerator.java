package cai.peter.concurrency.sync;

public class PrimeGenerator extends Thread
{
	public void run()
	{
		long number = 1L;
		while(true)
		{
			if( isPrime(number))
			{
				System.out.printf("Number %d is prime\n", number);
			}
			if( isInterrupted())
			{
				System.out.println("The Prime Generator has been interrupted");
				return;
			}
			number++;
		}
	}

	boolean isPrime(long number)
	{
		if( number <= 2 )
			return  true;
		for(long i=2;i<number;i++)
			if( number % i == 0 )
				return false;

		return true;
	}
}
