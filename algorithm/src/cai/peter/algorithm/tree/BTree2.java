package cai.peter.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class BTree2
{
	Node root;

	public List<Integer> preorder()
	{
		List<Integer> lst = new ArrayList<Integer>();
		if( root == null ) return null;

		Stack<Node> st = new Stack<Node>();
		st.push(root);

		while(!st.empty())
		{
			Node e = st.pop();
			lst.add(e.getValue()); // add value before trase

			if( e.getRight() != null )
				st.push(e.getRight());
			if( e.getLeft() !=null )
				st.push(e.getLeft());

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
			if( pre==null || pre.getLeft() == cur || pre.getRight() == cur )
			{
				if( cur.getLeft() != null) st.push(cur.getLeft());
				else if (cur.getRight()!=null) st.push(cur.getRight());
				else
				{
					st.pop();
					lst.add(cur.getValue());
				}
			}
			else if( cur.getLeft() == pre )
			{
				if( cur.getRight() != null ) st.push(cur.getRight());
				else
				{
					st.pop();
					lst.add(cur.getValue());
				}

			}
			else if( cur.getRight() == pre )
			{
				st.pop();
				lst.add(cur.getValue());
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
		if( root.getLeft() != null ) postRecursive(root.getLeft(), lst);
		if( root.getRight() != null ) postRecursive(root.getRight(), lst);

		lst.add(root.getValue());
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
		if(root.getLeft()!=null) inRecursive(root.getLeft(), lst);

		lst.add(root.getValue());

		if( root.getRight()!=null) inRecursive(root.getRight(), lst);
	}

	public Node find(Node root, int v)
	{
		if( root.getValue() == v ) return root;
		else if( v<root.getValue() && root.getLeft()!=null) return find(root.getLeft(), v);
		else if(v>root.getValue() && root.getRight()!=null) return find(root.getRight(), v);
		else return null;
	}

	public Node find2(Node root, int v)
	{
		Node cur = root;
		while(cur!=null && cur.getValue()!=v)
		{
			cur = v<cur.getValue()?cur.getLeft():cur.getRight();
		}

		return cur;
	}

	public void insert(int v)
	{
		Node cur = root!=null?root:new Node(v);
		Node parent;
		while(cur.getValue()!=v)
		{
			parent = cur;
			if( v < cur.getValue())
			{
				// in getLeft()
				cur = cur.getLeft();
				if( cur == null )
				{
					cur = new Node(v);
					parent.setLeft(cur);
				}

			}
			else
			{
				cur = cur.getRight();
				if( cur == null )
				{

					cur = new Node(v);
					parent.setRight( cur);
				}

			}

		}
	}

	public Node delete(Node root, int v)
	{
		Node cur = root;
		if (root==null) return null;
		if( root.getValue() == v )
		{
			root = null;
			return cur;
		}

		Node parent=root;
		boolean isLeft = true;
		while(cur!=null && cur.getValue()!=v)
		{
			parent = cur;
			if( v<cur.getValue() )
			{
				cur=cur.getLeft();
				isLeft = true;
			}
			else
			{
				cur = cur.getRight();
				isLeft = false;
			}
		}
		if( cur != null) // find the node (isLeft or getRight())
		{
			/*
			 * leaf
			 */
			if( cur.getLeft()==null&&cur.getRight()== null)
			{
				if( isLeft ) parent.setLeft(null) ;
				else parent.setRight(null);
				cur = null;

			}
			else if(cur.getLeft()==null) // only rigth
			{
				if(isLeft) parent.setLeft( cur.getRight());
				else	parent.setRight( cur.getRight());
			}
			else if( cur.getRight()==null) // only getLeft()
			{
				if(isLeft) parent.setLeft(cur.getLeft());
				else parent.setRight( cur.getLeft());
			}
			else // has both getRight() & getLeft()
			{
				Node ser = getSuccessor(cur);
				ser.setLeft( cur.getLeft());
				ser.setRight( cur.getRight());
				if( isLeft ) parent.setLeft( ser);
				else	parent.setRight(ser);
				cur = null;
			}
		}
		return cur;
	}

	Node getSuccessor(Node del)
	{
		Node parent = del;
		Node successor = del;
		// go to getRight() first
		Node cur = del.getRight();
		while(cur!=null)
		{
			parent = successor;
			successor = cur;
			cur = cur.getLeft();
		}
		//parent is the successor now
		parent.setLeft( successor.getRight());
		return successor;
	}

	public int height(Node root)
	{
		if( root == null ) return 0;
		return Math.max(height(root.getLeft()), height(root.getRight()))+1;
	}

	public boolean isBalanced(Node root)
	{
		if( root == null ) return true;

		int diff = height(root.getLeft())-height(root.getRight());
		if( Math.abs(diff)>1)
			return false;
		else
			return isBalanced(root.getLeft())&&isBalanced(root.getRight());
	}

}
