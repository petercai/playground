package cai.peter.common;

public class Queue<E>
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}
	
	Node<E> first, last;
	public void enqueue(Node<E> e)
	{
		if( first == null )
		{
			first = e;
			last = e;
			
		}
		else
		{
			last.next = e;
			last = e;
		}
	}
	public Node<E> dequeue()
	{
		if( first == null ) return  null;
		else
		{

			Node<E> res = new Node<E>(first.value);
			first = res.next;
			return res;
		}
		
	}
}
