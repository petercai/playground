package cai.peter.concurrency.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSemaphore
{
	public static void main(String[] args)
	{
		ThreadSemaphore main =
				new ThreadSemaphore();

		PrintQueue printQueue =
				main.new PrintQueue();

		Thread[] t = new Thread[10];
		for(int i=0;i<10;i++)
			t[i] = new Thread(main.new Job(printQueue), "Thread "+i);

		for(int i=0;i<10;i++)
			t[i].start();
	}

	class PrintQueue
	{
		final Semaphore semaphore;
		boolean freePrinters[];
		Lock lockPrinters;
		public PrintQueue()
		{
			super();
			this.semaphore =
					new Semaphore(3);
			freePrinters = new boolean[3];
			for( int i=0;i<3;i++)
				freePrinters[i] = true;
			lockPrinters = new ReentrantLock();
		}

		void printJob(Object document)
		{
			try
			{
				semaphore.acquire();
				int assignedPrinter = getPrinter();
				long duration = (long)(Math.random()*10);
				System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n", Thread.currentThread().getName(), assignedPrinter, duration);
				TimeUnit.SECONDS.sleep(duration);
				freePrinters[assignedPrinter]=true;
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				semaphore.release();
			}
		}

		int getPrinter()
		{
			int ret = -1;
			try
			{
				lockPrinters.lock();
				for( int i=0;i<freePrinters.length;i++)
				{
					if( freePrinters[i])
					{
						ret = i;
						freePrinters[i] = false;
						break;
					}
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				lockPrinters.unlock();
			}

			return ret;
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
			System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
			printQueue.printJob(new Object());
			System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
		}
	}
}
