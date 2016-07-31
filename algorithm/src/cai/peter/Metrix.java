package cai.peter;

public class Metrix
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	}
	
	public void rotate(int[][] a, int n)
	{
		for(int layer=0;layer<n/2;layer++)
		{
			int first = layer;
			int last = n-1-layer;
			for( int i=first;i<last;i++)
			{
				int offset = i-first;
				int top = a[first][i];
				
				a[first][i] = a[last-offset][first];
				
			}
		}
		
	}
}
