package cai.peter.algorithm.tree;

public class BTreeUtil {
  public static BTree2 buildFromArrayMid(int[] a) {
    BTree2 tree = new BTree2();
    tree.root = insertFromMid(a, 0, a.length - 1);
    return tree;
  }

  static Node insertFromMid(int[] a, int start, int end) {
    if (start > end) return null;

    int mid = (start + end) / 2;
    Node root = new Node(a[mid]);
    root.setLeft(insertFromMid(a, start, mid - 1));
    root.setRight(insertFromMid(a, mid + 1, end));

    return root;
  }

  public static BTree2 buildFromArray(int[] a) {
    BTree2 tree = new BTree2();
    for (int v : a) tree.insert(v);
    return tree;
  }
}
