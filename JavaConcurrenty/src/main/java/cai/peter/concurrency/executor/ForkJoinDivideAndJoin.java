package cai.peter.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDivideAndJoin
{
	class Document
	{
		String words[] = {"the", "hello", "goodbye","packet","java","thread","pool", "random","class","amin"};
		String[][] generateDocument(int numLines, int numWords, String word)
		{
			int counter = 0;
			String document[][] = new String[numLines][numWords];
			Random random = new Random();
			
			for( int i=0;i<numLines;i++)
			{
				for(int j=0;j<numWords;j++)
				{
					
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if(document[i][j].equals(word))
					counter++;
				}
			}
			System.out.println("DocumentMock: the word appears "+counter+" times in the document");
			return document;
		}
		
	}
	
	class DocumentTask extends RecursiveTask<Integer>
	{
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 79297439508287653L;
		String[][] document;
		int start, end;
		String word;
		public DocumentTask(String[][] document, int start, int end, String word)
		{
			super();
			this.document = document;
			this.start = start;
			this.end = end;
			this.word = word;
		}
		
		protected Integer compute()
		{
			int ret=0;
			if( end-start <10)
				ret = processLines(document, start, end, word);
			else
			{
				int mid = (start+end)/2;
				DocumentTask t1 = new DocumentTask(document, start,mid,word);
				DocumentTask t2 = new DocumentTask(document, mid, end, word);
				invokeAll(t1, t2);
				
				try
				{
					ret = groupResults(t1.get(), t2.get());
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
			
			return ret;
		}

		
		private int groupResults(Integer f, Integer s)
		{
			// TODO Auto-generated method stub
			return f+s;
		}

		public Integer processLines(String[][] document, int start, int end, String word)
		{
			List<LineTask> tasks = new ArrayList<>();
			for(int i=start;i<end;i++)
			{
				LineTask task = new LineTask(document[i], 0, document[i].length, word);
				tasks.add(task);
			}
			invokeAll(tasks);
			
			int ret = 0;
			for(int i=0;i<tasks.size();i++)
			{
				LineTask task = tasks.get(i);
				ret += task.get();
			}
			return new Integer(ret);
		}
	}

	class LineTask extends RecursiveTask<Integer>
	{

		/**
		 * 
		 */
		private static final long	serialVersionUID	= -4336860810297244403L;
		String[] line;
		int start, end;
		String word;
		public LineTask(String[] line, int start, int end, String word)
		{
			super();
			this.line = line;
			this.start = start;
			this.end = end;
			this.word = word;
		}
		
		protected Integer compute()
		{
			Integer ret = null;
			if( end-start<100)
				ret = count(line, start, end, word);
			else
			{
				int mid = (start+end)/2;
				LineTask t1 = new LineTask(line, start, mid, word);
				LineTask t2 = new LineTask(line, mid, end, word);
				invokeAll(t1, t2);
				
				try
				{
					ret = groupresults(t1.get(), t2.get());
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
			
			return ret;
		}

		private Integer groupresults(Integer f, Integer s)
		{
			// TODO Auto-generated method stub
			return f+s;
		}

		private Integer count(String[] line, int start, int end, String word2)
		{
			int counter = 0;
			for(int i=start;i<end;i++)
			{
				if( line[i].equals(word))
				{
					counter++;
				}
			}
			
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return counter;
		}
		
	}
}
