package cai.peter.exame;

import java.util.Stack;

public class EvalRPN
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String[] in = {"4","5","+","3","*"};
		System.out.println(eval(in));
	}
	
	public static int eval(String[] token)
	{
		int result = 0;
		String op = "+-*/";
		
		Stack<String> stack = new Stack<String>();
		
		for(String v : token)
		{
			if( !op.contains(v))
				stack.push(v);
			else
			{
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				int o = op.indexOf(v);
				switch(o)
				{
				case 0:
					stack.push(String.valueOf(a+b));
					break;
				case 1:
					stack.push(String.valueOf(a-b));
					break;
				case 2:
					stack.push(String.valueOf(a*b));
					break;
				case 3:
					stack.push(String.valueOf(a/b));
					break;
				}
			}
		}
		result = Integer.valueOf(stack.pop());
		
		return result;
	}
}
