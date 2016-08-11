package cai.peter.algorithm.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class BTree
{
	public static  class Node
	{
		int val;
		Node left=null,right=null;
		public Node(int v)
		{
			val = v;
		}
	}

	Node root;

	public List<Integer> inorder(Node root)
	{
		List<Integer> list = new ArrayList<Integer>();
		if( root == null) return list;

		Stack<Node> stack = new Stack<Node>();

		return list;
	}

	public List<Integer> preorder()
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

	public void insert(int v)
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
