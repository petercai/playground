package cai.peter.concurrency.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorThreadPool
{
	class Task implements Runnable
	{
		Date initDate;
		String name;

		public Task(String name)
		{
			initDate = new Date();
			this.name = name;
		}
		public void run()
		{
			System.out.printf("%s: Task %s: created on: %s\n", Thread.currentThread().getName(), name, initDate);
			System.out.printf("%s: task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());

			try
			{
				long duration = (long)(Math.random()*10);
				System.out.printf("%s:Task %s: Doing a task during %d seconds\n", Thread.currentThread().getName(), name, duration);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.printf("%s: Taks %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());

		}
	}

	class Server
	{
		ThreadPoolExecutor executor;

		public Server()
		{
//			executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();
			executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
		}
		void executeTask(Task task)
		{
			System.out.printf("Server: a new task has arrive\n");
			executor.execute(task);
			System.out.printf("Server: Pool size: %d\n", executor.getPoolSize());
			System.out.printf("Server: active count: %d\n", executor.getActiveCount());
			System.out.printf("server: completed tasks: %d\n", executor.getCompletedTaskCount());
			System.out.printf("Server: Task count: %d\n", executor.getTaskCount());
		}
		void endServer()
		{
			executor.shutdown();
		}
	}

	@Test
	public void test()
	{
		Server server = new Server();
		for(int i=0;i<100;i++)
		{
			Task t = new Task("Task "+i);
			server.executeTask(t);

		}
		server.endServer();
	}

}
