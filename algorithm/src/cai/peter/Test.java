/*******************************************************************************
 * Copyright (c) 2010 Peter Cai 
 * All rights reserved.
 * 
 * ***************************************************************
 * Filename:    Test.java
 * Description: algorithm
 * Created by:  Peter Cai
 * Created on:  2016-01-16
 ******************************************************************************/




package cai.peter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Peter Cai
 *
 */
public class Test
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		HashMap<String, String> map;
		TreeMap<String, String> map2;
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * replace space with %20 
	 */
	public  void replaceSpace(char[] s)
	{
		int count = 0;
		for(int i=0;i<s.length;i++)
			if(s[i]==' ') count++;
		
		char[] a = new char[s.length+2*count];
		
		int j=0;
		for( int i=0;i<s.length;i++)
		{
			if( s[i]==' ')
			{
				a[j++]='%';
				a[j++]='2';
				a[j++]='0';
			}
			else
				a[j++]=s[i];
		}
		
		s = a;
	}
}
