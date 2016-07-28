package cai.peter.concurrency.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import org.junit.Test;

public class ThreadExchanger
{
	class Producer implements Runnable
	{
		List<String> buffer;
		final Exchanger<List<String>> exchanger;

		public Producer(List<String> b, Exchanger<List<String>> e)
		{
			buffer = b;
			exchanger = e;
		}

		public void run()
		{
			int cycle = 1;
			for(int i=0;i<10;i++)
			{

				System.out.printf("Producer: cycle %d\n", cycle);

				for( int j=0;j<10;j++)
				{
					String message = "Event "+((i*10)+j);
					System.out.printf("Producer: %s\n", message);
					buffer.add(message);
				}

				try
				{
					buffer = exchanger.exchange(buffer);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Producer: "+buffer.size());
				cycle++;
			}
		}
	}

	class Consumer implements Runnable
	{
		List<String> buffer;
		public Consumer(List<String> buffer, Exchanger<List<String>> exchanger)
		{
			super();
			this.buffer =
					buffer;
			this.exchanger =
					exchanger;
		}
		final Exchanger<List<String>> exchanger;

		public void run()
		{
			int cycle = 1;
			for(int i=0;i<10;i++)
			{
				System.out.printf("Consumer: cycle %d\n", cycle);
				try
				{
					buffer = exchanger.exchange(buffer);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("consumer: "+buffer.size());

				for( int j=0;j<10;j++)
				{
					String message = buffer.get(0);
					System.out.println("Consumer: "+message);
					buffer.remove(0);
				}
				cycle++;
			}
		}

	}

	@Test
	public void test()
	{
		ThreadExchanger main =
				new ThreadExchanger();
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();

		Exchanger<List<String>> exchanger = new Exchanger<>();
		Producer producer = main.new Producer(buffer1, exchanger);
		Consumer consumer = main.new Consumer(buffer2, exchanger);
		Thread tp =
				new Thread(producer);
		Thread tc =
				new Thread(consumer);
		tp.start();
		tc.start();
	}
}
