package cai.peter.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorCallable
{
	class FactorialCalculator implements Callable<Integer>
	{
		Integer number;

		public FactorialCalculator(Integer number)
		{
			super();
			this.number =
					number;
		}

		public Integer call() throws Exception
		{
			int result = 1;
			if( number ==0 || number ==1 )
				result = 1;
			else
			{
				for( int i=2;i<=number; i++)
				{
					result *= i;
					TimeUnit.MILLISECONDS.sleep(20);
				}
			}
			System.out.printf("%s: result=%d\n", Thread.currentThread().getName(), result);

			return result;
		}
	}

	@Test
	public void test()
	{
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();
		Random random = new Random();
		for( int i=0;i<10;i++)
		{
			Integer number = random.nextInt(10);
			FactorialCalculator calculator =
					new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}
		do
		{
			System.out.printf("Main: number of completed tasks: %d\n", executor.getCompletedTaskCount());
			for( int i=0;i<resultList.size();i++)
			{
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: taks %d: isDone=%s\n", i, result.isDone());

			}
			try
			{
				TimeUnit.MILLISECONDS.sleep(50);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(executor.getCompletedTaskCount()<resultList.size());

		System.out.printf("Main: Results\n");
		for(int i=0;i<resultList.size();i++)
		{
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try
			{
				number = result.get();
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
			System.out.printf("Main: Task %d: %d\n", i, number);

		}
		executor.shutdown();
	}
}
