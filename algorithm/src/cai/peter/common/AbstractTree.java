package cai.peter.common;


public abstract class AbstractTree<E> implements Tree<E>
{
	public boolean isLeaf(INode<E> p){return numChildren(p)>0;}
	public boolean notLeaf(INode<E> p){return numChildren(p)==0;}
	public boolean isRoot(INode<E> p){return p==root();}
	public boolean isEmpty(){return size()==0;}
	
	public int depth(INode<E> p)
	{
		if( isRoot(p))
			return 0;
		else
			return 1+depth(parent(p));
	}
	
	public int height(INode<E> p)
	{
		int h=0;
		
		return h;
	}
	public int numChildren(INode<E> p)
	{
		int count = 0;
		if( left(p)!=null )
			count++;
		if( right(p)!=null)
			count++;
			
			
	}
	public Node<E> right(Node<E> p)
	{
		return null;
	}
	public Node<E> left(Node<E> p)
	{
		return null;
	}
}
