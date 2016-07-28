package cai.peter.concurrency.sync;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainThreadGroup
{
	static public void main(String[] args)
	{
		MainThreadGroup main =
				new MainThreadGroup();
		ThreadGroup tg =
				new ThreadGroup("Searcher");
		Result result =
				main.new Result();
		SearchTask task =
				main.new SearchTask(result);

		for( int i=0;i<5;i++)
		{
			Thread t =
					new Thread(tg, task);
			t.start();
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.printf("# of threads: %d\n", tg.activeCount());
		System.out.println("Information about the Thread Group");
		tg.list();

		Thread[] threads = new Thread[tg.activeCount()];
		tg.enumerate(threads);
		for(int i=0;i<tg.activeCount();i++)
		{
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}

		waitfFinish(tg);
		tg.interrupt();

	}

	private static void waitfFinish(ThreadGroup tg)
	{
		while(tg.activeCount()>4)
		{
				try
				{
					TimeUnit.SECONDS.sleep(1);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	class Result
	{
		String name;
	}

	class SearchTask implements Runnable
	{
		Result result;
		public SearchTask(Result res )
		{
			result = res;
		}

		public void run()
		{
			String name =
					Thread.currentThread().getName();
			System.out.printf("Thread %s: Start \n", name);
			try
			{
				doTask();
				result.name = name;
			}
			catch(InterruptedException e)
			{
				System.out.printf("Thread %s: Interrupted\n", name);
				return;
			}
		}

		void doTask() throws InterruptedException
		{
			Random random =
					new Random((new Date()).getTime());
			int val = (int)(random.nextDouble()*100);
			System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), val);
			TimeUnit.SECONDS.sleep(val);
		}
	}
}
