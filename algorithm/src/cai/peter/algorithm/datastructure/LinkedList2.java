package cai.peter.algorithm.datastructure;

public class LinkedList2<E extends Comparable<E>> {

	class Node<E>
	{
		E e;
		Node<E> next;
		public Node(E e, Node<E> next)
		{
			this.e = e;
			this.next = next;
		}
	}

	Node<E> head=null, tail=null;
	int size=0;

	boolean isEmpty()
	{
		return size==0;
	}

	E first()
	{
		return isEmpty()?null:head.e;
	}

	E last()
	{
		return isEmpty()?null:tail.e;
	}

	void addFirst(E e)
	{
		head = new Node<>(e, head);
		if( isEmpty())
			tail  = head;
		size++;
	}

	void addLast(E e)
	{
		Node<E> node = new Node<>(e, null);
		if( isEmpty())
			head = node;
		else
			tail.next = node;

		tail = node;
		size++;
	}

	E removeFirst()
	{
		if( isEmpty()) return null;

		E e = head.e;
		head = head.next;
		if( --size == 0 )
			tail = null; // update tail

		return e;

	}

	E removeLast()
	{
		if( isEmpty() ) return null;

		E e = tail.e;
		Node<E> p=null, c=head;
		do{
			if( c.next == null && p==null)
			{
				head = null; // update head
			}
			else
			{
				p = c;
				c = c.next;
			}
		}while( c.next != null);
		tail = p; // update tails
		size--;
		return e;
	}

	E delete(E e)
	{
		if( isEmpty() ) return null;
		Node<E> p = null, c = head;
		E result = null;
		do{
			if(c.e.compareTo(e)==0)
			{
				result = c.e;
				if( p==null) head = c.next;  // update head
				else p = c.next;
				break;
			}
			else
			{
				p = c;
				c = c.next;
				if( c==null)
				{
					tail = p; // update tail
					break;
				}
			}
		}while(true);
		size--;
		return result;
	}

}
