package cai.peter.common;


public class LinkedBTree<E> extends AbstractBTree<E>
{
	static class Node<E> implements INode<E>
	{
		E element;
		Node<E> parent;
		Node<E> left;
		Node<E> right;
		public E getElement() {return element;}
		
		public synchronized Node<E> getParent()
		{
			return parent;
		}
		public synchronized void setParent(Node<E> parent)
		{
			this.parent = parent;
		}
		public synchronized Node<E> getLeft()
		{
			return left;
		}
		public synchronized void setLeft(Node<E> left)
		{
			this.left = left;
		}
		public synchronized Node<E> getRight()
		{
			return right;
		}
		public synchronized void setRight(Node<E> right)
		{
			this.right = right;
		}
		
	}
	
	private Node<E> root;
	public Node<E> root(){return root;}
	private int size;
	public int size(){return size;}
	
	public Node<E> parent(Node<E> p)
	{
		return p.getParent();
	}
	protected Node<E> validate(INode<E> n) throws Exception
	{
		if(!(n instanceof Node)) throw new Exception("NOT a Node");
		return (Node<E>)n;
	}
	@Override
	public INode<E> left(INode<E> p) throws Exception{return validate(p).getLeft();}
	@Override
	public INode<E> right(INode<E> p){return p.getRight();}
	
}
