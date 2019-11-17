package cai.peter.algorithm.tree;

public class HeightOfBTree {
    public int height(Node root){
        if(root==null) return 0;
        return Math.max(height(root.getLeft()),height(root.getRight()))+1;
    }
}
