package cai.peter.concurrency.sync;

import java.lang.Thread.UncaughtExceptionHandler;

public class MainThreadException
{
	public class Task implements Runnable
	{
		public void run()
		{
			int num = Integer.parseInt("TTT");
		}
	}
	static public void main(String[] args)
	{
		MainThreadException main =
				new MainThreadException();

		Task task =
				main.new Task();

		Thread t =
				new Thread(task);
		t.setUncaughtExceptionHandler(main.new ExceptionHandler());
		t.start();
	}

	class ExceptionHandler implements UncaughtExceptionHandler
	{
		public void uncaughtException(Thread t, Throwable e)
		{
			System.out.println("An exception has been capture");
			System.out.printf("Thread %s\n", t.getId());
			System.out.printf("Exception %s: %s\n", e.getClass().getName(), e.getMessage());
			System.out.println("Stack Trace:");
			e.printStackTrace(System.out);
			System.out.printf("Thread status: %s\n", t.getState());
		}
	}

}
