package cai.peter.concurrency.sync;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class MainThreadNotify
{
	static public void main(String[] args)
	{
		MainThreadNotify main =
				new MainThreadNotify();
		EventStorage storage =
				main.new EventStorage();

		Producer producer =
				main.new Producer(storage);
		Thread tp =
				new Thread(producer);

		Consumer consumer =
				main.new Consumer(storage);
		Thread tc =
				new Thread(consumer);

		tp.start();
		tc.start();
	}

	class EventStorage
	{
		int maxSize;
		Queue<Date> storage;

		public EventStorage()
		{
			maxSize = 10;
			storage = new LinkedList<>();
		}

		public synchronized void set()
		{
			while(storage.size()==maxSize)
			{
				try
				{
					wait();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			storage.offer(new Date());
			System.out.printf("Set: %d\n", storage.size());
			notifyAll();
		}

		public synchronized void get()
		{
			while(storage.size()==0)
			{
				try
				{
					wait();
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.printf("Get: %d: %s\n", storage.size(), storage.poll());
			notifyAll();
		}
	}

	class Producer implements Runnable
	{
		EventStorage storage;

		public Producer(EventStorage storage)
		{
			super();
			this.storage =
					storage;
		}

		public void run()
		{
			for(int i=0;i<100;i++)
			{
				storage.set();
			}
		}

	}

	class Consumer implements Runnable
	{
		EventStorage storage;

		public Consumer(EventStorage storage)
		{
			super();
			this.storage =
					storage;
		}

		public void run()
		{
			for(int i=0;i<100;i++)
				storage.get();
		}
	}

}
