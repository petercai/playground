package cai.peter.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorFutureTask
{
	class ExecutableTask implements Callable<String>
	{
		String name;

		public ExecutableTask(String name)
		{
			super();
			this.name = name;
		}

		public  String getName()
		{
			return name;
		}
		
		public String call()
		{
			try
			{
				long duration = (long)(Math.random()*10);
				System.out.printf("%s: Waiting %d seconds for results\n", name, duration);
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Hello, world. I'm "+ name;
		}
		
	}
	
	class ResultTask extends FutureTask<String>
	{
		String name;

		public ResultTask(Callable<String> callable)
		{
			super(callable);
			name = ((ExecutableTask)callable).getName();
		}
		
		protected void done()
		{
			if(isCancelled())
				System.out.printf("%s: has been cancelled\n", name);
			else
				System.out.printf("%s: has finished\n", name);
		}
	}
	
	@Test
	public void test()
	{
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		ResultTask[] resultTasks = new ResultTask[5];
		for( int i=0;i<5;i++)
		{
			ExecutableTask t = new ExecutableTask("Taks "+i);
			resultTasks[i] = new ResultTask(t);
			
			executor.submit(resultTasks[i]);
			
		}
		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<resultTasks.length;i++)
			resultTasks[i].cancel(true);
		for(int i=0;i<resultTasks.length;i++)
		{
			try
			{
				if( !resultTasks[i].isCancelled())
					System.out.printf("%s\n", resultTasks[i].get());
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ExecutionException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}
}
