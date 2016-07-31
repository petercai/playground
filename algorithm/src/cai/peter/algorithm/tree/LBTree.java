package cai.peter.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;


public class LBTree
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		int[] ar = {0,1,2,3,4,5,6,7,8,9};
		
		
		LBTree tree = new LBTree();
		Node root = tree.fromArray(ar);
		List<Integer> list = tree.preorder(root);
		
		System.out.println("preorder: ");
		System.out.println(StringUtils.join(list,","));
		
		list = tree.postorder(root);
		System.out.println("postorder: ");
		System.out.println(StringUtils.join(list,","));
		
		list = tree.postorderRecur(root);
		System.out.println("postorder (recurisve): ");
		System.out.println(StringUtils.join(list,","));
		
		list = tree.inorderRecursive(root);
		System.out.println("inorder (recursive): ");
		System.out.println(StringUtils.join(list,","));
		
		int[] a = {19,7,2,13,36,25,49,5,8};
		System.out.println(Arrays.toString(a));
		Node rt = new LBTree.Node(a[0]);
		for(int i=1;i<a.length;i++ )
		{
			tree.insert(rt,a[i]);
		}
		System.out.println(tree.inorderRecursive(rt));
	}
	
	public Node fromArray(int[] a)
	{
		if( a.length==0) return  null;
		return fromArray(a, 0, a.length-1);
	}
	Node fromArray(int[] a, int start, int end)
	{
		if( start > end ) return null;
		
		int mid = (start+end)/2;
		Node root = new Node(a[mid]);
		root.left = fromArray(a, start,mid-1);
		root.right = fromArray(a,mid+1,end);
		
		return root;
	}
	
	public static  class Node
	{
		int val;
		Node left=null,right=null;
		public Node(int v)
		{
			val = v;
		}
	}
	public List<Integer> inorder(Node root)
	{
		List<Integer> list = new ArrayList<Integer>();
		if( root == null) return list;
		
		Stack<Node> stack = new Stack<Node>();
		
		return list;
	}
	
	public List<Integer> preorder(Node root)
	{
		List<Integer> lst = new ArrayList<Integer>();
		if( root == null ) return null;
		
		Stack<Node> st = new Stack<Node>();
		st.push(root);
		
		while(!st.empty())
		{
			Node e = st.pop();
			lst.add(e.val);
			
			if( e.right != null )
				st.push(e.right);
			if( e.left !=null )
				st.push(e.left);
			
		}
		return lst;
	}
	
	public List<Integer> postorder(Node root)
	{
		List<Integer> lst = new ArrayList<Integer>();
		if( root == null ) return null;
		
		Stack<Node> st = new Stack<Node>();
		st.push(root);
		
		Node pre = null;
		while(!st.empty())
		{
			Node cur = st.peek();
			if( pre==null || pre.left == cur || pre.right == cur )
			{
				if( cur.left != null) st.push(cur.left);
				else if (cur.right!=null) st.push(cur.right);
				else
				{
					st.pop();
					lst.add(cur.val);
				}
			}
			else if( cur.left == pre )
			{
				if( cur.right != null ) st.push(cur.right);
				else
				{
					st.pop();
					lst.add(cur.val);
				}
					
			}
			else if( cur.right == pre )
			{
				st.pop();
				lst.add(cur.val);
			}
			
			pre = cur;
		}
		
		return lst;
	}
	
	
	public List<Integer> postorderRecur(Node root)
	{
		List<Integer> lst = new ArrayList<Integer>();
		if( root != null )
			postRecursive(root, lst);
		
		return lst;
	}
	
	void postRecursive(Node root, List<Integer> lst)
	{
		if( root.left != null ) postRecursive(root.left, lst);
		if( root.right != null ) postRecursive(root.right, lst);
		
		lst.add(root.val);
	}
	public List<Integer> inorderRecursive(Node root)
	{
		List<Integer> lst = new ArrayList<Integer>();
		
		if( root != null )
			inRecursive(root, lst);
		
		return lst;
	}
	
	void inRecursive(Node root, List<Integer> lst)
	{
		if(root.left!=null) inRecursive(root.left, lst);
		
		lst.add(root.val);
		
		if( root.right!=null) inRecursive(root.right, lst);
	}
	
	public Node find(Node root, int v)
	{
		if( root.val == v ) return root;
		else if( v<root.val && root.left!=null) return find(root.left, v);
		else if(v>root.val && root.right!=null) return find(root.right, v);
		else return null;
	}
	
	public Node find2(Node root, int v)
	{
		Node cur = root;
		while(cur!=null && cur.val!=v)
		{
			cur = v<cur.val?cur.left:cur.right;
		}
		
		return cur;
	}
	
	public Node insert(Node root, int v)
	{
		Node cur = root!=null?root:new Node(v);
		Node parent;
		while(cur.val!=v)
		{
			parent = cur;
			if( v < cur.val)
			{
				// in left
				cur = cur.left;
				if( cur == null )
				{
					cur = new Node(v);
					parent.left = cur;
				}

			}
			else
			{
				cur = cur.right;
				if( cur == null )
				{
					
					cur = new Node(v);
					parent.right = cur;
				}

			}

		}
		return cur;
	}
	
	public Node delete(Node root, int v)
	{
		Node cur = root;
		if (root==null) return null;
		if( root.val == v )
		{
			root = null;
			return cur;
		}
		
		Node parent=root;
		boolean isLeft = true;
		while(cur!=null && cur.val!=v)
		{
			parent = cur;
			if( v<cur.val )
			{
				cur=cur.left;
				isLeft = true;
			}
			else
			{
				cur = cur.right;
				isLeft = false;
			}
		}
		if( cur != null) // find the node (isLeft or right)
		{
			/*
			 * leaf
			 */
			if( cur.left==null&&cur.right== null) 
			{
				if( isLeft ) parent.left = null;
				else parent.right = null;
				cur = null;
						
			}
			else if(cur.left==null) // only rigth
			{
				if(isLeft) parent.left = cur.right;
				else	parent.right = cur.right;
			}
			else if( cur.right==null) // only left
			{
				if(isLeft) parent.left = cur.left;
				else parent.right = cur.left;
			}
			else // has both right & left
			{
				Node ser = getSuccessor(cur);
				ser.left = cur.left;
				ser.right = cur.right;
				if( isLeft ) parent.left = ser;
				else	parent.right = ser;
				cur = null;
			}
		}
		return cur;
	}
	
	Node getSuccessor(Node del)
	{
		Node parent = del;
		Node successor = del;
		// go to right first
		Node cur = del.right;
		while(cur!=null)
		{
			parent = successor;
			successor = cur;
			cur = cur.left;
		}
		//parent is the successor now
		parent.left = successor.right;
		return successor;
	}
	
	public int height(Node root)
	{
		if( root == null ) return 0;
		return Math.max(height(root.left), height(root.right))+1;
	}
	
	public boolean isBalanced(Node root)
	{
		if( root == null ) return true;
		
		int diff = height(root.left)-height(root.right);
		if( Math.abs(diff)>1)
			return false;
		else
			return isBalanced(root.left)&&isBalanced(root.right);
	}
	
	public int depth(Node root)
	{
		return -1;
	}
}
