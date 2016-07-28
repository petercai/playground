package cai.peter.concurrency.sync;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable
{
	public void run()
	{
		System.out.printf("Beginning data sources loading: %s\n", new Date());
		try
		{
			TimeUnit.SECONDS.sleep(4);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("Data srouces loading has finished: %s\n", new Date());
	}
}
