package cai.peter.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class ExecutorInvokeAll
{
	class Result
	{
		String name;
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name =
					name;
		}
		public int getValue()
		{
			return value;
		}
		public void setValue(int value)
		{
			this.value =
					value;
		}
		int value;

	}

	class Task implements Callable<Result>
	{
		String name;

		public Task(String name)
		{
			super();
			this.name =
					name;
		}

		public Result call()
		{
			System.out.printf("%s: Starting\n", name);

			long duration = (long)(Math.random()*10);
			System.out.printf("%s: waiting %d senconds for results.\n", name, duration);

			int value = 0;
			for(int i=0;i<5;i++)
				value += (int)(Math.random()*100);

			Result result =
					new Result();
			result.setName(name);
			result.setValue(value);

			System.out.println(name + ": ends");

			return result;
		}
	}

	@Test
	public void test()
	{
		ExecutorService executor = (ExecutorService)Executors.newCachedThreadPool();
		List<Task> taskList = new ArrayList<>();
		for(int i=0;i<3;i++)
		{
			taskList.add(new Task(String.valueOf(i)));
		}
		List<Future<Result>> resultList = null;


		try
		{
			resultList = executor.invokeAll(taskList);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		executor.shutdown();
		System.out.println("Main: printing ths results");
		for( int i=0;i<resultList.size();i++)
		{
			Future<Result> future = resultList.get(i);

			try
			{
				Result result = future.get();
				System.out.println(result.getName()+": "+result.getValue());
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
	}
}
