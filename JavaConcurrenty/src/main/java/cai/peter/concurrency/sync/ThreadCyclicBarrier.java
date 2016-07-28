package cai.peter.concurrency.sync;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadCyclicBarrier
{

	static public void main(String[] args)
	{
		ThreadCyclicBarrier main =
				new ThreadCyclicBarrier();

		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;

		MatrixMock mock =
				main.new MatrixMock(ROWS, NUMBERS, SEARCH);

		Result result =
				main.new Result(ROWS);

		Grouper grouper =
				main.new Grouper(result);

		CyclicBarrier barrier =
				new CyclicBarrier(PARTICIPANTS, grouper);

		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for(int i=0;i<PARTICIPANTS;i++)
		{
			searchers[i]= main.new Searcher(
			                           i+LINES_PARTICIPANT,
			                           i*LINES_PARTICIPANT+LINES_PARTICIPANT,
			                           mock,
			                           result,
			                           5,
			                           barrier);
			Thread t =
					new Thread(searchers[i]);
			t.start();
		}

		System.out.printf("Main: the main thread has finished.\n");
	}

	class MatrixMock
	{
		int data[][];

		public  MatrixMock(int size, int length, int number)
		{
			int counter = 0;
			data = new int[size][length];
			Random random =
					new Random();

			for(int i=0;i<size;i++)
				for(int j=0;i<length;j++)
				{
					data[i][j] = random.nextInt(10);
					if( data[i][j] == number )
						counter++;
				}
			System.out.printf("Mock: there are %d ocurrences of number in generated data.\n", counter, number);
		}

		public int[] getRow(int row)
		{
			if( row>=0 && row <data.length)
				return data[row];
			return null;
		}
	}

	class Result
	{
		int data[];
		public Result(int size)
		{
			data = new int[size];
		}

		void setData(int pos, int val)
		{
			data[pos]=val;
		}

		int[] getData()
		{
			return data;
		}


	}

	class Searcher implements Runnable
	{
		int firstRow;
		int lastRow;
		MatrixMock mock;
		Result result;
		int number;
		CyclicBarrier barrier;

		public Searcher( int first, int last, MatrixMock mock, Result result, int number, CyclicBarrier barrier)
		{
			firstRow = first;
			lastRow = last;
			this.mock = mock;
			this.result = result;
			this.barrier = barrier;
		}

		public void run()
		{
			int counter;
			System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
			for( int i=firstRow;i<lastRow;i++)
			{
				int row[] = mock.getRow(i);
				counter = 0;
				for(int j=0;j<row.length;j++)
					if( row[j] == number)
						counter++;
				result.setData(i,  counter);
			}

			System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

			try
			{
				barrier.await();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (BrokenBarrierException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	class Grouper implements Runnable
	{
		Result result;

		public Grouper(Result res )
		{
			result = res;
		}

		public void run()
		{
			int finalResult = 0;
			System.out.printf("Grouper: processing results...\n");
			int data[] = result.getData();
			for( int number:data)
				finalResult += number;

			System.out.printf("Grouper: Total result: %d.\n", finalResult);
		}

	}
}
