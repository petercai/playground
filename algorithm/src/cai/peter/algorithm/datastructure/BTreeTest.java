package cai.peter.algorithm.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;


public class BTreeTest
{
	BTree tree;
	int[] ar = {0,1,2,3,4,5,6,7,8,9};

	@Before
	public void init()
	{
		tree = BTreeUtil.buildFromArrayMid(ar);

	}
	@Test
	public void testPreOrder()
	{
		// TODO Auto-generated method stub




		List<Integer> list = tree.preorder(root);

		System.out.println("preorder: ");
		System.out.println(StringUtils.join(list,","));

	}
	@Test
	public void test()
	{
		// TODO Auto-generated method stub

		int[] ar = {0,1,2,3,4,5,6,7,8,9};


		BTreeTest tree = new BTreeTest();
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

//		int[] a = {19,7,2,13,36,25,49,5,8};
//		System.out.println(Arrays.toString(a));
//		Node rt = new BTreeTest.Node(a[0]);
//		for(int i=1;i<a.length;i++ )
//		{
//			tree.insert(a[i],rt);
//		}
//		System.out.println(tree.inorderRecursive(rt));
	}



}
