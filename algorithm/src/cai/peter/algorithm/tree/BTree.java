package cai.peter.algorithm.tree;

import org.junit.Test;

public class BTree {

    public int height(Node root) {
        if (root == null) return 0;

        return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
    }

    public boolean isBalance(Node root) {
        if (root == null) return true;
        int diff = Math.abs(height(root.getLeft()) - height(root.getRight())); // compare the height(max)
        if (diff > 1) return false;
        else return isBalance(root.getLeft()) && isBalance(root.getRight()); // compare all branches
    }

    public int miniDepth(Node root) {
        if (root == null) return 0;
        if (root.getRight() == null && root.getLeft() == null) return 1;
        if (root.getLeft() == null) return miniDepth(root.getRight()) + 1;
        if (root.getRight() == null) return miniDepth(root.getLeft()) + 1;

        return Math.min(miniDepth(root.getLeft()), miniDepth(root.getRight()))+1;
    }

    public void insert(Node root, int v) {
        if (root == null) return;
        else {
            Node left = root.getLeft();
            Node right = root.getRight();
            int val = root.getValue();
            if (v <= val) {
                if (left == null) root.setLeft(new Node(v));
                else insert(left, v);
            } else {
                if (right == null) root.setRight(new Node(v));
                else insert(right, v);
            }
        }
    }

    // Function to insert nodes in level order
    public Node insertLevelOrder(int[] arr, int i) {
        Node root = null;
        if (i < arr.length) {
            root = new Node(arr[i]);
            root.setLeft(insertLevelOrder(arr, 2 * i + 1));
            root.setRight(insertLevelOrder(arr, 2 * i + 2));
        }
        return root;
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        Node root = insertLevelOrder(a, 0);
        System.out.println("");
        System.out.println(isBalance(root));
        System.out.println("height=" + height(root));
        System.out.println("depth=" + miniDepth(root));
    }


}
