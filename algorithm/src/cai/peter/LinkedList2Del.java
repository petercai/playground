package cai.peter;

public class LinkedList2Del
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
		int v;
		Node next;
		public Node(int x, Node n)
		{
			v = x;
			next = n;
		}
	}
	
	Node head = null, tail = null;
	int size = 0;
	
	public void addFirst(int v)
	{
		head = new Node(v, head);
		if( size == 0 ) tail = head;
		size++;
	}
	public void addLast(int v)
	{
		Node n = new Node(v, null);
		if( size == 0) 
		{
			head = n;
		}
		else
		{
			tail.next = n;
		}
		tail = n;
		size++;
	}
	public Integer removeFirst()
	{
		if( size==0 ) return null;
		Node res = head;
		head = head.next;
		if( --size == 0 ) tail = null;
		return res.v;
	}
	
	public Integer deleteNode(int v)
	{
		if( size == 0 ) return null;
		Node pre = head, cur=head, res = null;
		do
		{
			if(v == cur.v)
			{
				res = cur;
				pre.next = cur.next;
				if( pre == cur ) head = cur.next;
				size--;
				break;
			}
			pre = cur;
			cur = cur.next;
		}while(cur!=null);
		
		if(res == null) return null;
		else return res.v;
	}
	
	public Node removePos(int p)
	{
		if(p==size)
		{
			head = head.next;
			size--;
		}
		else if( p<size)
		{
			Node pre=head,cur=head.next;
			for(int i=0;i<(size-p)-1;i++)
			{
				pre = cur;
				cur = cur.next;
			}
			pre.next = cur.next;
			size--;
		}
		return head;
	}
	
	public Node removePosWoSize(int p)
	{
		if( head == null) return null;
		int size = 0;
		Node n = head;
		while(n!=null)
		{
			size++;
			n = n.next;
		}
		
		if( p>size) return null;
		else if( p == size)
			return head.next;
		else
		{
			n = head;
			int i=0, start=size-p;
			while(n!=null)
			{
				i++;
				if( i == start)
					n.next = n.next.next;
				n = n.next;
			}
			return head;
		}
		
		
		
	}
	
	public Node removePosOnePss(int p)
	{
		Node fast = head, slow=head;
		for(int i=0;i<p;i++)
			fast = fast.next;
		
		while(fast.next!=null)
		{
			fast = fast.next;
			slow = slow.next;
		}
		slow.next=slow.next.next;
		return head;
	}
}
