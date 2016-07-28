package cai.peter.concurrency.sync;

import java.io.File;

public class FileSearch implements Runnable
{
	String initPath;
	String fileName;

	public FileSearch(String path, String name)
	{
		initPath = path;
		fileName = name;
	}

	public void run()
	{
		File file  = new File(initPath);
		if( file.isDirectory())
		{
			try
			{
				directoryProcess(file);
			}
			catch(InterruptedException e)
			{
				System.out.printf("%s: the search has been interrupted\n", Thread.currentThread().getName());
			}
		}
	}

	void directoryProcess(File file) throws InterruptedException
	{
		File[] list = file.listFiles();
		if( list != null )
		{
			for(File f : list)
			{
				if( f.isDirectory()) directoryProcess(f);
				else fileProcess(f);
			}
		}
		if( Thread.interrupted())
		{
			throw new InterruptedException();
		}
	}

	void fileProcess(File f) throws InterruptedException
	{
		if( f.getName().equals(fileName))
			System.out.printf("%s : %s\n", Thread.currentThread().getName(), f.getAbsolutePath());
		if( Thread.interrupted() )
			throw new InterruptedException();
	}
}
