package cai.peter.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorCompletionServiceTest
{
	class ReportGenerator implements Callable<String>
	{
		String sender;
		String title;
		public ReportGenerator(String sender, String title)
		{
			super();
			this.sender = sender;
			this.title = title;
		}
		
		public String call()
		{
			long duration = (long)(Math.random()*10);
			System.out.printf("%s_%s: ReportGenerator: Generating a report during %d seconds\n",sender, title, duration);
			try
			{
				TimeUnit.SECONDS.sleep(duration);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sender+": "+title;
		}
	}
	
	class ReportRequest implements Runnable
	{
		String name;
		CompletionService<String> service;
		public ReportRequest(String name, CompletionService<String> service)
		{
			super();
			this.name = name;
			this.service = service;
		}
		public void run()
		{
			ReportGenerator generator = new ReportGenerator(name,"Report");
			service.submit(generator);
		}
	}
	
	class ReportProcessor implements Runnable
	{
		CompletionService<String> service;
		public ReportProcessor(CompletionService<String> service)
		{
			super();
			this.service = service;
			end = false;
		}
		boolean end;
		
		public synchronized void setEnd(boolean end)
		{
			this.end = end;
		}

		public void run()
		{
			while(!end)
			{
				try
				{
					Future<String> result = service.poll(20, TimeUnit.SECONDS);
					if( result != null )
					{
						String report = result.get();
						System.out.printf("reportReceiver: Report Received: %s\n", report);
					}
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
				System.out.println("ReportSender: End");
			}
		}
	}
	
	@Test
	public void test()
	{
		ExecutorService executor = Executors.newCachedThreadPool();
		CompletionService<String> service = new ExecutorCompletionService<>(executor);
		
		ReportRequest factReq = new ReportRequest("Face", service);
		ReportRequest onlineReq = new ReportRequest("Online", service);
		
		Thread tFace = new Thread(factReq);
		Thread tOnline = new Thread(onlineReq);
		
		ReportProcessor processor = new ReportProcessor(service);
		Thread tSender = new Thread(processor);
		
		System.out.println("Main: starting the Threads");
		tFace.start();
		tOnline.start();
		tSender.start();
		
		try
		{
			System.out.printf("Main: waiting for the report generators.\n");
			tFace.join();
			tOnline.join();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main: shutting down the executor");
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
		
		processor.setEnd(true);
		System.out.println("Main: ends");
		
	}
}
