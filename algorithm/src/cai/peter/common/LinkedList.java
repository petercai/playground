package cai.peter.common;

public class LinkedList<E> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static class Node<E>
	{
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> next)
		{
			this.element = e;
			this.next = next;
		}
		
		public E getElement()
		{
			return element;
		}
		
		public Node<E> getNext()
		{
			return next;
		}
		
		public void setNext(Node<E> next)
		{
			this.next = next;
		}
	}

	
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	public int getSize()
	{
		return size;
	}

	public boolean isEmpty()
	{return size == 0;}
	
	public E last()
	{
		return isEmpty()?null:tail.getElement();
	}
	public E first()
	{
		return isEmpty()?null:head.getElement();
	}
	
	public void addFirst(E e)
	{
		head = new Node<>(e, head);
		if( size == 0 ) tail = head;
		size++;
	}
	
	public void addLast(E e)
	{
		Node<E> node = new Node<>(e, null);
		if( size == 0 )
			head = node;
		else
			tail.setNext(node);
		tail = node;
		size++;
	}
	
	public E removeFirst()
	{
		if( size == 0 ) return null;
		
		E result = head.getElement();
		head = head.getNext();
		if(--size == 0 )
			tail = null;
		return result;
	}
	
	public E deleteNode(Node<E> e)
	{
		boolean result = false;
		Node<E> cur = head;
		if( cur != null )
		{
			while( cur.next != null)
			{
				if( cur.getElement() == e.getElement() )
					
			}
		}
		while( cur != null && cur)
		{
			while
		}
	}
}
