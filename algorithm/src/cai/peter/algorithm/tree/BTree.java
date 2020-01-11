package cai.peter.algorithm.tree;

import java.util.Optional;

public class BTree {

    private Node root;

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

    public void insert(Node root, int v) {
        if (root == null) root = new Node(v);
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
}
