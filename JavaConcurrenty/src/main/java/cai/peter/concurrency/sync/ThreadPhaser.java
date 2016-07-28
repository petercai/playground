package cai.peter.concurrency.sync;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class ThreadPhaser
{
	static public void main(String[] args)
	{
		ThreadPhaser main =
				new ThreadPhaser();
		Phaser phaser =
				new Phaser(3);
		FileSearch system =
				main.new FileSearch("c:\\windows", "log", phaser);
		FileSearch apps =
				main.new FileSearch("c:\\Program Files","log", phaser);
		FileSearch docs =
				main.new FileSearch("c:\\Documents And Settings", "log", phaser );

		Thread st =
				new Thread(system, "System");
		st.start();

		Thread at =
				new Thread(apps, "Apps");
		at.start();

		Thread dt =
				new Thread(docs, "Documents");
		dt.start();

		try
		{
			st.join();
			at.join();
			dt.join();
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Terminated: "+phaser.isTerminated());
	}

	class FileSearch implements Runnable
	{
		String initPath;
		String end;
		List<String> result;
		Phaser phaser;

		public FileSearch(String path, String end, Phaser phaser)
		{
			initPath = path;
			this.end = end;
			this.phaser = phaser;
			result = new ArrayList<>();
		}

		void directoryProcess(File file)
		{
			File[] list = file.listFiles();
			if( list != null )
			{
				for(int i=0;i<list.length;i++)
				{
					if( list[i].isDirectory())
						directoryProcess(list[i]);
					else
						fileProcess(list[i]);
				}
			}
		}

		void fileProcess(File file)
		{
			if( file.getName().endsWith(end))
				result.add(file.getAbsolutePath());
		}

		void filterResult()
		{
			List<String> newResult =
					new ArrayList<>();
			long actualDate =
					new Date().getTime();

			for( int i=0;i<result.size();i++)
			{
				File file =
						new File(result.get(i));
				long fileDate =
						file.lastModified();
				if(actualDate-fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
					newResult.add(result.get(i));
			}
			result = newResult;
		}

		boolean checkResult()
		{
			if( result.isEmpty())
			{
				System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
				System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
				phaser.arriveAndDeregister();
				return false;
			}
			else
			{
				System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), result.size());
				phaser.arriveAndAwaitAdvance();
				return true;
			}
		}

		void showInfo()
		{
			for(int i=0;i<result.size();i++)
			{
				File file = new File(result.get(i));
				System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());

			}
			phaser.arriveAndAwaitAdvance();
		}

		public void run()
		{
			phaser.arriveAndAwaitAdvance();
			System.out.printf("%s: Starting.\n", Thread.currentThread().getName());

			File file = new File(initPath);
			if(file.isDirectory())
				directoryProcess(file);

			if( !checkResult())
				return;

			filterResult();
			if( !checkResult())
				return;

			showInfo();
			phaser.arriveAndDeregister();
			System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
		}
	}
}
