package cai.peter;

public class Graph
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
	}
	class Node
	{
		int val;
		Node next;
		Node[] neighbors;
		boolean visited;
		
		public Node(int v)
		{
			val = v;
		}
		public Node(int v, Node[] n)
		{
			val = v;
			neighbors = n;
		}
		
		public String toString()
		{
			return "value: "+val;
		}
		
	}
	
	class Queue
	{
		Node first, last;
		public void enqueue(Node n)
		{
			if( first == null )
			{
				first = n;
				last = first;
			}
			else
			{
				last.next = n;
				
			}
		}
		public Node dequeue()
		{
			if( first == null ) return null;
			else
			{
				Node tmp = new Node(first.val,first.neighbors);
				first = first.next;
				return tmp;
			}
		}
	}
}
