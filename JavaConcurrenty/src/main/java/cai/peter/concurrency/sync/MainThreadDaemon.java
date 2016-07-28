package cai.peter.concurrency.sync;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class MainThreadDaemon {

	public class Event
	{
		Date date;
		public Date getDate()
		{
			return date;
		}
		public void setDate(Date date)
		{
			this.date =
					date;
		}
		public String getEvent()
		{
			return event;
		}
		public void setEvent(String event)
		{
			this.event =
					event;
		}
		String event;
	}
	public class WriterTask implements Runnable
	{
		Deque<Event> deque;

		public WriterTask(Deque<Event> deque)
		{
			this.deque = deque;
		}

		public void run()
		{
			for(int i=1;i<100;i++)
			{
				Event e =
						new Event();
				e.setDate(new Date());
				e.setEvent(String.format("The thread %s has generated an event", Thread.currentThread().getId()));
				deque.addFirst(e);
				try
				{
					TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}

		}
	}
	public class CleanerTask extends Thread
	{
		Deque<Event> deque;

		public CleanerTask(Deque<Event> d)
		{
			deque = d;
			setDaemon(true);
		}

		public void run()
		{
			while(true)
			{
				Date date =
						new Date();
				clean(date);
			}
		}

		void clean(Date date)
		{
			long dif;
			boolean delete;

			if(deque.size()==0) return;

			delete = false;
			do
			{
				Event event =
						deque.getLast();
				dif = date.getTime() - event.getDate().getTime();
				if( dif > 10000)
				{
					System.out.printf("Cleaner: %s\n", event.getEvent());
					deque.removeLast();
					delete = true;
				}
			}while(dif>10000);

			if( delete )
				System.out.printf("Cleaner: size of the queue: %d\n", deque.size());
		}
	}
	static public void main(String[] args)
	{
		MainThreadDaemon main =
				new MainThreadDaemon();
		Deque<Event> deque =
				new ArrayDeque<Event>();
		WriterTask writer =
				main.new WriterTask(deque);
		for(int i=0;i<3;i++)
		{
			Thread t =
					new Thread(writer);
			t.start();
		}

		CleanerTask cleaner=
				main.new CleanerTask(deque);
		cleaner.start();
	}

//	static public void main(String[] args)
//	{
//		DataSourcesLoader dsLoader = new DataSourcesLoader();
//		Thread t1 = new Thread(dsLoader, "DataSourceThread");
//		NetworkConnectionsLoader ncLoader =
//				new NetworkConnectionsLoader();
//		Thread t2 =
//				new Thread(ncLoader,"NetworkConnectionThread");
//		t1.start();
//		t2.start();
//
//		try
//		{
//			t1.join();
//			t2.join();
//		}
//		catch(InterruptedException e)
//		{
//			e.printStackTrace();
//		}
//		System.out.printf("Main: configuration has been loaded: %s\n", new Date());
//	}

//	static public void main(String[] args)
//	{
//		FileSearch searcher = new FileSearch("c:\\workspace", "dev4.sh");
//		Thread t = new Thread(searcher);
//		t.start();
//
//		try
//		{
//			TimeUnit.SECONDS.sleep(10);
//		}
//		catch( InterruptedException e)
//		{
//			e.printStackTrace();
//		}
//
//		t.interrupt();
//	}

//	static public void main(String[] args)
//	{
//		Thread t = new PrimeGenerator();
//		t.start();
//
//		try
//		{
//			Thread.sleep(5000);
//		}
//		catch(InterruptedException e)
//		{
//			e.printStackTrace();
//		}
//		t.interrupt();
//
//	}


//	static public void main(String[] args)
//	{
//		for(int i=0;i<10;i++)
//		{
//			Calculator cal = new Calculator(i);
//			Thread t = new Thread(cal);
//			t.start();
//		}
//	}
}
