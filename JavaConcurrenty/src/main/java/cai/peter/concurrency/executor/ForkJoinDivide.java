package cai.peter.concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ForkJoinDivide
{
	class Product
	{
		String name;
		double price;
		public synchronized String getName()
		{
			return name;
		}
		public synchronized void setName(String name)
		{
			this.name = name;
		}
		public synchronized double getPrice()
		{
			return price;
		}
		public synchronized void setPrice(double price)
		{
			this.price = price;
		}
		public Product(String name, double price)
		{
			super();
			this.name = name;
			this.price = price;
		};
		
	}
	
	class ProductListGenerator
	{
		public List<Product> generate(int size)
		{
			List<Product> ret = new ArrayList<>();
			
			for( int i=0;i<size;i++)
			{
				Product p = new Product("Product "+i, 10);
				ret.add(p);
			}
			
			return ret;
		}
	}
	
	class Task extends RecursiveAction
	{

		/**
		 * 
		 */
		private static final long	serialVersionUID	= -4247380094940118782L;
		
		List<Product> products;
		int first, last;
		double increment;
		public Task(List<Product> products, int first, int last,
			double increment)
		{
			super();
			this.products = products;
			this.first = first;
			this.last = last;
			this.increment = increment;
		}
		
		protected void compute()
		{
			if( last - first < 10)
				updatePrices();
			else
			{
				int middle = (last+first)/2;
				System.out.printf("Task: pending tasks: %s\n", getQueuedTaskCount());
				Task t1 = new Task(products, first, middle+1,increment);
				Task t2 = new Task(products, middle+1, last, increment);
				invokeAll(t1, t2);
			}
		}

		private void updatePrices()
		{
			for( int i=first;i<last;i++)
			{
				Product p = products.get(i);
				p.setPrice(p.getPrice()*(1+increment));
			}
			
		}
	}
	
	@Test
	public void test()
	{
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);
		Task task = new Task(products, 0, products.size(), .20);
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		
		do
		{
			System.out.printf(	"Main: Thread count: %d\n",
								pool.getActiveThreadCount());
			System.out.printf("Main: Thread steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			
			try
			{
				TimeUnit.MILLISECONDS.sleep(5);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		while (!task.isDone());
		pool.shutdown();
		
		if( task.isCompletedNormally())
			System.out.println("Main: The process has completed mormally.");
		
		for( int i=0;i<products.size();i++)
		{
			Product p = products.get(i);
			if(p.getPrice()!=12)
				System.out.printf("Product %s: %f\n", p.getName(), p.getPrice());
		}
		
		System.out.println("Main: end of the program.");
	}
}
