package cai.peter.concurrency.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class MainThreadLock
{
	@Test
	public void test()
	{

		PrintQueue printQueue =
				new PrintQueue();

		Thread[] threads = new Thread[10];

		for( int i=0;i<10;i++)
		{
			threads[i] = new Thread(new Job(printQueue),"Thread "+i);
		}
		for( Thread t : threads)
			t.start();
	}

	class PrintQueue
	{
		final Lock lock = new ReentrantLock();

		public void printJob(Object document)
		{
			lock.lock();

			try
			{
				Long duration = (long)(Math.random()*10000);
				System.out.println(Thread.currentThread().getName()+": PrintQueue: Printing a Job during "+duration/1000+" seconds");
				Thread.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
		}
	}

	class Job implements Runnable
	{
		PrintQueue printQueue;

		public Job(PrintQueue printQueue)
		{
			super();
			this.printQueue =
					printQueue;
		}

		public void run()
		{
			System.out.printf("%s Going to print a documents\n", Thread.currentThread().getName());
			printQueue.printJob(new Object());
			System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
		}

	}
}
