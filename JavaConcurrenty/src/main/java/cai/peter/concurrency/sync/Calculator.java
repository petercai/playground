package cai.peter.concurrency.sync;


public class Calculator implements Runnable
{
	int number;

	public Calculator(int v )
	{
		number = v;
	}

	public void run()
	{
		for( int i=1;i<=10;i++)
		{
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(),number, i, i*number);
		}
	}
}
