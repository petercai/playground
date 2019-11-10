package cai.peter.algorithm.tree;

import cai.peter.algorithm.tree.BTree.Node;

public class BTreeUtil
{
	public static BTree buildFromArrayMid(int[] a)
	{
			BTree tree = new BTree();
			tree.root = insertFromMid(a, 0, a.length-1);
			return tree;
	}
	static Node insertFromMid(int[] a, int start, int end)
	{
		if( start > end ) return null;

		int mid = (start+end)/2;
		Node root = new Node(a[mid]);
		root.left = insertFromMid(a, start,mid-1);
		root.right = insertFromMid(a,mid+1,end);

		return root;
	}

	public static BTree buildFromArray(int[] a)
	{
		BTree tree = new BTree();
		for(int v : a )
			tree.insert(v);
		return tree;
	}
}
