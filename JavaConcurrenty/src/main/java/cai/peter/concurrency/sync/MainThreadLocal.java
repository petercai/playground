package cai.peter.concurrency.sync;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainThreadLocal
{
	static public void main(String[] args)
	{
		MainThreadLocal main =
				new MainThreadLocal();

		SafeTask task =
				new SafeTask();
		for(int i=0;i<10;i++)
		{
			Thread t =
					new Thread(task);
			t.start();

			try
			{
				TimeUnit.SECONDS.sleep(2);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	class UnsafeTask implements Runnable
	{
		Date startDate;

		public void run()
		{
			startDate = new Date();
			System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate);
			try
			{
				TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate);
		}
	}

	static class SafeTask implements Runnable
	{
		private static ThreadLocal<Date> startDate = new ThreadLocal<Date>()
		{
			protected Date initialValue()
			{
				return new Date();
			}
		};

		public void run()
		{
			System.out.printf("Starting Thread: %s : %s\n", Thread.currentThread().getId(), startDate.get());
			try
			{
				TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.printf("Thread Finished: %s : %s\n", Thread.currentThread().getId(), startDate.get());
		}
	}

}
