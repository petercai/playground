package cai.peter.sort;

import java.util.Arrays;

public class ListSorting
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int a[]={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};  
		System.out.println(Arrays.toString(a));
		ListSorting s = new ListSorting();
//		s.insertSort(a);
//		System.out.println(Arrays.toString(a));
		
		System.out.println(Arrays.toString(a));
		
//		Node head = s.insertSortLList(s.toList(a));
//		System.out.println(Arrays.toString(s.toArray(head)));
	}
	
	 Node toList(int[] a)
	{
		Node head = new Node(a[0]), cur=head;
		for(int i=1;i<a.length;i++ )
		{
			Node n = new Node(a[i]);
			cur.next = n;
			cur = n;
		}
		return head;
	}
	int[] toArray(Node head)
	{
		int size = 0;
		Node cur = head;
		while(cur!= null) 
		{
			size++;
			cur = cur.next;
		}
		int[] res = new int[size];
		cur = head;
		int i=0;
		while(cur!=null){
			res[i++] = cur.val;
			cur = cur.next;
		};
		return res;
	}
	
	class Node
	{
		int val;
		Node next;
		
		public Node(int v)
		{
			val = v;
		}
	}
	
	public Node insertSortLList(Node head)
	{
		if( head == null || head.next == null ) return head;
		
		Node newHead = new Node(head.val);
		Node cur = head.next;
		
		while(cur!=null)
		{
			Node innerCur = newHead;
			Node next = cur.next;
			
			if( cur.val <= newHead.val )
			{
				Node tmp = newHead;
				newHead = cur;
				newHead.next = tmp;
			}
			else
			{
				while(innerCur.next!=null)
				{
					if( cur.val > innerCur.val && cur.val <= innerCur.next.val )
					{
						Node tmp = innerCur.next;
						innerCur.next = cur;
						cur.next = tmp;
					}
					
					innerCur = innerCur.next;
				}
				if( innerCur.next == null && cur.val > innerCur.val )
				{
					innerCur.next = cur;
					cur.next = null;
				}
			}
			cur = next;
		}
		
		return newHead;
	}
	
	Node insertSList(Node head)
	{
		if( head == null || head.next == null) return  head;
		
		Node newHead = new Node(head.val);
		Node cur = head.next;
		
		while(cur !=null)
		{
			Node next = cur.next;
			Node innerCur = newHead;
			while(innerCur.next != null)
			{
				// head
				if( cur.val <= newHead.val )
				{
					Node tmp = newHead;
					newHead = cur;
					newHead.next = tmp;
				}
				//middle
				else if( cur.val > innerCur.val && cur.val <= innerCur.next.val )
				{
					Node tmp = innerCur.next;
					innerCur.next = cur;
					cur.next = tmp;
				}
				//next
				innerCur = innerCur.next;
			}
			//tail
			if( innerCur.next ==  null)
			{
				innerCur.next =cur;
				cur.next = null;
			}
			//next
			cur = next;
		}
		return newHead;
	}
}
