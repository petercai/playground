package cai.peter.concurrency.sync;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {






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
