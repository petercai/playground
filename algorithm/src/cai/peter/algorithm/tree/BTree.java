package cai.peter.algorithm.tree;

public class BTree {

    public int height(Node root){
        if( root==null ) return 0;
        return Math.max(height(root.getLeft()),height(root.getRight()))+1;
    }

    public boolean isBalance(Node root){
        if( root==null) return true;

        int diff = height(root.getLeft())-height(root.getRight());
        if(Math.abs(diff)>1)
            return false;
        else
            return isBalance(root.getLeft())&&isBalance(root.getRight());
    }
}
