package cai.peter.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorRejectTask
{
	class RejectedTaskController implements RejectedExecutionHandler
	{
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
		{
			System.out.printf("RejectedTaskController: the task %s has been rejected\n", r.toString());
			System.out.printf("RejectedTaskController: %s\n", executor.toString());
			System.out.printf("RejectedTaskController: Terminting: %s\n", executor.isTerminating());
			System.out.printf("RejectedTaskController: Terminatd: %s\n", executor.isTerminated());
		}
	}
	
	class Task implements Runnable
	{
		@Override
		public String toString()
		{
			return "Task [name=" + name
					+ "]";
		}
		String name;

		public Task(String name)
		{
			super();
			this.name = name;
		}
		public void run()
		{
			System.out.println("Taks "+name+": Starting");
			try
			{
				long duration = (long)(Math.random()*10);
				System.out.printf("Task %s: ReportGenerator: Generating a report during %d seconds\n", name, duration);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.printf("Taks %s: Ending\n", name);
		}
	}
	
	@Test
	public void test()
	{
		RejectedTaskController controller = new RejectedTaskController();
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
		executor.setRejectedExecutionHandler(controller);
		System.out.println("Main: Starting");
		for( int i=0;i<3;i++)
		{
			Task t = new Task("Task "+i);
			executor.submit(t);
			
		}
		System.out.println("Main: Shutting down the executor.");
		executor.shutdown();
		
		System.out.println("Main: sending another task");
		Task t = new Task("RejectedTask");
		executor.submit(t);
		System.out.println("Main: end");
		
	}
}
