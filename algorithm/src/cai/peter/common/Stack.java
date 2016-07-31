package cai.peter.common;

public class Stack<E>
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}
	
	Node<E> top;
	
	public void push(Node<E> e)
	{
		if( e!=null)
		{

			e.next = top;
			top = e;
		}
	}
	
	public Node<E> pop()
	{
		if( top == null) return null;
		else
		{
			Node<E> res = new Node<E>(top.value);
			top = top.next;
			return res;
		}

	}
	
	public Node<E> peek()
	{
		if( top == null) return null;
		else return new Node<E>(top.value);
	}
}
