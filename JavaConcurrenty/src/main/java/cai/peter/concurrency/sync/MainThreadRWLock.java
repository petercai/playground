package cai.peter.concurrency.sync;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainThreadRWLock
{
	static public void main(String[] args)
	{
		MainThreadRWLock main =
				new MainThreadRWLock();

		PriceInfo priceInfo =
				main.new PriceInfo();

		Reader[] readers = new Reader[5];
		Thread[] ts = new Thread[5];

		for(int i=0;i<5;i++)
		{
			readers[i] = main.new Reader(priceInfo);
			ts[i] = new Thread(readers[i]);
		}

		Writer writer = main.new Writer(priceInfo);
		Thread t = new Thread(writer);

		for(int i=0;i<5;i++)
			ts[i].start();

		t.start();


	}

	class PriceInfo
	{
		double price1;
		double price2;

		ReadWriteLock lock;

		public PriceInfo()
		{
			price1 = 1.0;
			price2 = 2.0;
			lock = new ReentrantReadWriteLock();
		}

		public double getPrice1()
		{
			lock.readLock().lock();
			double value = price1;
			lock.readLock().unlock();
			return value;
		}

		double getPrice2()
		{
			lock.readLock().lock();
			double value = price2;
			lock.readLock().unlock();
			return value;
		}

		void setPrice(double p1, double p2)
		{
			lock.writeLock().lock();
			price1 = p1;
			price2 = p2;
			lock.writeLock().unlock();
		}

	}

	class Reader implements Runnable
	{
		PriceInfo priceInfo;

		public Reader(PriceInfo priceInfo)
		{
			super();
			this.priceInfo =
					priceInfo;
		}

		public void run()
		{
			for( int i=0;i<10;i++)
			{
				System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), priceInfo.getPrice1());
				System.out.printf("%s: Prices2: %f\n", Thread.currentThread().getName(), priceInfo.getPrice2());
			}
		}
	}

	class Writer implements Runnable
	{
		PriceInfo priceInfo;

		public Writer(PriceInfo priceInfo)
		{
			super();
			this.priceInfo =
					priceInfo;
		}

		public void run()
		{
			for(int i=0;i<3;i++)
			{

			System.out.printf("Writer: Attempt to modify the prices\n");
			priceInfo.setPrice(Math.random()*10, Math.random()*8);
			System.out.println("Writer: Prices have been modified.");

			try
			{
				Thread.sleep(2);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	}
}
