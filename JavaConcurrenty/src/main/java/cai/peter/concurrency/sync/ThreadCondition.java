package cai.peter.concurrency.sync;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCondition
{
	static public void main(String[] args)
	{
		ThreadCondition main =
				new ThreadCondition();
		FileMock mock =
				main.new FileMock(100,10);
		Buffer buffer =
				main.new Buffer(20);
		Producer producer =
				main.new Producer(mock, buffer);
		Thread t =
				new Thread(producer,"Producer");

		Consumer[] consumers = new Consumer[3];
		Thread[] ts = new Thread[3];

		for(int i=0;i<3;i++)
		{
			consumers[i] = main.new Consumer(buffer);
			ts[i] = new Thread(consumers[i], "Consumer "+i);
		}

		t.start();
		for(int i=0;i<3;i++)
			ts[i].start();

	}

	class FileMock
	{
		String content[];
		int index;

		public FileMock(int size, int length)
		{
			content = new String[size];
			for(int i=0;i<size;i++)
			{
				StringBuilder buffer = new StringBuilder(length);
				for(int j=0;i<length;j++)
				{
					int indice = (int)Math.random()*255;
					buffer.append((char)indice);
				}
				content[i] = buffer.toString();
			}
			index = 0;
		}

		boolean hasMoreLines()
		{
			return index < content.length;
		}

		String getLine()
		{
			if( hasMoreLines())
			{
				System.out.println("Mock: "+(content.length - index));
				return content[index+1];
			}
			return null;
		}
	}

	class Buffer
	{
		LinkedList<String> buffer;
		int maxSize;
		ReentrantLock lock;
		Condition lines;
		Condition space;
		boolean pendingLines;

		public boolean hasPendingLines()
		{
			return pendingLines||buffer.size()>0;
		}

		public void setPendingLines(boolean pendingLines)
		{
			this.pendingLines =
					pendingLines;
		}

		public Buffer(int size)
		{
			maxSize = size;
			buffer = new LinkedList<>();
			lock = new ReentrantLock();
			lines = lock.newCondition();
			space = lock.newCondition();
			pendingLines = true;
		}

		public void insert(String line)
		{
			lock.lock();
			try
			{
				while (buffer.size() == maxSize )
					space.await();
				buffer.offer(line);
				System.out.printf("%s: Inserted line: %d\n",  Thread.currentThread().getName(), buffer.size());
				lines.signalAll();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
		}

		public String get()
		{
			String line = null;
			lock.lock();
			try
			{
				while(buffer.size()==0 && this.hasPendingLines())
					lines.await();
				if( hasPendingLines())
				{
					line = buffer.poll();
					System.out.printf("%s: Line readed: %d\n", Thread.currentThread().getName(), buffer.size());
					space.signalAll();
				}
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
			return line;
		}
	}

	class Producer implements Runnable
	{
		FileMock mock;
		Buffer buffer;

		public Producer(FileMock mock, Buffer buffer )
		{
			this.mock = mock;
			this.buffer = buffer;
		}

		public void run()
		{
			buffer.setPendingLines(true);
			while(mock.hasMoreLines())
			{
				String line = mock.getLine();
				buffer.insert(line);

			}
			buffer.setPendingLines(false);
		}
	}

	class Consumer implements Runnable
	{
		Buffer buffer;
		public Consumer(Buffer buf )
		{
			buffer = buf;
		}

		public void run()
		{
			while(buffer.hasPendingLines())
			{
				String line = buffer.get();
				processLine(line);
			}
		}

		void processLine(String line)
		{
			try
			{
				Random random = new Random();
				Thread.sleep(random.nextInt(100));
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
