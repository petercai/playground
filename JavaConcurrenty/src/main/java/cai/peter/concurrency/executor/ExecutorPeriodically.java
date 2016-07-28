package cai.peter.concurrency.executor;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorPeriodically
{
	class Task implements Runnable
	{
		String name;

		public Task(String name)
		{
			super();
			this.name =
					name;
		}
		public void run()
		{
			System.out.printf("%s: starting at: %s\n",name, new Date());
		}

	}

	@Test
	public void test()
	{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		System.out.printf("Main: starting at: %s\n", new Date());

		Task task =
				new Task("Task");

		ScheduledFuture<?> result =
				executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		for( int i=0;i<10;i++)
		{
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));

			try
			{
				TimeUnit.MILLISECONDS.sleep(500);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		executor.shutdown();

		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.printf("Main: finished at: %s\n", new Date());
	}
}
