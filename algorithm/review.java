class sorting
{
	void insertSort(int[] a)
	{
		for( int i=1;i<a.length;i++)
		{
			int j=i-1;
			int c = a[i]
			while(j<=0&&c<a[j])
			{
				a[j+1]=a[j];
				j--;
			}
			a[j+1] = c;
		}
	}
	
	void _swap(int[]a, int first, int second)
	{
		int t = a[first];
		a[first]=a[second];
		a[second]=t;
	}
	
	void bubbleSort(int[] a)
	{
		for(int i=a.length-1;i>1;i--)
			for(int j=0;j<i;j++)
			{
				if(a[j]>a[j+1])
					_swap(a,j,j+1);
			}
	}
	
	void slelectSort(int[] a)
	{
		int len = a.length;
		for(int i=0;i<len;i++)
		{
			int min = i;
			for(int j=i+1;j<len;j++)
			{
				if( a[j]<a[min]) min =j;
			}
			_swap(a, i, min);
		}
	}
	
	int[] ar, hp;
	void mergeSort(int[] a)
	{
		if( a==null || a.length <2 ) return;
		
		ar = a;
		hp = new int[a.length];
		_divide(a, 0, a.length);
	}
	
	void _divide(int[] a, int start, int end)
	{
		if( start < end )
		{
			int mid = start + (end-start)/2;
			_divide(a, start, mid);
			_divide(a, mid+1, end);
			_merge(start, mid, end);
		}
	}
	void _merge(int start, int mid, int end)
	{
		for( int i=start, i<=end; i++)
			hp[i] = ar[i];
		int i=start;
		int j=mid+1;
		int k = start;
		while(i<=mid && j<=end)
		{
			if( hp[i] <= hp[j] )
				ar[k] = hp[i++];
			else
				ar[k] = hp[j++];
			k++;
		}
		while(i<=mid)
			ar[k++] = hp[i++];
	}
	
	void quickSort(int[] a)
	{
		if( a==null || a.length <2 ) return null;
		
		_quick(a, 0, a.length-1);
	}
	void_quick(int[] a , int start, int end)
	{
		if( start >= end ) return;
		
		int pivot = start + (end-start)/2;
		int i = start, j=end;
		while( i<=j)
		{
			while(a[i]<a[pivot]) i++;
			while(a[j]>a[pivot]) j++;
			if( i<=j )
				_swap(a, i++, j++);
		}
		if( start < j ) _quick(a, start, j);
		if( i < end ) _quick(a, j, end);
	}
}