package cai.peter.concurrency.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorSchedule
{
	class Task implements Callable<String>
	{
		String name;

		public Task(String name)
		{
			super();
			this.name =
					name;
		}

		public String call()
		{
			System.out.printf("%s: staring at: %s\n", name, new Date());
			return "Hello, world";
		}
	}

	@Test
	public void test()
	{
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
		System.out.printf("Main: starting at: %s\n", new Date());
		for(int i=0;i<5;i++)
		{
			Task t =
					new Task("Task "+i);
			executor.schedule(t, i+1, TimeUnit.SECONDS);
		}
		executor.shutdown();

		try
		{
			executor.awaitTermination(1, TimeUnit.DAYS);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.printf("Main: ends at: %s\n", new Date());
	}
}
