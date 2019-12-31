package cai.peter.algorithm.array;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ArraySorting
{
	private int a[];
	@Before
	public void init()
	{
		a = new int[] {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};

	}

	@Test
	public  void testInsertSort()
	{
		System.out.println(Arrays.toString(a));
		insertSort(a);
		System.out.println(Arrays.toString(a));
	}
	@Test
	public  void testShellSort()
	{
		System.out.println(Arrays.toString(a));
		shellSort(a);
		System.out.println(Arrays.toString(a));
	}

	/*
	 * O(n^2)
	 */
	public void insertSort(int[] a)
	{
		int curv = 0;
		for(int i=1;i<a.length;i++)
		{
			int j=i-1;
			curv = a[i];
			while(j>=0&&curv<a[j])
			{
				a[j+1] = a[j];
				j--;
			}
			a[j+1] = curv;
		}
	}

	/*
	 * O(n(long(n)^2)
	 */
	public void shellSort(int[] a)
	{
		int size = a.length;

		int itl = 1;
		while(itl < size/3)
		{
			itl = itl*3+1;
		}

		while(itl>0)
		{

			for( int i=itl;i<size;i++)
			{
				int t = a[i];
				int j = i;
				while(j>itl-1 && a[j-itl]>=t)
				{
					a[j]=a[j-itl];
					j=j-itl;
				}
				a[j]=t;
			}
			itl = (itl-1)/3;
		}
	}




	public void _swap(int[] a, int first, int second)
	{
		int t = a[first];
		a[first]=a[second];
		a[second]=t;
	}


}
