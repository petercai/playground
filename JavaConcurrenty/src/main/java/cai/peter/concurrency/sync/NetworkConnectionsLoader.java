package cai.peter.concurrency.sync;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable
{
	public void run()
	{
		System.out.printf("Beginning network connecting: %s\n", new Date());
		try
		{
			TimeUnit.SECONDS.sleep(6);
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.printf("Network connecting has finsihed: %s\n", new Date());
	}
}
