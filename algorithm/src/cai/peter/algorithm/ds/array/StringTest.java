package cai.peter.algorithm.ds.array;

public class StringTest {

	void _swap(String[] s, int i, int j)
	{
		String tmp = s[i];
		s[i] = s[j];
		s[j] = tmp;
	}
	void _reverse(String[] s, int i, int j)
	{
		while(i<j)
		{
			_swap(s, i,j);
			i++;
			j--;
		}
	}

	public String reverseWord(String input)
	{
		String[] a = input.split(" ");
		_reverse(a, 0, a.length-1);
		String result = "";
		for(String s:a)
			result = result.concat(s+" ");
		return result;

	}

	static public void main(String[] args)
	{
		String s= "this is a test";
		System.out.println(new StringTest().reverseWord(s));
	}
}
