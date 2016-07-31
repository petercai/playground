package cai.peter.exame;

public class CharCount
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String s = "AbcdABCDacdACE";
		
		char[] chr = s.toCharArray();
		
		int len = chr.length;
		
		
		int c[] = new int[128];
		for( int i=0;i<s.length();i++)
		{
			char ch = s.charAt(i);
			c[ch]++;
		}
		for( int j=0;j<128;j++)
		{
			char k = (char)j;
			if( c[j]>0)
				System.out.println("char: "+k+ "- value: "+c[j]);
		}
	}
}
