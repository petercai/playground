package cai.peter.common;


public interface Tree<E>
{
	INode<E> root();
	INode<E> parent(INode<E> p);
	
	Iterable<INode<E>> children(INode<E> p);
	Iterable<INode<E>> nodes();
	
	int numChildren(INode<E> p);
	int size();
	boolean isEmpty();
	boolean isRoot(INode<E> p);
	boolean isLeaf(INode<E> p);
	boolean notLeaf(INode<E> p);

}
